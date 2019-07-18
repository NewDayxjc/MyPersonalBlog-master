package com.blog.hh.personalblog.config;

import com.blog.hh.personalblog.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LoginInterceptor loginInterceptor = new LoginInterceptor();
    InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
//    loginRegistry.addPathPatterns("/article/add");
    loginRegistry.addPathPatterns("/**/add");
    loginRegistry.addPathPatterns("/**/delete");
    loginRegistry.addPathPatterns("/**/edit");
    loginRegistry.addPathPatterns("/**/update");
    loginRegistry.addPathPatterns("/upload/**");



    loginRegistry.excludePathPatterns("/**/find");
    loginRegistry.excludePathPatterns("/user/login");
  }
}
