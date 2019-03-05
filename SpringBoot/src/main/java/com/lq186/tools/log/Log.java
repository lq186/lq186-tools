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
    FileName: Log.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.tools.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log {

    private final Logger logger;

    public Log(Logger logger) {
        this.logger = logger;
    }

    public static final Log getLog(Class<?> clazz) {
        return new Log(LoggerFactory.getLogger(clazz));
    }

    public static final Log getLog(String name) {
        return new Log(LoggerFactory.getLogger(name));
    }

    public void trace(String msg) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    public void trace(String msg, Object object) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, object);
        }
    }

    public void trace(String msg, Object... objects) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, objects);
        }
    }

    public void trace(String msg, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, throwable);
        }
    }

    public void tracef(String format, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format, args));
        }
    }

    public void tracef(String format, Throwable throwable, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format, args), throwable);
        }
    }

    public void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    public void debug(String msg, Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, object);
        }
    }

    public void debug(String msg, Object... objects) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, objects);
        }
    }

    public void debug(String msg, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, throwable);
        }
    }

    public void debugf(String format, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format, args));
        }
    }

    public void debugf(String format, Throwable throwable, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format, args), throwable);
        }
    }

    public void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    public void info(String msg, Object object) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, object);
        }
    }

    public void info(String msg, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, objects);
        }
    }

    public void info(String msg, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, throwable);
        }
    }

    public void infof(String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format, args));
        }
    }

    public void infof(String format, Throwable throwable, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format, args), throwable);
        }
    }

    public void warn(String msg) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    public void warn(String msg, Object object) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, object);
        }
    }

    public void warn(String msg, Object... objects) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, objects);
        }
    }

    public void warn(String msg, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, throwable);
        }
    }

    public void warnf(String format, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format, args));
        }
    }

    public void warnf(String format, Throwable throwable, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format, args), throwable);
        }
    }

    public void error(String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    public void error(String msg, Object object) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, object);
        }
    }

    public void error(String msg, Object... objects) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, objects);
        }
    }

    public void error(String msg, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, throwable);
        }
    }

    public void errorf(String format, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format, args));
        }
    }

    public void errorf(String format, Throwable throwable, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format, args), throwable);
        }
    }
}
