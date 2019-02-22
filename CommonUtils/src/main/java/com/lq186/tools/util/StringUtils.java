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
    FileName: StringUtils.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.tools.util;

public final class StringUtils {

    public static final String join(String ... strings) {
        if (null != strings && strings.length > 0) {
            if (strings.length == 1) {
                return strings[0];
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (String s : strings) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }

}
