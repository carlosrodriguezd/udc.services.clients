using Grpc.Core;
using Grpc.Net.Client;
using System.Net;
using System.Security.Authentication;
using Udc.Services.InternalGrpcApi.FakeClient.Providers;

namespace Udc.Services.InternalGrpcApi.FakeClient.Helpers;

public static class ServiceHelpers
{
    public static GrpcChannel CreateSimpleChannel(string address)
    {
        var channel = GrpcChannel.ForAddress(address);
        return channel;
    }

    public static GrpcChannel CreateUnauthenticatedChannel(string address)
    {
        var httpHandler = new HttpClientHandler
        {
        };

        var channel = GrpcChannel.ForAddress(address, new GrpcChannelOptions
        {
            HttpHandler = httpHandler
        });
        return channel;
    }

    public static async Task<GrpcChannel> CreateAuthenticatedChannelAsync(string address, ITokenProvider tokenProvider)
    {
        string? _token = await tokenProvider.GetTokenAsync();
        var credentials = CallCredentials.FromInterceptor((context, metadata) =>
        {
            if (!string.IsNullOrEmpty(_token))
            {
                metadata.Add("Authorization", $"Bearer {_token}");
            }
            return Task.CompletedTask;
        });

        var httpHandler = new HttpClientHandler
        {
            ServerCertificateCustomValidationCallback = HttpClientHandler.DangerousAcceptAnyServerCertificateValidator, // Return `true` to allow certificates that are untrusted/invalid
            SslProtocols = SslProtocols.Tls12 
        };

        var channel = GrpcChannel.ForAddress(address, new GrpcChannelOptions
        {
            Credentials = ChannelCredentials.Create(new SslCredentials(), credentials),
            HttpHandler = httpHandler
        });
        return channel;
    }
}
