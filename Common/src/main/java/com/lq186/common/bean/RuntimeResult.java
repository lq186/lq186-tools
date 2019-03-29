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
    FileName: RuntimeResult.java
    Date: 2019/3/29
    Author: lq
*/
package com.lq186.common.bean;

import java.io.Serializable;

public final class RuntimeResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private StringBuffer out;

    private StringBuffer error;

    public RuntimeResult() {

    }

    public RuntimeResult(int code) {
        this.code = code;
    }

    public RuntimeResult(int code, StringBuffer out, StringBuffer error) {
        this.code = code;
        this.out = out;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StringBuffer getOut() {
        return out;
    }

    public void setOut(StringBuffer out) {
        this.out = out;
    }

    public StringBuffer getError() {
        return error;
    }

    public void setError(StringBuffer error) {
        this.error = error;
    }
}
