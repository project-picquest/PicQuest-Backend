package com.bvm.picquest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().components(new Components()).info(info());
    }

    private Info info() {
        return new Info().title("PicQuest").description("PicQuest API").version("1.0");
    }
}
