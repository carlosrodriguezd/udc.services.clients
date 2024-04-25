package udc.services.internalgrpcapi.fakeclient;

import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.grpc.CallCredentials;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslProvider;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import udc.services.internalgrpcapi.fakeclient.builders.FakeStudentBuilder;
import udc.services.internalgrpcapi.fakeclient.helpers.ServiceHelpers;
import udc.services.internalgrpcapi.fakeclient.oauth.BearerToken;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeStudent;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc.FakeServiceBlockingStub;
import udc.services.internalgrpcapi.fakeclient.trailers.ResourceDuplicateTrailer;
import udc.services.internalgrpcapi.fakeclient.trailers.ValidationTrailer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateFakeStudentTest extends BaseTest {
	
    private static final Logger logger = LogManager.getLogger(CreateFakeStudentTest.class.getName());

	private FakeServiceBlockingStub blockingStub;
    private FakeStudentBuilder fakeStudentBuilder = new FakeStudentBuilder();
    private ManagedChannel channel;
	private CallCredentials callCredentials;
    
    @BeforeAll
    public void init() throws Exception {
    	
    	callCredentials = new BearerToken(ServiceHelpers.getBearerToken(oAuthAccessTokenEndpoint, clientId, clientSecret));
        SslContext sslContext = SslContextBuilder.forClient()
        		.sslProvider(SslProvider.OPENSSL)
        		.protocols(TLS_PROTOCOL)
        		.applicationProtocolConfig(new ApplicationProtocolConfig(Protocol.ALPN, SelectorFailureBehavior.NO_ADVERTISE, SelectedListenerFailureBehavior.ACCEPT, ALPN_PROTOCOLS))
        		.trustManager(InsecureTrustManagerFactory.INSTANCE)
        		.build();   
  	
        URL url = new URL(requiredAuthenticatedServiceUrl);
		ManagedChannel channel = NettyChannelBuilder.forAddress(url.getHost(), url.getPort()).sslContext(sslContext).negotiationType(NegotiationType.TLS).build();
        blockingStub = FakeServiceGrpc.newBlockingStub(channel);
    }

	@Test
	void createsNewFakeStudent_ThroughTo_InternalFakeGrpcService() {
		
		FakeStudent newFakeStudent = fakeStudentBuilder.withRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = CreateFakeStudentRequest.newBuilder().setFakeStudent(newFakeStudent).build();
		CreateFakeStudentResponse newFakeStudentIdResponse = blockingStub.withCallCredentials(callCredentials).createFakeStudent(newFakeStudentRequest);
		newFakeStudent = fakeStudentBuilder.withNewId(newFakeStudent, newFakeStudentIdResponse.getId());
		
		GetFakeStudentByIdRequest getNewFakeStudentIdrequest = GetFakeStudentByIdRequest.newBuilder().setId(newFakeStudentIdResponse.getId()).build();
		GetFakeStudentByIdResponse newFakeStudentResponseFromService = blockingStub.withCallCredentials(callCredentials).getFakeStudentById(getNewFakeStudentIdrequest);		
		assertEquals(newFakeStudent, newFakeStudentResponseFromService.getFakeStudent());
		
		DeleteFakeStudentByIdRequest deleteRequest = DeleteFakeStudentByIdRequest.newBuilder().setId(newFakeStudent.getFakeBasePerson().getFakeBaseEntity().getId().getValue()).build();
		blockingStub.withCallCredentials(callCredentials).deleteFakeStudentById(deleteRequest);
	}
	
	@Test
	void cantCreateDuplicateFakeStudentByIdDocument_ThroughTo_InternalFakeGrpcService() throws JsonMappingException, JsonProcessingException {
	
	    FakeStudent newFakeStudent = fakeStudentBuilder.withRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = CreateFakeStudentRequest.newBuilder().setFakeStudent(newFakeStudent).build();
		CreateFakeStudentResponse newFakeStudentIdResponse = blockingStub.withCallCredentials(callCredentials).createFakeStudent(newFakeStudentRequest);

		StatusRuntimeException ex = assertThrows(StatusRuntimeException.class, () -> {
    		FakeStudent newDuplicateFakeStudentByIdDocument = fakeStudentBuilder.withRandomValuesAndNif();
    	    newDuplicateFakeStudentByIdDocument = fakeStudentBuilder.withNewIdDocumentNumber(
    				newDuplicateFakeStudentByIdDocument, newFakeStudent.getFakeBasePerson().getFakeIdDocument().getNumber(), newFakeStudent.getFakeBasePerson().getFakeIdDocument().getType());
            CreateFakeStudentRequest newDuplicateFakeStudentByIdDocumentRequest = CreateFakeStudentRequest.newBuilder().setFakeStudent(newDuplicateFakeStudentByIdDocument).build();
        	blockingStub.withCallCredentials(callCredentials).createFakeStudent(newDuplicateFakeStudentByIdDocumentRequest);
    	});
		
	    List<ResourceDuplicateTrailer> errors  = ServiceHelpers.getResourceDuplicateErrors(ex);
	    assertEquals(Status.ALREADY_EXISTS.getCode(), ex.getStatus().getCode());
        assertTrue(errors.size() > 0);
        assertEquals(newFakeStudent.getClass().getSimpleName(), errors.get(0).getResource());
        assertTrue(errors.get(0).getProperties().containsKey("FakeIdDocument"));
        
		DeleteFakeStudentByIdRequest deleteRequest = DeleteFakeStudentByIdRequest.newBuilder().setId(newFakeStudentIdResponse.getId()).build();
		blockingStub.withCallCredentials(callCredentials).deleteFakeStudentById(deleteRequest);
	}

	@Test
	void cantValidateEmptyIdDocument_In_CreateFakeStudent_From_InternalFakeGrpcService() throws JsonMappingException, JsonProcessingException {
	    
		FakeStudent newFakeStudent = fakeStudentBuilder.withRandomValuesAndEmptyIdDocumentNumber();
        CreateFakeStudentRequest newFakeStudentRequest = CreateFakeStudentRequest.newBuilder().setFakeStudent(newFakeStudent).build();

		StatusRuntimeException ex = assertThrows(StatusRuntimeException.class, () -> {
        	blockingStub.withCallCredentials(callCredentials).createFakeStudent(newFakeStudentRequest);
    	});
		
		List<ValidationTrailer> errors  = ServiceHelpers.getValidationErrors(ex);
	    assertEquals(Status.INVALID_ARGUMENT.getCode(), ex.getStatus().getCode());
		assertTrue(errors.size() > 0);
	    String expectedPropertyName = String.format("%s.%s.%s.%s", newFakeStudent.getClass().getSimpleName(), newFakeStudent.getFakeBasePerson().getClass().getSimpleName(), newFakeStudent.getFakeBasePerson().getFakeIdDocument().getClass().getSimpleName(), "Number");
	    assertEquals(expectedPropertyName.toLowerCase(), errors.get(0).getPropertyName().toLowerCase());
	}
	
	@AfterAll  
	public void finish() throws InterruptedException {  
		
		if (channel != null)
		{
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	} 
}