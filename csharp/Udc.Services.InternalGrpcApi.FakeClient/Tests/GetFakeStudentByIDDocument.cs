using Grpc.Core;
using Grpc.Net.Client;
using Udc.Services.InternalGrpcApi.FakeClient.Builders;
using Udc.Services.InternalGrpcApi.FakeClient.Extensions;
using Udc.Services.InternalGrpcApi.FakeClient.Helpers;
using Udc.Services.InternalGrpcApi.FakeClient.Providers;
using Udc.Services.Protos.Fake;
using Xunit;
using Xunit.Abstractions;

namespace Udc.Services.InternalGrpcApi.FakeClient.Tests;

public class GetFakeStudentByIdDocument : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeStudentBuilder FakeStudentBuilder { get; } = new();

    public GetFakeStudentByIdDocument(ITestOutputHelper output, ITokenProvider tokenProvider)
    {
        _output = output;
        _channel = Task.Run<GrpcChannel>(async () => await ServiceHelpers.CreateAuthenticatedChannelAsync(RequiredAuthenticatedServiceUrl, tokenProvider)).Result;
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task GetsExistingFakeStudentByIdDocument_From_InternalFakeGrpcService_Async()
    {
        var existingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest existingFakeStudentRequest = new() { FakeStudent = existingFakeStudent };
        var existingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(existingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = existingFakeStudentIdResponse.Id;
        _output.WriteLine($"ExisitingFakeStudentId: {existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");

        GetFakeStudentByIdDocumentRequest request = new() { FakeIdDocument = existingFakeStudent.FakeBasePerson.FakeIdDocument };
        var fakeStudentResponseFromService = await _client.GetFakeStudentByIdDocumentAsync(request, deadline: DateTime.UtcNow.AddMinutes(1));

        Assert.Equal<FakeStudent>(existingFakeStudent, fakeStudentResponseFromService.FakeStudent);

        DeleteFakeStudentByIdRequest deleteRequest = new() { Id = existingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIdAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantGetNotExistingFakeStudentByIdDocument_From_InternalFakeGrpcService_Async()
    {
        var fakeStudent = FakeStudentBuilder.Build();
        GetFakeStudentByIdDocumentRequest request = new() { FakeIdDocument = FakeStudentBuilder.TestNotExistingFakeIDDocument };

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.GetFakeStudentByIdDocumentAsync(request, deadline: DateTime.UtcNow.AddMinutes(1)));
        Assert.Equal(StatusCode.NotFound, ex.StatusCode);
    }

    [Fact]
    public async Task CantValidateEmptyIdDocumentNumber_In_GetFakeStudentByIDDocument_From_InternalFakeGrpcService_Async()
    {
        var fakeStudent = FakeStudentBuilder.Build(); 
        GetFakeStudentByIdDocumentRequest request = new() { FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentNumber };

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.GetFakeStudentByIdDocumentAsync(request, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{request.FakeIdDocument.GetType().Name}.{nameof(fakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidIdDocumentNumber_In_GetFakeStudentByIDDocument_From_InternalFakeGrpcService_Async()
    {
        var fakeStudent = FakeStudentBuilder.Build();
        GetFakeStudentByIdDocumentRequest request = new() { FakeIdDocument = FakeStudentBuilder.TestInvalidFakeIDDocument };

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.GetFakeStudentByIdDocumentAsync(request, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{request.FakeIdDocument.GetType().Name}.{nameof(fakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyIdDocumentType_In_GetFakeStudentByIDDocument_From_InternalFakeGrpcService_Async()
    {
        var fakeStudent = FakeStudentBuilder.Build();
        GetFakeStudentByIdDocumentRequest request = new() { FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentType };

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.GetFakeStudentByIdDocumentAsync(request, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{request.FakeIdDocument.GetType().Name}.{nameof(fakeStudent.FakeBasePerson.FakeIdDocument.Type)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    public void Dispose()
    {
        if (_channel is not null)
        {
            _channel.Dispose();
        }
    }
}
