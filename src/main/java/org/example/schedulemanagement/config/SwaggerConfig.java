package org.example.schedulemanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        // API 기본 정보 설정
        Info info = new Info()
                .title("일정관리 API Document")
                .version("1.0")
                .description(
                        "내일배움캠프 스프링 숙련과제 일정관리 앱 Develop\n")
                .contact(new io.swagger.v3.oas.models.info.Contact().email("mxcoogi@gmail.com"));

        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))  // 추가적인 서버 URL 설정 가능
                .info(info);
    }
}
