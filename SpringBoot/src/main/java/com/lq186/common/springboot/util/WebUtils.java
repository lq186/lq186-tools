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
    FileName: WebUtils.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.common.springboot.util;

import com.lq186.common.log.Log;
import com.lq186.common.util.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.MessageFormat;

public final class WebUtils {

    private static final Log log = Log.getLog(WebUtils.class);

    public static final void writeJsonUtf8String(HttpServletResponse response, String content) {
        addJsonUtf8Header(response);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            log.error(MessageFormat.format("Write data [{0}] response error.", content), e);
        }
    }

    public static final void addJsonUtf8Header(HttpServletResponse response) {
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    public static final void addAllowOrigin(HttpServletResponse response, String origin, String... headers) {
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Headers", StringUtils.join(headers));
    }

    public static final boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS == HttpMethod.resolve(request.getMethod());
    }

}
