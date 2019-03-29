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
    FileName: RuntimeUtils.java
    Date: 2019/3/29
    Author: lq
*/
package com.lq186.common.util;

import com.lq186.common.bean.RuntimeResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class RuntimeUtils {

    public static final RuntimeResult exec(String script) throws Exception {
        Process process = Runtime.getRuntime().exec(script);
        int code = process.waitFor();
        RuntimeResult result = new RuntimeResult(code);

        if (process.getInputStream() != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                result.setOut(fromBufferedReader(bufferedReader));
            }
        }

        if (process.getErrorStream() != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                result.setError(fromBufferedReader(bufferedReader));
            }
        }

        return result;
    }

    private static final StringBuffer fromBufferedReader(BufferedReader bufferedReader) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while (null != (line = bufferedReader.readLine())) {
            stringBuffer.append(line).append("\n");
        }
        return stringBuffer;
    }
}
