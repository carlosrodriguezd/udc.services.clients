using Udc.Services.InternalGrpcApi.FakeClient.Helpers;
using Udc.Services.Protos.Fake;

namespace Udc.Services.InternalGrpcApi.FakeClient.Builders;

public class FakeCenterBuilder
{
    private FakeBaseCenter _fakeBaseCenter;

    public string TestId => "614";
    public string TestCode => "614";
    public string TestName => "Faculty of Computer Science";

    public FakeCenterBuilder()
    {
        _fakeBaseCenter = WithDefaultValues();
    }

    public FakeBaseCenter Build()
    {
        return _fakeBaseCenter;
    }

    public FakeBaseCenter WithDefaultValues()
    {
        _fakeBaseCenter = new FakeBaseCenter()
        {
            FakeBaseEntity = new FakeBaseEntity()
            {
                Id = TestId
            },
            Code = TestCode,
            Name = TestName
        };

        return _fakeBaseCenter;
    }
}
