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

public class UpdateFakeStudent : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeStudentBuilder FakeStudentBuilder { get; } = new();

    public UpdateFakeStudent(ITestOutputHelper output, ITokenProvider tokenProvider)
    {
        _output = output;
        _channel = Task.Run<GrpcChannel>(async () => await ServiceHelpers.CreateAuthenticatedChannelAsync(RequiredAuthenticatedServiceUrl, tokenProvider)).Result;
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task UpsertsExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        _output.WriteLine($"NewFakeStudentId: {newFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));

        GetFakeStudentByIDRequest getUpsertedFakeStudentIDrequest = new() { Id = upsertingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        var updatedFakeStudentResponse = await _client.GetFakeStudentByIDAsync(getUpsertedFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(upsertingFakeStudent, updatedFakeStudentResponse.FakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task UpsertsNotExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();

        UpsertFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var upsertingFakeStudentIdResponse = await _client.UpsertFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = upsertingFakeStudentIdResponse.Id;

        GetFakeStudentByIDRequest getUpsertedFakeStudentIDrequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        var updatedFakeStudentResponse = await _client.GetFakeStudentByIDAsync(getUpsertedFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(newFakeStudent, updatedFakeStudentResponse.FakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpsertDuplicateFakeStudentByIDDocument_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var upsertingDuplicateFakeStudentByIDDocument = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocument = newFakeStudent.FakeBasePerson.FakeIdDocument;

        UpsertFakeStudentRequest upsertingDuplicateFakeStudentByIDDocumentRequest = new() { FakeStudent = upsertingDuplicateFakeStudentByIDDocument };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingDuplicateFakeStudentByIDDocumentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(upsertingDuplicateFakeStudentByIDDocument.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocument), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpsertDuplicateFakeStudentByUsername_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        CreateFakeStudentRequest newFakeStudentRequest = new() { FakeStudent = newFakeStudent };
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var upsertingDuplicateFakeStudentByUsername = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingDuplicateFakeStudentByUsername.Username = newFakeStudent.Username;

        UpsertFakeStudentRequest upsertingDuplicateFakeStudentByUsernameRequest = new() { FakeStudent = upsertingDuplicateFakeStudentByUsername };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingDuplicateFakeStudentByUsernameRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(upsertingDuplicateFakeStudentByUsername.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(upsertingDuplicateFakeStudentByUsername.Username), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentNumber_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentNumber;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidIDDocumentNumber_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestInvalidFakeIDDocument;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FakeIdDocument.Number)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentType_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeIdDocument = FakeStudentBuilder.TestEmptyFakeIDDocumentType;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.FakeIdDocument.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FakeIdDocument.Type)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyFirstname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestEmptyString;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidFirstname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestInvalidFirstname;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptySurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestEmptyString;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestInvalidSurname;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSecondSurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.SecondSurname = FakeStudentBuilder.TestInvalidSecondSurname;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.SecondSurname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidUsername_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.Username = FakeStudentBuilder.TestInvalidUsername;

        UpsertFakeStudentRequest upsertingFakeStudentRequest = new() { FakeStudent = upsertingFakeStudent };
        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudentRequest, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.GetType().Name}.{nameof(upsertingFakeStudent.Username)}";
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
