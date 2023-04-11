namespace Udc.Services.InternalGrpcApi.FakeClient.Providers;

public interface ITokenProvider
{
    Task<string?> GetTokenAsync();
}
