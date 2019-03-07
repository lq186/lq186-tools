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
    FileName: BootstrapApp.java
    Date: 2019/2/22
    Author: lq
*/
package com.lq186.common;

import com.lq186.common.exec.CmdExecFactory;
import com.lq186.common.exec.ICmdExec;
import com.lq186.common.out.Print;
import com.lq186.common.usage.Usage;

public final class BootstrapApp {

    public static void main(String[] args) {

        if (null == args || args.length == 0) {
            Usage.print();
        }

        final String cmd = args[0];
        String[] params = null;

        if (1 < args.length) {
            params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, args.length - 1);
        }

        ICmdExec cmdExec = CmdExecFactory.getCmdExec(cmd);
        try {
            cmdExec.exec(params);
        } catch (Exception e) {
            Print.printException(e);
        }
    }

}
