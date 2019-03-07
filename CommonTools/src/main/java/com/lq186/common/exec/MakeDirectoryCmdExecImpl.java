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
    FileName: MakeDirectoryCmdExecImpl.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.common.exec;

import com.lq186.common.out.Print;
import com.lq186.common.util.FileUtils;
import com.lq186.common.util.StringUtils;

import java.io.File;

public final class MakeDirectoryCmdExecImpl implements ICmdExec {

    @Override
    public void exec(String[] args) {
        String fromFileName = "dir.txt";
        String subDirs = "";

        if (null != args && args.length > 0) {
            fromFileName = args[0];
            if (args.length > 1) {
                subDirs = args[1];
            }
        }

        try {
            doMakeDir(fromFileName, subDirs);
        } catch (Exception e) {
            Print.printException(e);
        }
    }

    private void doMakeDir(String fromFileName, String subDirs) throws Exception {
        FileUtils.doWithLine(fromFileName, line -> {
            String dirPath = line;
            if (null != subDirs || subDirs.trim().length() > 0) {
                dirPath = StringUtils.join(dirPath, File.separator, subDirs.trim());
            }
            Print.printLine("创建文件夹：", dirPath);
            try {
                FileUtils.makeDir(dirPath);
                Print.printLine("成功\n");
            } catch (Exception e) {
                Print.printLine("失败\n");
                Print.printException(e);
            }
        });
    }

}
