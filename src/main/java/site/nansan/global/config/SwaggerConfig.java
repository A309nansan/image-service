package site.nansan.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("X-User-Id",
                                new SecurityScheme()
                                        .name("X-User-Id")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("X-User-Nickname",
                                new SecurityScheme()
                                        .name("X-User-Nickname")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("X-User-Role",
                                new SecurityScheme()
                                        .name("X-User-Role")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER))
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("X-User-Id")
                        .addList("X-User-Nickname")
                        .addList("X-User-Role")
                )
                # Service에 맞게 설정 값 변경 필요
                .info(new Info()
                                .title("Nansan: User Service API")
                                .description("유저 관련 기능 API")
                                .version("v1")
                );
    }
}
