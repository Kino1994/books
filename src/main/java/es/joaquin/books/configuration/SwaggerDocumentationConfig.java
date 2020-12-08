package es.joaquin.books.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerDocumentationConfig {
	
	 @Bean
	 public Docket api() {
		 return new Docket(DocumentationType.OAS_30)
			.apiInfo(apiInfo())
			.select()
	        .apis(RequestHandlerSelectors.basePackage("es.joaquin.books"))
	        .paths(PathSelectors.any())
	        .build();
	}

	 private ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
			.title("Books Management API")
	        .description("Books Maneagement API")
	        .version("1.0.0")
	        .build();
	}
	 
}
