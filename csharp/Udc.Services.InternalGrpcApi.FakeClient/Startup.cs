using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Udc.Services.InternalGrpcApi.FakeClient.Providers;

namespace Udc.Services.InternalGrpcApi.FakeClient;

public class Startup
{
    public void ConfigureServices(IServiceCollection services)
    {
        var oauthSettings = GetOAuthSettings();
        services.AddScoped<ITokenProvider, AppTokenProvider>(provider => new AppTokenProvider(oauthSettings.AccessTokenEndpoint, oauthSettings.TestClient.Id, oauthSettings.TestClient.Secret));
    }

    public static AppSettings GetAppSettings()
    {
        var configuration = new AppSettings();
        GetIConfigurationRoot().Bind("Settings", configuration);
        return configuration;
    }

    public static OAuthSettings GetOAuthSettings()
    {
        var configuration = new OAuthSettings();
        GetIConfigurationRoot().Bind("OAuth", configuration);
        return configuration;
    }

    public static IConfigurationRoot GetIConfigurationRoot()
    {
        return new ConfigurationBuilder()
            .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true)
            .AddUserSecrets("3c9491de-9a40-4d37-be96-efe6d7ef242c")
            .AddEnvironmentVariables()
            .Build();
    }
}
