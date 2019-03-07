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
    FileName: RestRequestBody.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.common.springboot.rest;

import com.lq186.common.util.StringUtils;
import org.springframework.http.HttpMethod;

import java.util.LinkedHashMap;
import java.util.Map;

public final class RestRequestBody {

    private HttpMethod method;

    private String authorization;

    private String url;

    private Map<String, String> parameters = new LinkedHashMap<>();

    private Map<String, String> headers = new LinkedHashMap<>();

    private String body;

    public HttpMethod getMethod() {
        return method;
    }

    public RestRequestBody setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public String getAuthorization() {
        return authorization;
    }

    public RestRequestBody setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RestRequestBody setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public RestRequestBody setParameters(Map<String, String> parameters) {
        if (null != parameters) {
            this.parameters = parameters;
        }
        return this;
    }

    public RestRequestBody addParameters(String paramName, String paramValue) {
        if (StringUtils.isNotBlank(paramName)) {
            this.parameters.put(paramName, paramValue);
        }
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RestRequestBody setHeaders(Map<String, String> headers) {
        if (null != headers) {
            this.headers = headers;
        }
        return this;
    }

    public RestRequestBody addHeader(String headerName, String headerValue) {
        if (StringUtils.isNotBlank(headerName)) {
            this.headers.put(headerName, headerValue);
        }
        return this;
    }

    public String getBody() {
        return body;
    }

    public RestRequestBody setBody(String body) {
        this.body = body;
        return this;
    }
}
