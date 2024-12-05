package userclientdiscovery.UserServiceClient.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import userclientdiscovery.UserServiceClient.Config.RestTemplateInterceptor;

@Configuration
@Component
public class MainConfig {
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
	
	@Autowired
	private OAuth2AuthorizedClientManager manager;
	
	@Bean
	public RestTemplate resttemplate()
	{
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new RestTemplateInterceptor(manager));
		RestTemplate template = new RestTemplate();
		template.setInterceptors(interceptors);
		
		
		return  template;
	}
	
	
	
	
	
	@Bean
	public OAuth2AuthorizedClientManager oAuth2authorizedclientmanager(ClientRegistrationRepository clientregistrationrepository,OAuth2AuthorizedClientRepository oauth2registeredclientrepository) {
		
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		
		
		DefaultOAuth2AuthorizedClientManager defaultoauth2authorizedclientmanager = new DefaultOAuth2AuthorizedClientManager(clientregistrationrepository,oauth2registeredclientrepository);
		defaultoauth2authorizedclientmanager.setAuthorizedClientProvider(provider);
		return defaultoauth2authorizedclientmanager;
		
		
	}

}
