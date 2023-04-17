package udc.services.internalgrpcapi.fakeclient.trailers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ValidationTrailer implements Serializable {

	private static final long serialVersionUID = 4806524426169419111L;
	
	private String propertyName;
	private String errorMessage;
	private String attemptedValue;
	  
	public String getPropertyName() {
		return propertyName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getAttemptedValue() {
		return attemptedValue;
	}
	
	private ValidationTrailer()
	{
	}
	  
	public ValidationTrailer(String propertyName, String errorMessage, String attemptedValue)
	{
		this.propertyName = propertyName;
	    this.errorMessage = errorMessage;
	    this.attemptedValue = attemptedValue;
	}
}
