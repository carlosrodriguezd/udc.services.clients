package udc.services.internalgrpcapi.fakeclient.builders;

import com.google.protobuf.StringValue;

import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseCenter;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseEntity;;

public class FakeCenterBuilder {
    
	private FakeBaseCenter _fakeBaseCenter;
	
    public final String TestId = "614";
    public final String TestCode = "614";
    public final String TestName = "Faculty of Computer Science";

    public FakeCenterBuilder()
    {
        _fakeBaseCenter = withDefaultValues();
    }

    public FakeBaseCenter build()
    {
        return _fakeBaseCenter;
    }

    public FakeBaseCenter withDefaultValues()
    {
    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.setId(StringValue.newBuilder().setValue(TestId))
    			.build();
    	 _fakeBaseCenter = FakeBaseCenter
        		.newBuilder()
        		.setFakeBaseEntity(fakeBaseEntity)
        		.setCode(TestCode)
        		.setName(TestName)
        		.build();
    	
        return _fakeBaseCenter;
    }
}
