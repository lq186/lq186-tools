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
    FileName: FileUtils.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.tools.util;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.Consumer;

public final class FileUtils {

    public static final void copy(File srcFile, File destFile) throws IOException {

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(destFile)) {
            try (FileInputStream fileInputStream = new FileInputStream(srcFile)) {
                FileChannel inputChannel = fileInputStream.getChannel();
                MappedByteBuffer mappedByteBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());
                FileChannel outputChannel = fileOutputStream.getChannel();
                outputChannel.write(mappedByteBuffer);
            }
        }

    }

    public static final void doWithLine(String filePath, Consumer<String> consumer) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception(StringUtils.join("找不到文件：", filePath));
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            for(String line; (line = bufferedReader.readLine()) != null; ) {
                consumer.accept(line);
            }
        }
    }

    public static void makeDir(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
    }
}
