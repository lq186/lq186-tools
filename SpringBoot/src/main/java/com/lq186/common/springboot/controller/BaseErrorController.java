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
    FileName: BaseErrorController.java
    Date: 2019/3/8
    Author: lq
*/
package com.lq186.common.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq186.common.bean.ResultBean;
import com.lq186.common.consts.Attribute;
import com.lq186.common.consts.Parameter;
import com.lq186.common.i18n.MessageKey;
import com.lq186.common.log.Log;
import com.lq186.common.util.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class BaseErrorController implements ErrorController {

    private static final Log log = Log.getLog(BaseErrorController.class);

    protected static final String ERROR_PATH = "/error";

    protected abstract ErrorAttributes getErrorAttributes();

    protected abstract ObjectMapper getObjectMapper();

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    public ResultBean handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(Attribute.STATUS_CODE);
        if (null == statusCode) {
            statusCode = 500;
        }
        ResultBean resultBean = new ResultBean(statusCode);
        resultBean.setUrl(request.getRequestURL().toString());
        resultBean.setMsg(getErrorMsg(request));
        return resultBean;
    }

    private boolean enableTrace(HttpServletRequest request) {
        String parameter = request.getParameter(Parameter.TRACE);
        if (StringUtils.isBlank(parameter)) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private String getErrorMsg(HttpServletRequest request) {
        boolean includeStackTrace = enableTrace(request);
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Map<String, Object> errorMap = getErrorAttributes().getErrorAttributes(servletWebRequest, includeStackTrace);
        String errorMsg = MessageKey.SYSTEM_ERROR;
        try {
            errorMsg = getObjectMapper().writeValueAsString(errorMap);
        } catch (Exception e) {
            log.error("错误信息转JSON字符串异常。", e);
        }
        return errorMsg;
    }

}
