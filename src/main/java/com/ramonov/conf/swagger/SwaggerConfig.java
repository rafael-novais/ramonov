package com.ramonov.conf.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.info.BuildProperties;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo(BuildProperties buildProperties) {
        String version = (buildProperties != null) ? buildProperties.getVersion() : "dev";
        return new OpenAPI()
                .info(new Info()
                        .title("RAMONOV API")
                        .description("Documentação da API RAMONOV")
                        .version(version));
    }
}
