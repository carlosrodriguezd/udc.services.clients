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

public class UpsertFakeStudent : BaseTest, IDisposable
{
    private readonly GrpcChannel _channel;
    private readonly FakeService.FakeServiceClient _client;
    private readonly ITestOutputHelper _output;
    private FakeStudentBuilder FakeStudentBuilder { get; } = new();

    public UpsertFakeStudent(ITestOutputHelper output, ITokenProvider tokenProvider)
    {
        _output = output;
        _channel = Task.Run<GrpcChannel>(async () => await ServiceHelper.CreateAuthenticatedChannelAsync(RequiredAuthenticatedServiceUrl, tokenProvider)).Result;
        _client = new FakeService.FakeServiceClient(_channel);
    }

    [Fact]
    public async Task UpsertsExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        _output.WriteLine($"NewFakeStudentId: {newFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id;

        await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));

        GetFakeStudentByIDRequest getUpsertedFakeStudentIDrequest = new() { Id = upsertingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        var updatedFakeStudent = await _client.GetFakeStudentByIDAsync(getUpsertedFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(upsertingFakeStudent, updatedFakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task UpsertsNotExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();

        var upsertingFakeStudentIdResponse = await _client.UpsertFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = upsertingFakeStudentIdResponse.Id;

        GetFakeStudentByIDRequest getUpsertedFakeStudentIDrequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        var updatedFakeStudent = await _client.GetFakeStudentByIDAsync(getUpsertedFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(newFakeStudent, updatedFakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpsertDuplicateFakeStudentByIDDocument_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var upsertingDuplicateFakeStudentByIDDocument = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber = newFakeStudent.FakeBasePerson.IdDocumentNumber;
        upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType = newFakeStudent.FakeBasePerson.FakeIdDocumentType;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingDuplicateFakeStudentByIDDocument, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(upsertingDuplicateFakeStudentByIDDocument.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber), StringComparison.OrdinalIgnoreCase)));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(upsertingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpsertDuplicateFakeStudentByUsername_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var upsertingDuplicateFakeStudentByUsername = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingDuplicateFakeStudentByUsername.Username = newFakeStudent.Username;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingDuplicateFakeStudentByUsername, deadline: DateTime.UtcNow.AddMinutes(1)));

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
        upsertingFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidIDDocumentNumber_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestInvalidIDDocument;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentType_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FakeIdDocumentType = FakeIDDocumentType.Unspecified;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FakeIdDocumentType)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyFirstname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidFirstname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestInvalidFirstname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptySurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestInvalidSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSecondSurname_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.FakeBasePerson.SecondSurname = FakeStudentBuilder.TestInvalidSecondSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{upsertingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(upsertingFakeStudent.FakeBasePerson.SecondSurname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidUsername_In_UpsertFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var upsertingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        upsertingFakeStudent.Username = FakeStudentBuilder.TestInvalidUsername;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpsertFakeStudentAsync(upsertingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(nameof(upsertingFakeStudent.Username), error.PropertyName, ignoreCase: true));
    }

    public void Dispose()
    {
        if (_channel is not null)
        {
            _channel.Dispose();
        }
    }
}
