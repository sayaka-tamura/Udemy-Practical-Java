package com.course.practicaljava.common;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	// Return API info
	private ApiInfo buildApiInfo() {
		Contact contact = new Contact("Your name", "http://www.yoursite.com", "your@email.com");

		return new ApiInfo("Practical Java API", "Practical Java API description", "1.0.0", "", contact, "", "",
				Collections.emptyList());
	}

	/**
	 * @Bean // Generate swagger documentation for all end point in this project //
	 *       Put buildApiInfo(), display on only our API under package "com.course"
	 *       and sub package public Docket swaggerApi() { return new
	 *       Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
	 *       .paths(PathSelectors.any()).apis(RequestHandlerSelectors.basePackage("com.course")).build()
	 *       .apiInfo(buildApiInfo()); }
	 */

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).apis(RequestHandlerSelectors.basePackage("com.course")).build()
				.apiInfo(buildApiInfo()).useDefaultResponseMessages(false); // useDefaultResponseMessages: hide default
																			// response messag for all API
	}

	// hide try it out button for All HTTP method
	@Bean
	public UiConfiguration tryItOutDisable() {
		// "Enable try it out button" only on HTTP get
		final String[] methodsWithButtonTry = { "get" };

		return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithButtonTry).build();
	}

}
