package udc.services.internalgrpcapi.fakeclient;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;

import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolNames;

public abstract class BaseTest {
    
    protected static String requiredAuthenticatedServiceUrl;
    protected static String notRequiredAuthenticatedServiceUrl;
    protected static String oAuthServerUrl;
    protected static String oAuthAccessTokenEndpoint;
    protected static String clientId;
    protected static String clientSecret;
    
    protected static final String[] ALPN_PROTOCOLS = new String[] {ApplicationProtocolNames.HTTP_2}; // Names as per ALPN spec
	protected static final String TLS_PROTOCOL = "TLSv1.2";
   
    @BeforeAll
    static void baseTestSetup() throws IOException {
		Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("settings.properties");
        properties.load(url.openStream());
        
        requiredAuthenticatedServiceUrl = properties.getProperty("settings.requiredauthenticatedserviceurl");
        notRequiredAuthenticatedServiceUrl = properties.getProperty("settings.notrequiredauthenticatedserviceurl");
        oAuthServerUrl = properties.getProperty("oauth.server");
        oAuthAccessTokenEndpoint = properties.getProperty("oauth.accesstokenendpoint");
        clientId = properties.getProperty("oauth.client.id");
        clientSecret = properties.getProperty("oauth.client.secret");
    }
}
