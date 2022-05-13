package com.ead.authuser.configs;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//WebMvcConfigurationSupport will convert the param data to basic java types
@Configuration
public class ResolverConfig extends WebMvcConfigurationSupport {

    // HandlerMethodArgumentResolver will convert the method params to context arguments to each request
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //Argument resolver for specification
        argumentResolvers.add(new SpecificationArgumentResolver());

        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        //Argument resolver for pagination
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

}
