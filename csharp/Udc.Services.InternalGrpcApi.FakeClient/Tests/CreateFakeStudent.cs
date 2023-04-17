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
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;

        GetFakeStudentByIDRequest getNewFakeStudentIDrequest = new() { Id = newFakeStudentIdResponse.Id };
        var newFakeStudentFromService = await _client.GetFakeStudentByIDAsync(getNewFakeStudentIDrequest, deadline: DateTime.UtcNow.AddMinutes(1));
        Assert.Equal<FakeStudent>(newFakeStudent, newFakeStudentFromService);

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantCreateDuplicateFakeStudentByIDDocument_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;
        var newDuplicateFakeStudentByIDDocument = FakeStudentBuilder.WithRandomValuesAndNif();
        newDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber = newFakeStudent.FakeBasePerson.IdDocumentNumber;
        newDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType = newFakeStudent.FakeBasePerson.FakeIdDocumentType;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newDuplicateFakeStudentByIDDocument, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetResourceDuplicateErrors();
        Assert.Equal(StatusCode.AlreadyExists, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ResourceDuplicateTrailer>(errors, error => Assert.Equal(newDuplicateFakeStudentByIDDocument.GetType().Name, error.Resource, ignoreCase: true));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(newDuplicateFakeStudentByIDDocument.FakeBasePerson.IdDocumentNumber), StringComparison.OrdinalIgnoreCase)));
        Assert.Single(errors.First().Properties.Keys.Where(k => k.Equals(nameof(newDuplicateFakeStudentByIDDocument.FakeBasePerson.FakeIdDocumentType), StringComparison.OrdinalIgnoreCase)));

        DeleteFakeStudentByIDRequest deleteRequest = new() { Id = newFakeStudent.FakeBasePerson.FakeBaseEntity.Id };
        await _client.DeleteFakeStudentByIDAsync(deleteRequest, deadline: DateTime.UtcNow.AddMinutes(1));
    }

    [Fact]
    public async Task CantCreateDuplicateFakeStudentByUsername_ThroughTo_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        var newFakeStudentIdResponse = await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1));
        newFakeStudent.FakeBasePerson.FakeBaseEntity.Id = newFakeStudentIdResponse.Id;

        var newDuplicateFakeStudentByUsername = FakeStudentBuilder.WithRandomValuesAndNif();
        newDuplicateFakeStudentByUsername.Username = newFakeStudent.Username;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newDuplicateFakeStudentByUsername, deadline: DateTime.UtcNow.AddMinutes(1)));

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
        newFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidIDDocument_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.IdDocumentNumber = FakeStudentBuilder.TestInvalidIDDocument;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.IdDocumentNumber)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyIDDocumentType_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FakeIdDocumentType = FakeIDDocumentType.Unspecified;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FakeIdDocumentType)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptyFirstname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidFirstname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.FirstName = FakeStudentBuilder.TestInvalidFirstname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.FirstName)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateEmptySurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestEmptyString;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.Surname = FakeStudentBuilder.TestInvalidSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.Surname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidSecondSurname_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.FakeBasePerson.SecondSurname = FakeStudentBuilder.TestInvalidSecondSurname;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        var expectedPropertyName = $"{newFakeStudent.FakeBasePerson.GetType().Name}.{nameof(newFakeStudent.FakeBasePerson.SecondSurname)}";
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(expectedPropertyName, error.PropertyName, ignoreCase: true));
    }

    [Fact]
    public async Task CantValidateInvalidUsername_In_CreateFakeStudent_From_InternalFakeGrpcService_Async()
    {
        var newFakeStudent = FakeStudentBuilder.WithRandomValuesAndNif();
        newFakeStudent.Username = FakeStudentBuilder.TestInvalidUsername;

        var ex = await Assert.ThrowsAsync<RpcException>(async () => await _client.CreateFakeStudentAsync(newFakeStudent, deadline: DateTime.UtcNow.AddMinutes(1)));

        var errors = ex.GetValidationErrors();
        Assert.Equal(StatusCode.InvalidArgument, ex.StatusCode);
        Assert.NotEmpty(errors);
        Assert.All<ValidationTrailer>(errors, error => Assert.Equal(nameof(newFakeStudent.Username), error.PropertyName, ignoreCase: true));
    }

    public void Dispose()
    {
        if (_channel is not null)
        {
            _channel.Dispose();
        }
    }
}

