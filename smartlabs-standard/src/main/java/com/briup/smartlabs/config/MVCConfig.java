package com.briup.smartlabs.config;

import com.briup.smartlabs.web.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//jwt及权限拦截 配置
@Configuration
public class MVCConfig implements WebMvcConfigurer {
	//@Autowired
	//LoggingInterceptor loggingInterceptor;

	//jwt认证拦截
	@Autowired
	private AuthInterceptor authInterceptor;

	//权限拦截
//	@Autowired
//	private AuthorizationInterceptor authorizationInterceptor;

	//跨域设置
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
		FilterRegistrationBean bean =
				new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
//		registry.addInterceptor(loggingInterceptor)
//				.addPathPatterns("/**") //设置拦截的路径
//				.excludePathPatterns(
//						"/swagger-resources/**","/error",
//						"/swagger-ui.html","/v2/api-docs",
//						"/webjar/**");//设置不拦截的路径
//		System.out.println("authInterceptor..."+authInterceptor);

		//jwt拦截配置
//		registry.addInterceptor(authInterceptor)
//				.addPathPatterns("/device/**","/sys/**","/menu/**",
//						"/deviceType/**","/labs/**",
//						"/syslog/**","/auth/**","/user/**","/role/**");

		//权限拦截配置
//		registry.addInterceptor(authorizationInterceptor)
//				.addPathPatterns("/device/**","/sys/**",
//						"/deviceType/**","/labs/**",
//						"/syslog/**","/user/**","/role/**");
	}
}


