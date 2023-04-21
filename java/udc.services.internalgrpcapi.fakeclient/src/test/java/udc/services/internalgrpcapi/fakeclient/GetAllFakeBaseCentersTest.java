package udc.services.internalgrpcapi.fakeclient;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.protobuf.Empty;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import udc.services.internalgrpcapi.fakeclient.builders.FakeCenterBuilder;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseCenter;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetAllFakeBaseCentersResponse;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc;
import udc.services.internalgrpcapi.fakeclient.protos.fake.FakeServiceGrpc.FakeServiceBlockingStub;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetAllFakeBaseCentersTest extends BaseTest {
	
    private static final Logger logger = LogManager.getLogger(GetAllFakeBaseCentersTest.class.getName());

	private FakeServiceBlockingStub blockingStub;
    private FakeCenterBuilder fakeCenterBuilder = new FakeCenterBuilder();
    private ManagedChannel channel;
    
    @BeforeAll
    public void init() throws MalformedURLException {
        
        URL url = new URL(notRequiredAuthenticatedServiceUrl);
        channel = Grpc.newChannelBuilderForAddress(url.getHost(), url.getPort(), InsecureChannelCredentials.create()).build();
        blockingStub = FakeServiceGrpc.newBlockingStub(channel);
    }
    
	@Test
	void getsAllFakeBaseCenters_From_InternalFakeGrpcService() {
        
		GetAllFakeBaseCentersResponse fakeBaseCentersResponseFromService = blockingStub.getAllFakeBaseCenters(Empty.newBuilder().build());
		
		FakeBaseCenter fakeBaseCenter = fakeCenterBuilder.withDefaultValues();
		assertTrue(fakeBaseCentersResponseFromService.getFakeBaseCentersList().contains(fakeBaseCenter));
	}

	@AfterAll  
	public void finish() throws InterruptedException {  
		
		if (channel != null)
		{
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	} 
}
