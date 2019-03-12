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
    FileName: EnumUtils.java
    Date: 2019/3/12
    Author: lq
*/
package com.lq186.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class EnumUtils {

    public static final <T extends Enum<T>> T ofValue(Class<T> classOfEnum, Object value, String fieldName) {
        for (Object obj : classOfEnum.getEnumConstants()) {
            try {
                Method m = obj.getClass().getDeclaredMethod("values", null);
                Object[] results = (Object[]) m.invoke(obj, null);
                for (Object result : results) {
                    Field codeField = result.getClass().getDeclaredField(fieldName);
                    boolean isAccessible = codeField.isAccessible();
                    codeField.setAccessible(true);
                    String fileValue = String.valueOf(codeField.get(result));
                    if (fileValue.equals(value)) {
                        codeField.setAccessible(isAccessible);
                        return (T) result;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException(String.format("Can not found %s of enum[%s] %s", String.valueOf(value), fieldName, String.valueOf(classOfEnum)));
    }
}
