/**
 * 
 */
package com.jwt.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shans
 *
 */
@Configuration
public class CorsConfiguration {
	
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/**")
				.allowedMethods(Arrays.asList("GET","POST","PUT","DELETE").toString())
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowCredentials(true);
			}
			
		};
	}

}
