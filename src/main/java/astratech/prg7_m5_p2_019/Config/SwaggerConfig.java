package astratech.prg7_m5_p2_019.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@Configuration
@EnableJpaRepositories(basePackages = "astratech.prg7_m5_p2_019")
@ComponentScan(basePackages = {"astratech.prg7_m5_p2_019"})

public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo())
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Swagger with Spring Boot + Security")
                .version("1.0.0")
                .description("Your Description")
                .contact(new Contact("Dito Modovan Junian", "https://www.facebook.com/dito.mj.5", "ditomjdxd22@gmail.com"))
                .build();
    }

/*    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Spring boot Swagger 0320210019",
                "Praktikum Swagger Manajemen Informatika: Kumpulan API Praktikum M5_2 Helm",
                "1.0",
                "Terms of Service",
                "Dito Modovan Junian",
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
        return apiInfo;
    }*/

    //Encrypt

/*  Password
    nomor HP
    KTP*/

    /*Nama lengkap
    * Alamat
    * Username*/
}
