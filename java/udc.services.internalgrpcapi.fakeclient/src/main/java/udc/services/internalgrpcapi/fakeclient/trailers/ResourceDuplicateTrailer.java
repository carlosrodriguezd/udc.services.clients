package udc.services.internalgrpcapi.fakeclient.trailers;

import java.io.Serializable;
import java.util.Hashtable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ResourceDuplicateTrailer implements Serializable {

	private static final long serialVersionUID = -895855709174840832L;
	
	private String resource;
	private String errorMessage;
	private Hashtable<String, String> properties = new Hashtable<String, String>();
	  
	public String getResource() {
		return resource;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}
	
	private ResourceDuplicateTrailer()
	{
	}
	  
	public ResourceDuplicateTrailer(String resource, String errorMessage, Hashtable<String, String> properties)
	{
		this.resource = resource;
	    this.errorMessage = errorMessage;
	    this.properties = properties;
	}
}
