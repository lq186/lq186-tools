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
    FileName: CmdExecFactory.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.common.exec;

import com.lq186.common.usage.Usage;

public final class CmdExecFactory {


    public static ICmdExec getCmdExec(final String cmd) {
        if (null == cmd || cmd.trim().equals("")) {
            Usage.print();
            return DefaultNoneCmdExecHolder.CMD_EXEC;
        }

        switch (cmd.trim().toLowerCase()) {
            case "cpfile":
                return new CopyFilesCmdExecImpl();
            case "mkdir":
                return new MakeDirectoryCmdExecImpl();
            case "cpjar":
                return new CopyMavenJarExecImpl();
            default:
                return DefaultNoneCmdExecHolder.CMD_EXEC;
        }
    }

    static class DefaultNoneCmdExecHolder {
        static final DefaultNoneCmdExecImpl CMD_EXEC = new DefaultNoneCmdExecImpl();
    }
}
