using Google.Protobuf.WellKnownTypes;
using Grpc.Net.Client;
using Udc.Services.InternalGrpcApi.FakeClient.Helpers;
using Udc.Services.Protos.Fake;
using Xunit.Abstractions;
using Xunit;
using Udc.Services.InternalGrpcApi.FakeClient.Builders;

namespace Udc.Services.InternalGrpcApi.FakeClient.Tests;

public class GetAllFakeBaseCenters : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeCenterBuilder FakeCenterBuilder { get; } = new();

    public GetAllFakeBaseCenters(ITestOutputHelper output)
    {
        _output = output;
        _channel = ServiceHelpers.CreateUnauthenticatedChannel(NotRequiredAuthenticatedServiceUrl);
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task GetsAllFakeBaseCenters_From_InternalFakeGrpcService_Async()
    {
        var fakeBaseCentersFromService = await _client.GetAllFakeBaseCentersAsync(new Empty(), deadline: DateTime.UtcNow.AddMinutes(1));

        var expectedFakeBaseCenter = FakeCenterBuilder.WithDefaultValues();
        Assert.Contains<FakeBaseCenter>(expectedFakeBaseCenter, fakeBaseCentersFromService.FakeBaseCenters_);
    }

    public void Dispose()
    {
        if (_channel is not null)
        {
            _channel.Dispose();
        }
    }
}
