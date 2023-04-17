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
    public async Task UpdatesExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        _output.WriteLine($"NewFakeStudentId: {newFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id;

        await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));

        GetFakeStudentByIDRequest getUpdatedFakeStudentIDrequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        var updatedFakeStudent = await _client.GetFakeStudentByIDAsync(getUpdatedFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(updatingFakeStudent, updatedFakeStudent);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpdateNotExistingFakeStudent_ThroughTo_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = FakeStudentBuilder.TestNotExistingId;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        Assert.Equal(StatusCode.NotFound, ex.StatusCode);
    }

    [Fact]
    public async Task CantUpdateDuplicateFakeStudentByIDDocument_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        _output.WriteLine($"NewFakeStudentId: {newFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");
        var updatingDuplicateFakeStudentByIDDocument = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingDuplicateFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingDuplicateFakeStudentByIDDocument, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber = newFakeStudent.FakeBasePerson.IdDocumentNumber;
        updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType = newFakeStudent.FakeBasePerson.FakeIdDocumentType;
        updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeBaseEntity.Id = updatingDuplicateFakeStudentIdResponse.Id;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingDuplicateFakeStudentByIDDocument, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);

        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(updatingDuplicateFakeStudentByIDDocument.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber), StringComparison.OrdinalIgnoreCase)));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        deleteRequest = new() { Id = updatingDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantUpdateDuplicateFakeStudentByUsername_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        _output.WriteLine($"NewFakeStudentId: {newFakeStudent.FakeBasePerson.FakeBaseEntity.Id}");
        var updatingDuplicateFakeStudentByUsername = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingDuplicateFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingDuplicateFakeStudentByUsername, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingDuplicateFakeStudentByUsername.Username = newFakeStudent.Username;
        updatingDuplicateFakeStudentByUsername.FakeBasePerson.FakeBaseEntity.Id = updatingDuplicateFakeStudentIdResponse.Id;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingDuplicateFakeStudentByUsername, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(updatingDuplicateFakeStudentByUsername.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(updatingDuplicateFakeStudentByUsername.Username), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
        deleteRequest = new() { Id = updatingDuplicateFakeStudentByUsername.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentNumber_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateInvalidIDDocumentNumber_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestInvalidIDDocument;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentType_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.FakeIdDocumentType = FakeIDDocumentType.Unspecified;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.FakeIdDocumentType)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptyFirstname_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateInvalidFirstname_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestInvalidFirstname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateEmptySurname_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateInvalidSurname_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestInvalidSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantValidateInvalidSecondSurname_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.FakeBasePerson.SecondSurname = FakeStudentBuilder.TestInvalidSecondSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{updatingFakeStudent.FakeBasePerson.GetType().Name}.{nameof(updatingFakeStudent.FakeBasePerson.SecondSurname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }
    [Fact]
    public async Task CantValidateInvalidUsername_In_UpdateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var updatingFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var updatingFakeStudentIdResponse = await _client.CreateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id = updatingFakeStudentIdResponse.Id;
        updatingFakeStudent.Username = FakeStudentBuilder.TestInvalidUsername;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.UpdateFakeStudentAsync(updatingFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(nameof(updatingFakeStudent.Username), error.PropertyName, ignoreCase: true));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = updatingFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
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
