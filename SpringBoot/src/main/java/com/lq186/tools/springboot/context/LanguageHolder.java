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
    FileName: LanguageHolder.java
    Date: 2019/3/5
    Author: lq
*/
package com.lq186.tools.springboot.context;

import com.lq186.tools.util.StringUtils;

public final class LanguageHolder {

    private static final String DEFAULT_LANGUAGE = "zh-CN";

    public static final void setLanguage(String language) {
        ThreadLocalHolder.THREAD_LOCAL.set(language);
    }

    public static final String getLanguage() {
        String language = ThreadLocalHolder.THREAD_LOCAL.get();
        return StringUtils.isBlank(language) ? DEFAULT_LANGUAGE : language;
    }

    static class ThreadLocalHolder {
        static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();
    }
}
