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
    FileName: UserInfoHolder.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.tools.springboot.context;

import com.lq186.tools.bean.UserInfo;

public final class UserInfoHolder {

    public static final void setUserInfo(UserInfo userInfo) {
        ThreadLocalHolder.THREAD_LOCAL.set(userInfo);
    }

    public static final UserInfo getUserInfo() {
        return ThreadLocalHolder.THREAD_LOCAL.get();
    }

    static class ThreadLocalHolder {
        static final InheritableThreadLocal<UserInfo> THREAD_LOCAL = new InheritableThreadLocal<>();
    }

}
