using IdentityModel.Client;
using System.Text.RegularExpressions;

namespace Udc.Services.InternalGrpcApi.FakeClient.Providers;

public class AppTokenProvider : ITokenProvider
{
    private string? _token;
    private readonly string _endpoint;
    private readonly string _clientId;
    private readonly string _clientSecret;

    public AppTokenProvider(string endpoint, string clientId, string clientSecret)
    {
        _endpoint = endpoint;
        _clientId = clientId;
        _clientSecret = clientSecret;
    }

    public Task<string?> GetTokenAsync()
    {
        if (_token is null)
        {
            var client = new HttpClient();
            var request = new ClientCredentialsTokenRequest
            {
                Address = $"{_endpoint.Trim('/')}?grant_type=client_credentials&client_id={_clientId}&client_secret={_clientSecret}"
            };

            var tokenResponse = Task.Run(async () => await client.RequestClientCredentialsTokenAsync(request)).Result;
            if (tokenResponse.IsError)
            {
            }

            string pattern = @"access_token=([a-z0-9\-]*)\&\.*";
            Match m = Regex.Match(tokenResponse.Raw, pattern, RegexOptions.IgnoreCase);
            if (m.Success)
            {
                _token = m.Groups[1].Value;
            }
        }

        return Task.FromResult(_token);
    }
}

