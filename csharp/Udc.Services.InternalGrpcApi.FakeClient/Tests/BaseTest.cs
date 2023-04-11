using Grpc.Core;

namespace Udc.Services.InternalGrpcApi.FakeClient.Tests;

public class BaseTest
{
    public string RequiredAuthenticatedServiceUrl => _appSettings.RequiredAuthenticatedServiceUrl;
    public string NotRequiredAuthenticatedServiceUrl => _appSettings.NotRequiredAuthenticatedServiceUrl;
    public string OAuthServerUrl => _oauthSettings.Server;
    public string OAuthAccessTokenEndpoint => _oauthSettings.AccessTokenEndpoint;

    private readonly AppSettings _appSettings;
    private readonly OAuthSettings _oauthSettings;

    public BaseTest()
    {
        _appSettings = Startup.GetAppSettings();
        _oauthSettings = Startup.GetOAuthSettings();
    }
}