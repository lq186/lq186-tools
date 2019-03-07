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
    FileName: PageRequestInterceptor.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.common.springboot.interceptor;

import com.lq186.common.consts.Parameter;
import com.lq186.common.pageable.PageRequestHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String page = request.getParameter(Parameter.PAGE);
        String size = request.getParameter(Parameter.SIZE);
        String sort = request.getParameter(Parameter.SORT);
        String direction = request.getParameter(Parameter.DIRECTION);
        PageRequestHolder.setPageRequest(page, size, sort, direction);
        return true;
    }
}
