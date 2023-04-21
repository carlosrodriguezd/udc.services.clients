using Google.Protobuf.WellKnownTypes;
using Grpc.Net.Client;
using Udc.Services.InternalGrpcApi.FakeClient.Builders;
using Udc.Services.InternalGrpcApi.FakeClient.Helpers;
using Udc.Services.InternalGrpcApi.FakeClient.Providers;
using Udc.Services.Protos.Fake;
using Xunit.Abstractions;
using Xunit;

namespace Udc.Services.InternalGrpcApi.FakeClient.Tests;

public class GetAllFakeStudents : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeStudentBuilder FakeStudentBuilder { get; } = new();

    public GetAllFakeStudents(ITestOutputHelper output, ITokenProvider tokenProvider)
    {
        _output = output;
        _channel = Task.Run<GrpcChannel>(async () => await ServiceHelpers.CreateAuthenticatedChannelAsync(RequiredAuthenticatedServiceUrl, tokenProvider)).Result;
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task GetsAllFakeStudents_From_InternalFakeGrpcService_Async()
    {
        var existingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest existingFakeStudentRequest = new() { FakeStudent = existingFakeStudent };
        var existingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(existingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = existingFakeStudentIdResponse.Id;
        _output.WriteLine($"ExistingFakeStudentId: {existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");

        var fakeStudentsResponseFromService = await _client.GetAllFakeStudentsAsync(new Empty(), deadline: DateTime.UtcNow.AddMinutes(1));

        Assert.Contains<FakeStudent>(existingFakeStudent, fakeStudentsResponseFromService.FakeStudents);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    public void Dispose()
    {
        if (_channel is not null)
        {
            _channel.Dispose();
        }
    }
}
