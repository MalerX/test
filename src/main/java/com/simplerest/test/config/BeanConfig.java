package com.simplerest.test.config;

import com.simplerest.test.service.AuthenticationFacade;
import com.simplerest.test.service.JWTFilter;
import com.simplerest.test.service.JWTService;
import com.simplerest.test.service.impl.AuthenticationFacadeImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class BeanConfig {
    @Value("${api.version}")
    private String version_api;

    private final JWTService jwtService;

//    Бин фильтра создаю вручную, чтобы он не попадал в контекст при запросах к общедоступным эндпоинтам.
    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(jwtService);
    }

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Документация REST API сервиса сообщений.")
                        .version(version_api)
                        .contact(new Contact()
                                .email("malleerx@gmail.com")
                                .url("https://github.com/MalerX/test")
                        )
                );
    }
}
