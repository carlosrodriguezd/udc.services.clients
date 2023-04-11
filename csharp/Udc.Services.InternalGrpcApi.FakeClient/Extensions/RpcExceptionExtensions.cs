using Grpc.Core;
using System.Text;
using System.Text.Json;

namespace Udc.Services.InternalGrpcApi.FakeClient.Extensions;

public static class RpcExceptionExtensions
{
    public static IEnumerable<ValidationTrailer> GetValidationErrors(this RpcException exception)
    {
        var validationTrailer = exception.Trailers.FirstOrDefault(x => x.Key == "validation-errors");

        if (validationTrailer != null)
        {
            var validationTrailers = validationTrailer.Value.FromBase64<IEnumerable<ValidationTrailer>>();
            if (validationTrailers is not null)
            {
                return validationTrailers;
            }
        }
        return new List<ValidationTrailer>();
    }

    public static IEnumerable<ResourceDuplicateTrailer> GetResourceDuplicateErrors(this RpcException exception)
    {
        var resourceDuplicateTrailer = exception.Trailers.FirstOrDefault(x => x.Key == "resourceduplicate-errors");

        if (resourceDuplicateTrailer != null)
        {
            var resourceDuplicateTrailers = resourceDuplicateTrailer.Value.FromBase64<IEnumerable<ResourceDuplicateTrailer>>();
            if (resourceDuplicateTrailers is not null)
            {
                return resourceDuplicateTrailers;
            }
        }
        return new List<ResourceDuplicateTrailer>();
    }

}

[Serializable]
public class ValidationTrailer
{
    public string PropertyName { get; }

    public string ErrorMessage { get; }

    public string? AttemptedValue { get; }

    public ValidationTrailer(string propertyName, string errorMessage, string? attemptedValue)
    {
        PropertyName = propertyName;
        ErrorMessage = errorMessage;
        AttemptedValue = attemptedValue;
    }
}

[Serializable]
public class ResourceDuplicateTrailer
{
    public string? Resource { get; }
    public string ErrorMessage { get; }
    public Dictionary<string, string> Properties { get; } = new Dictionary<string, string>();

    public ResourceDuplicateTrailer(string? resource, string errorMessage, Dictionary<string, string> properties)
    {
        Resource = resource;
        ErrorMessage = errorMessage;
        Properties = properties;
    }
}

public static class Base64Serializer
{
    public static T? FromBase64<T>(this string base64Text)
    {
        byte[] bytes = Convert.FromBase64String(base64Text);
        string json = Encoding.Default.GetString(bytes);
        return JsonSerializer.Deserialize<T>(json);
    }
}
