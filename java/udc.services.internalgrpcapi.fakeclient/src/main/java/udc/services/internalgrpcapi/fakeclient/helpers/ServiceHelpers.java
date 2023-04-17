package udc.services.internalgrpcapi.fakeclient.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import udc.services.internalgrpcapi.fakeclient.trailers.ResourceDuplicateTrailer;
import udc.services.internalgrpcapi.fakeclient.trailers.ValidationTrailer;

public class ServiceHelpers {

    private static final Logger logger = LogManager.getLogger(ServiceHelpers.class.getName());
    
	private static String strPattern = "access_token=([a-z0-9\\-]*)\\&.*";
	
	public static String getBearerToken(String oAuthServer, String clientId, String clientSecret) throws Exception {
		
		String token = null;
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					  .GET()
					  .uri(new URI(String.format("%saccessToken?grant_type=client_credentials&client_id=%s&client_secret=%s", oAuthServer, clientId, clientSecret)))
					  .build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() != 200) {
	        	logger.error(String.format("Error getting access token. Response status code: %s", response.statusCode()));
	        	throw new IOException(String.format("Error getting access token. Response status code: %s", response.statusCode()));
			}
			
	        Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(response.body());
	        if (matcher.matches())
	        {
	        	token = matcher.group(1);
	        }
	        else
	        {
	        	logger.error("Ivalid bearer token.");
	        	throw new IOException("Ivalid bearer token");
	        }
		} catch (IOException | URISyntaxException  | InterruptedException ex) {
        	logger.error(String.format("Error getting access token. %s", ex.getMessage()));
        	throw ex;
		}
		
	    return token;
	}
	
    public static  List<ValidationTrailer> fromBase64ToValidationTrailers(String base64Text) throws JsonMappingException, JsonProcessingException
    {
    	byte[] bytes = Base64.getDecoder().decode(base64Text);
    	String json = new String(bytes);
    	return new ObjectMapper().readValue(json, new TypeReference<List<ValidationTrailer>>(){});
    }

    public static  List<ResourceDuplicateTrailer> fromBase64ToResourceDuplicateTrailers(String base64Text) throws JsonMappingException, JsonProcessingException
    {
    	byte[] bytes = Base64.getDecoder().decode(base64Text);
    	String json = new String(bytes);
    	return new ObjectMapper().readValue(json, new TypeReference<List<ResourceDuplicateTrailer>>(){});
    }
	
    public static List<ValidationTrailer> getValidationErrors(StatusRuntimeException exception) throws JsonMappingException, JsonProcessingException
	{
	    String validationTrailer = exception.getTrailers().get(Metadata.Key.of("validation-errors", Metadata.ASCII_STRING_MARSHALLER));
		if (validationTrailer != null)
		{
			List<ValidationTrailer> validationTrailers = fromBase64ToValidationTrailers(validationTrailer);
            if (validationTrailers != null)
            {
                return validationTrailers;
            }
		}
		
		return new ArrayList<ValidationTrailer>();
	}
	
	public static List<ResourceDuplicateTrailer> getResourceDuplicateErrors(StatusRuntimeException exception) throws JsonMappingException, JsonProcessingException
	{
	    String resourceDuplicateTrailer = exception.getTrailers().get(Metadata.Key.of("resourceduplicate-errors", Metadata.ASCII_STRING_MARSHALLER));
		if (resourceDuplicateTrailer != null)
		{
			List<ResourceDuplicateTrailer> resourceDuplicateTrailers = fromBase64ToResourceDuplicateTrailers(resourceDuplicateTrailer);
            if (resourceDuplicateTrailers != null)
            {
                return resourceDuplicateTrailers;
            }
		}
		
		return new ArrayList<ResourceDuplicateTrailer>();
	}
}
