package iampotato.iampotato.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String CORS_URL_PATTERN = "/**";

    private static final String CORS_URL = "*";
    private static final String CORS_METHOD = "*";

    /**
     * CORS 문제를 위한 글로벌 설정함수 입니다.
     * 프론트엔드 서버 만들면 보안상 수정 필요합니다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CORS_URL_PATTERN)
                .allowedOrigins(CORS_URL)
                .allowedMethods(CORS_METHOD);
    }
}
