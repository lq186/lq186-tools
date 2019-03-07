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
    FileName: ConfigUtils.java
    Date: 2019/3/7
    Author: lq
*/
package com.lq186.tools.springboot.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.lq186.tools.pattern.DatePattern;
import com.lq186.tools.springboot.redis.RedisUtils;
import com.lq186.tools.util.DateUtils;
import com.lq186.tools.util.StringUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public final class ConfigUtils {

    public static final void addMvcConversion(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (null != initializer && null != initializer.getConversionService()) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new DateConverter());
            genericConversionService.addConverter(new LocalDateTimeConverter());
            genericConversionService.addConverter(new LocalDateConverter());
            genericConversionService.addConverter(new LocalTimeConverter());
        }
    }

    public static final ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.DATETIME)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.DATE)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.TIME)));
        javaTimeModule.addSerializer(Date.class, new DateSerializer(true, new SimpleDateFormat(DatePattern.DATETIME)));

        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.DATETIME)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.DATE)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.TIME)));
        DateDeserializers.DateDeserializer dateDeserializer = new DateDeserializers.DateDeserializer();
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer(dateDeserializer, new SimpleDateFormat(DatePattern.DATETIME), DatePattern.DATETIME));

        objectMapper.registerModule(javaTimeModule);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    public static final RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(getObjectMapper());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer); // key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer); // hash的key也采用String的序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer); // value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer); // hash的value序列化方式采用jackson
        template.afterPropertiesSet();
        RedisUtils.setRedisTemplate(template);
        return template;
    }

    public static final List<HttpMessageConverter<?>> getHttpMessageConverters(ObjectMapper objectMapper) {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(mappingJackson2HttpMessageConverter);
        return converters;
    }

    public static final JavaMailSenderImpl getJavaMailSenderImpl(String host, int port, String username, String password, String encoding, long timeout, boolean auth, boolean enableStarttls) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding(encoding);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", auth);
        javaMailProperties.put("mail.smtp.starttls.enable", enableStarttls);
        javaMailProperties.put("mail.smtp.timeout", timeout);
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    public static final void sendMail(JavaMailSenderImpl javaMailSender, String from, String fromAlias, String to, String subject, boolean isHtml, String content) {
        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        messageHelper.setText(content, isHtml);
        try {
            messageHelper.setFrom(from, fromAlias);
        } catch (Exception e) {
            messageHelper.setFrom(from);
        }
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

}

class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = StringUtils.trim(source);
        if (source.length() == 19) {
            return DateUtils.dateFromString(source, DatePattern.DATETIME);
        } else if (source.length() == 10) {
            return DateUtils.dateFromString(source, DatePattern.DATE);
        }
        return null;
    }
}

class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = StringUtils.trim(source);
        return DateUtils.localDateTimeFromString(source, DatePattern.DATETIME);
    }
}

class LocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = StringUtils.trim(source);
        return DateUtils.localDateFromString(source, DatePattern.DATE);
    }
}

class LocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = StringUtils.trim(source);
        return DateUtils.localTimeFromString(source, DatePattern.TIME);
    }
}
