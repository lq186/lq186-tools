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
    FileName: RestUtils.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.common.springboot.rest;

import com.lq186.common.consts.Header;
import com.lq186.common.log.Log;
import com.lq186.common.util.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

public final class RestUtils {

    private static final Log log = Log.getLog(RestUtils.class);

    public static final <T> RestResponseBody<Object> exchange(RestTemplate restTemplate, RestRequestBody requestBody, Class<T> classOfT) {
        Objects.requireNonNull(restTemplate, "restTemplate");
        HttpHeaders headers = new HttpHeaders();

        if (!StringUtils.isEmpty(requestBody.getAuthorization())) {
            headers.add(Header.AUTHORIZATION, requestBody.getAuthorization());
        }

        if (requestBody.getHeaders() != null && requestBody.getHeaders().size() > 0) {
            requestBody.getHeaders().forEach(headers::add);
        }

        // 如果没有Content-Type 则默认使用 application/json;charset=utf-8
        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }
        String url = buildParameterUrl(requestBody.getUrl(), requestBody.getParameters());
        log.info(MessageFormat.format("Request for {0}", url));

        HttpEntity<Object> entity = new HttpEntity<>(requestBody.getBody(), headers);
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, requestBody.getMethod(), entity, classOfT);
            return new RestResponseBody<>(responseEntity.getStatusCode(), responseEntity.getBody());
        } catch (HttpClientErrorException e) {
            return new RestResponseBody<>(e.getStatusCode(), e.getResponseBodyAsByteArray());
        }
    }

    public static final String buildParameterUrl(String url, Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return url;
        } else {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(url);
            String separator = (url.indexOf("?") >= 0) ? "&" : "?";
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                urlBuilder.append(separator).append(entry.getKey()).append("=").append(entry.getValue());
                separator = "&";
            }
            return urlBuilder.toString();
        }
    }
}
