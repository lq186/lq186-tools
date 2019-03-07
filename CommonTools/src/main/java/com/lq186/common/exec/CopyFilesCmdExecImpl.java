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
    FileName: CopyFilesCmdExecImpl.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.common.exec;

import com.lq186.common.out.Print;
import com.lq186.common.util.FileUtils;

import java.io.*;

public final class CopyFilesCmdExecImpl implements ICmdExec {

    public void exec(String[] args) {

        String fromFolder = "";
        String fromFileName = "copy-files.txt";
        String linePrefixes = "";
        if (null != args && args.length > 0) {
            fromFolder = args[0];
            if (args.length > 1) {
                fromFileName = args[1];
            }
            if (args.length > 2) {
                linePrefixes = args[2];
            }
        }

        try {
            doCopyFiles(fromFolder, fromFileName, linePrefixes);
        } catch (Exception e) {
            Print.printException(e);
        }
    }

    private void doCopyFiles(String fromFolder, String fromFileName, String linePrefixes) throws Exception {

        Print.printLine("文件所在文件夹：", fromFolder, "\t", "列表文件：", fromFileName, "\t", "标识前缀：", linePrefixes);

        FileUtils.doWithLine(fromFileName, line -> {
            if (lineMatch(line, linePrefixes)) {
                String srcFilePath = line.substring(linePrefixes.trim().length()).trim();
                File srcFile = new File(fromFolder, srcFilePath);
                if (srcFile.exists()) {
                    Print.printLine("复制文件：", srcFilePath);
                    File destFile = new File(srcFilePath);
                    if (!destFile.getParentFile().exists()) {
                        destFile.getParentFile().mkdirs();
                    }
                    try {
                        FileUtils.copy(srcFile, destFile);
                        Print.printLine("成功\n");
                    } catch (Exception e) {
                        Print.printLine("失败\n");
                        Print.printException(e);
                    }
                }
            }
        });
    }

    private boolean lineMatch(String line, String linePrefixes) {
        if (line.trim().length() == 0) {
            return false;
        }
        if (null == linePrefixes || linePrefixes.trim().length() == 0) {
            return true;
        }
        return line.toLowerCase().indexOf(linePrefixes.trim().toLowerCase()) == 0;
    }
}
