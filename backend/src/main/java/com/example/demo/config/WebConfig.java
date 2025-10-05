// Spring Boot 默认不允许 React (5173) 的跨域请求。在你的后端加一个简单的全局 CORS 配置类：
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 声明这是一个 Spring 配置类，Spring 启动时会自动扫描并加载它
@Configuration
public class WebConfig {
    
    // @Bean 告诉 Spring 这是一个需要管理的对象
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            // 这个方法专门用于配置 CORS 规则
            public void addCorsMappings(CorsRegistry registry) {
                // "/**" 表示所有路径都应用这个 CORS 配置
                registry.addMapping("/**")
                // "http://localhost:5173" 是 Vite 开发服务器的默认地址
                        .allowedOrigins(
                            "http://localhost:5173"  // 开发环境
                            // "http://new4test-frontend.s3-website.eu-north-1.amazonaws.com" // 生产环境
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

}
