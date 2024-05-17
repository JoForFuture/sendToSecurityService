package com.giogio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AuthenticationFromExternalSecurityServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationFromExternalSecurityServiceApplication.class, args);
	}
	
	
	
	@ToMyCustomSecurityService(authorization="token-for-authorization",securityEndpointService="your security-service endpoint")
	@GetMapping("/sendWithoutAnySpecification")
	public String method1()
	{
		/* 
		 * the request will be redirect to a security service that will secure it like an internal end-point by securityFilterChain 
		 */
		return "success";
	}
	
	
	

}
