package com.giogio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * remember annotate main class with @EnableAspectJAutoProxy
 * @apiNote authorization : token
 * @apiNote securityEndpointService :default location is http://security-service/securityControl/accessPoint, you must implement it in your security service. Will authenticate all services by only one security-service. 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ToMyCustomSecurityService {
	
	/**
	 * The Json Web Token 
	 * default empty String
	 * @return emptyString or insert value
	 */
	public String authorization() default  "";
	
	/**
	 * @apiNote default location is http://security-service/securityControl/accessPoint, you must implement it in your security service
	 * @apiNote endpoint of security service for authenticate all services by only one security-service. 
	 * @return
	 */
	public String securityEndpointService() default "http://security-service/securityControl/accessPoint";

}
