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
    FileName: ResultCode.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.common.code;

public final class ResultCode {

    public static final int SUCCESS = 0;


    public static final int PARAM_INVALID = 1001;

    public static final int PARAM_EMPTY = 1002;

    public static final int PARAM_TYPE_BIND_ERROR = 1003;

    public static final int PARAM_NOT_FOUND = 1004;


    public static final int USER_NOT_LOGIN = 2001;

    public static final int USER_LOGIN_ERROR = 2002;

    public static final int USER_ACCOUNT_FORBIDDEN = 2003;

    public static final int USER_NOT_FOUND = 2004;

    public static final int USER_EXISTS = 2005;


    public static final int OPERATION_ERROR = 3001;


    public static final int DATA_NOT_FOUND = 4001;

    public static final int DATA_EXISTS = 4002;

    public static final int DATA_ERROR = 4003;


    public static final int SYSTEM_ERROR = 5001;

    public static final int FORBIDDEN = 5002;


    public static final int INTERFACE_INNER_INVOKE_ERROR = 6001;

    public static final int INTERFACE_OUTER_INVOKE_ERROR = 6002;

    public static final int INTERFACE_FORBIDDEN = 6003;

    public static final int INTERFACE_INVALID = 6004;

    public static final int INTERFACE_TIMEOUT = 6005;

}
