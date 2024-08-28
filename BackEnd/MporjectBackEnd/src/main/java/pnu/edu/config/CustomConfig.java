package pnu.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Nonnull;

@Configuration
public class CustomConfig implements WebMvcConfigurer {

//	@Override
//	public void addCorsMappings(@Nonnull CorsRegistry registry) {
//		registry.addMapping("/**") // 모든 주소에 대해서
//		.allowedMethods(CorsConfiguration.ALL) // 모든 Method에 대해서
//		.allowedOrigins(CorsConfiguration.ALL); // 모든 Origin에 대해서
//	}
	
	@Override
	public void addCorsMappings(@Nonnull CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods(CorsConfiguration.ALL)
				.allowedOriginPatterns("http://localhost:3000")
				.allowCredentials(true)								//login시 true로 설정해야한다 > 클라이언트가 쿠키/인증헤더를 포함하도록 허용
				.allowedHeaders(CorsConfiguration.ALL)
				.exposedHeaders(HttpHeaders.AUTHORIZATION);		// 토큰을 header에 넣어 주는 코드

	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//		.allowCredentials(true) 
//		.allowedHeaders(HttpHeaders.AUTHORIZATION)
//		.exposedHeaders(HttpHeaders.AUTHORIZATION) 
//		.allowedMethods(CorsConfiguration.ALL)
//		.allowedOriginPatterns("http://localhost:3000");
//	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/photos/**")
		.addResourceLocations("file:src/main/resources/static/photos/");
//		.addResourceLocations("classpath:/static/photos/");	//java & resource 둘다에서 찾아서 설정해준다.
	}
}
