package pnu.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
//	@Override
//	public void addCorsMappings(@Nonnull CorsRegistry registry) {
//		registry.addMapping("/login/**")
//		.allowedMethods(HttpMethod.POST.name())
//		.allowedOriginPatterns("http://localhost:3000");
//		registry.addMapping("/api/**")
//		.allowedMethods(HttpMethod.POST.name(),
//				HttpMethod.GET.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.DELETE.name())
//		.allowedOriginPatterns("http://localhost:3000");
//		registry.addMapping("/signin/**")
//		.allowedMethods(HttpMethod.POST.name(),
//				HttpMethod.GET.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.DELETE.name())
//		.allowedOriginPatterns("http://localhost:3000");
//	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowCredentials(true) 
		.allowedHeaders(HttpHeaders.AUTHORIZATION)
		.exposedHeaders(HttpHeaders.AUTHORIZATION) 
		.allowedMethods(CorsConfiguration.ALL)
		.allowedOriginPatterns("http://localhost:3000");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/photos/**")
		.addResourceLocations("file:src/main/resources/static/photos/");
//		.addResourceLocations("classpath:/static/photos/");	//java & resource 둘다에서 찾아서 설정해준다.
	}
}
