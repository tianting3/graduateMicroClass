/*
package com.tt.wkkt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

*/
/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **//*

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    */
/*实现跨域*//*

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8020")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(30*1000);
    }
}
*/
