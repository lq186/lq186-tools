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
    FileName: ResultBeanBuilder.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.tools.bean.builder;

import com.lq186.tools.bean.ResultBean;
import com.lq186.tools.code.ResultCode;

public final class ResultBeanBuilder {

    /**
     * 获取一个仅包含成功代码的结果实例，不能对该实例进行任何修改
     *
     * @return 一个仅包含成功代码的结果实例，非空
     */
    public static final ResultBean success() {
        return ResultBeanHolder.SUCCESS_BEAN;
    }

    public static final ResultBean success(Object data) {
        return new ResultBean(ResultCode.SUCCESS, null, data);
    }

    public static final ResultBean failed(int code, String msg) {
        return new ResultBean(code, msg);
    }

}

class ResultBeanHolder {

    static final ResultBean SUCCESS_BEAN = new ResultBean(ResultCode.SUCCESS);

}