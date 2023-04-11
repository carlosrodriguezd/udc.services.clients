namespace Udc.Services.InternalGrpcApi.FakeClient;

public class AppSettings
{
    public string NotRequiredAuthenticatedServiceUrl { get; set; } = string.Empty;
    public string RequiredAuthenticatedServiceUrl { get; set; } = string.Empty;
}

public class OAuthSettings
{
    public string Server { get; set; } = string.Empty;
    public string AccessTokenEndpoint { get; set; } = string.Empty;
    public Client TestClient { get; set; } = new();

    public class Client
    {
        public string Name { get; set; } = string.Empty;
        public string Id { get; set; } = string.Empty;
        public string Secret { get; set; } = string.Empty;
    }
}
