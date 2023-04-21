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

public class CreateFakeStudent : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeStudentBuilder FakeStudentBuilder { get; } = new();

    public CreateFakeStudent(ITestOutputHelper output, ITokenProvider tokenProvider)
    {
        _output = output;
        _channel = Task.Run<GrpcChannel>(async () => await ServiceHelpers.CreateAuthenticatedChannelAsync(RequiredAuthenticatedServiceUrl, tokenProvider)).Result;
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task CreatesNewFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;

        GetFakeStudentByIDRequest getNewFakeStudentIDrequest = new() { Id = newFakeStudentIdResponse.Id };
        var newFakeStudentResponseFromService = await _client.GetFakeStudentByIDAsync(getNewFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(newFakeStudent, newFakeStudentResponseFromService.FakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantCreateDuplicateFakeStudentByIDDocument_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var newDuplicateFakeStudentByIDDocument = FakeStudentBuilder.WithRandomValuesAndNif();
        newDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocument = newFakeStudent.FakeBasePerson.FakeIdDocument;

        CreateFakeStudentRequest newDuplicateFakeStudentByIDDocumentRequest = new() { FakeStudent = newDuplicateFakeStudentByIDDocument };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newDuplicateFakeStudentByIDDocumentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(newDuplicateFakeStudentByIDDocument.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(newDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocument), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantCreateDuplicateFakeStudentByUsername_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;

        var newDuplicateFakeStudentByUsername = FakeStudentBuilder.WithRandomValuesAndNif();
        newDuplicateFakeStudentByUsername.Username = newFakeStudent.Username;

        CreateFakeStudentRequest newDuplicateFakeStudentByUsernameRequest = new() { FakeStudent = newDuplicateFakeStudentByUsername };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newDuplicateFakeStudentByUsernameRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(newDuplicateFakeStudentByUsername.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(newDuplicateFakeStudentByUsername.Username), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocument_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentNumber;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{newFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidIDDocument_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestInvalidFakeIDDocument;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{newFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentType_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentType;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{newFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FakeIdDocument.Type)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyFirstname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestEmptyString;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidFirstname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestInvalidFirstname;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptySurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestEmptyString;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestInvalidSurname;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSecondSurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.SecondSurname = FakeStudentBuilder.TestInvalidSecondSurname;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.SecondSurname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidUsername_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.Username = FakeStudentBuilder.TestInvalidUsername;

        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.GetType().Name}.{nameof(newFakeStudent.Username)}";
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

