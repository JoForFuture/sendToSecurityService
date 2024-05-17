
package com.giogio;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * remember annotate main class with @EnableAspectJAutoProxy
 */
public interface SecuritySender {
	
	
	/**
	 * @apiNote it sent the request to security-service for retrieve a correct range of authorization. If you wont, you can make different end-points for different roles and implements it by SecuirityFilterChain configuration
	 * @param authorization  (String token)
	 * @param sendToSecurityService  (String uri)
	 * @param webClientBuilder 
	 * @param lbFunction	(LoadBalancer for eureka client microservices)
	 * @return boolean (true if authenticated)
	 */
	default boolean verifySecurityAccess(String authorization,String sendToSecurityService, WebClient.Builder webClientBuilder,ReactorLoadBalancerExchangeFilterFunction lbFunction)
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authorization);
//		lo mando indietro al servizio di security
		
		return WebClient.builder()
					.filter(lbFunction)
					.build()
					.post()
					.uri(sendToSecurityService)
					.headers(httpHeaders->httpHeaders.addAll(headers))
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
	

	}
	@Deprecated
	default boolean verifySecurityAccess(String authorization,String sendToSecurityService, RestTemplate restTemplate)
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authorization);
//		lo mando indietro al servizio di security

		
		return 
				restTemplate.postForEntity(sendToSecurityService, headers, Boolean.class).getBody();
		
	

	}

}
