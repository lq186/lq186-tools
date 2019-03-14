/*    
    Copyright ©2019 lq186.com 
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
/*
    FileName: BaseWebMvcConfiguration.java
    Date: 2019/3/7
    Author: lq
*/
package com.lq186.common.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

public abstract class BaseWebMvcConfiguration extends WebMvcConfigurationSupport {

    protected abstract ObjectMapper getObjectMapper();

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(ConfigUtils.getHttpMessageConverters(getObjectMapper()));
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/"
                        , "classpath:/frontend/static/", "classpath:/frontend/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        ConfigUtils.addMvcConversion(requestMappingHandlerAdapter);
        return requestMappingHandlerAdapter;
    }
}
