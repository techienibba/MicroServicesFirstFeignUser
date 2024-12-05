package userclientdiscovery.UserServiceClient.Config;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class feigninterceptor implements RequestInterceptor{

	private static final String AUTHORIZATION="Authorization"
;
	private static final String BEARER="Bearer";
	
	OAuth2AuthorizedClientManager manager;
	
	@Override
	public void apply(RequestTemplate template) {
		// TODO Auto-generated method stub
	String token=	manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
		
		
		template.header(AUTHORIZATION, BEARER+token);
		
		
		
	}

}
