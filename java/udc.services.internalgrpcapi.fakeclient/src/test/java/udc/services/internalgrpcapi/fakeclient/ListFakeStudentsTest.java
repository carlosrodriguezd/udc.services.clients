package udc.services.internalgrpcapi.fakeclient;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.protobuf.Empty;

import io.grpc.CallCredentials;
import io.grpc.ManagedChannel;
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
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc.FakeServiceBlockingStub;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListFakeStudentsTest extends BaseTest {
	
    private static final Logger logger = LogManager.getLogger(ListFakeStudentsTest.class.getName());

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
	void listsFakeStudents_From_InternalFakeGrpcService() {
		
		FakeStudent existingFakeStudent = fakeStudentBuilder.withRandomValuesAndNif();
        CreateFakeStudentRequest existingFakeStudentRequest = CreateFakeStudentRequest.newBuilder().setFakeStudent(existingFakeStudent).build();
		CreateFakeStudentResponse existingFakeStudentIdResponse = blockingStub.withCallCredentials(callCredentials).createFakeStudent(existingFakeStudentRequest);
		existingFakeStudent = fakeStudentBuilder.withNewId(existingFakeStudent, existingFakeStudentIdResponse.getId());
		logger.info(String.format("ExistingFakeStudentId: %s", existingFakeStudentIdResponse.getId()));

		ListFakeStudentsResponse fakeStudentsResponseFromService = blockingStub.withCallCredentials(callCredentials).listFakeStudents(Empty.newBuilder().build());
		
		assertTrue(fakeStudentsResponseFromService.getFakeStudentsList().contains(existingFakeStudent));
		
		DeleteFakeStudentByIdRequest deleteRequest = DeleteFakeStudentByIdRequest.newBuilder().setId(existingFakeStudent.getFakeBasePerson().getFakeBaseEntity().getId().getValue()).build();
		blockingStub.withCallCredentials(callCredentials).deleteFakeStudentById(deleteRequest);
	}
	
	@AfterAll  
	public void finish() throws InterruptedException {  
		
		if (channel != null)
		{
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	} 
}