package org.nofdev.logging

import groovy.transform.CompileStatic
import org.nofdev.servicefacade.ServiceContextHolder
import org.slf4j.LoggerFactory
import org.slf4j.spi.LocationAwareLogger

import java.util.function.Supplier

/**
 * Created by Liutengfei on 2016/8/11 0011.
 */
@CompileStatic
public final class CustomLogger {

    /**
     * The fully qualified class name.
     */
    private static final String FQCN = CustomLogger.class.getName();

    /**
     * SLF4j logger.
     */
    private org.slf4j.Logger proxy;

    public org.slf4j.Logger getProxy() {
        return proxy;
    }


    public static CustomLogger getLogger(final String className) {
        return new CustomLogger(className);
    }

    public static CustomLogger getLogger(final Class<?> clazz) {
        return new CustomLogger(clazz.getName());
    }

    private CustomLogger(final String className) {
        proxy = LoggerFactory.getLogger(className);
    }

//==============================error==========================================
    public void error(final String message) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, LocationAwareLogger.ERROR_INT);
            } else {
                proxy.error(message);
            }
        }
    }

    public void error(final String message, final Throwable throwable) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, LocationAwareLogger.ERROR_INT);
            } else {
                proxy.error(message, throwable);
            }
        }
    }

    public void error(final String message, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, args, LocationAwareLogger.ERROR_INT);
            } else {
                proxy.error(message);
            }
        }
    }

    public void error(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, args, LocationAwareLogger.ERROR_INT);
            } else {
                proxy.error(message, throwable);
            }
        }
    }
    /**
     * 当传入map的时候，map的key不要定义为 timestamp 、level 、thread 、logger 、context 因为这样会覆盖掉内置关键字。<br>
     * 当传入String类型的时候，会覆盖messsage
     * @param args 目前仅支持Map和String类型
     */
    public void error(final Supplier args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(args, LocationAwareLogger.ERROR_INT)
            } else {
                proxy.error(String.valueOf(args?.get()), (Throwable) null);
            }
        }
    }

    public void error(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(throwable, args, LocationAwareLogger.ERROR_INT);
            } else {
                proxy.error(null, throwable);
            }
        }
    }

    //==============================warn==========================================
    public void warn(final String message) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(message);
            }
        }
    }

    public void warn(final String message, final Throwable throwable) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(message, throwable);
            }
        }
    }

    public void warn(final String message, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, args, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(message);
            }
        }
    }

    public void warn(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, args, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(message, throwable);
            }
        }
    }
    /**
     * 当传入map的时候，map的key不要定义为 timestamp 、level 、thread 、logger 、context 因为这样会覆盖掉内置关键字。<br>
     * 当传入String类型的时候，会覆盖messsage
     * @param args 目前仅支持Map和String类型
     */
    public void warn(final Supplier args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(args, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(String.valueOf(args?.get()), (Throwable) null);
            }
        }
    }

    public void warn(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(throwable, args, LocationAwareLogger.WARN_INT);
            } else {
                proxy.warn(null, throwable);
            }
        }
    }
//==================================info======================================
    public void info(final String message) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(message);
            }
        }
    }

    public void info(final String message, final Throwable throwable) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(message, throwable);
            }
        }
    }

    public void info(final String message, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, args, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(message);
            }
        }
    }

    public void info(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, throwable, args, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(message, throwable);
            }
        }
    }
    /**
     * 当传入map的时候，map的key不要定义为 timestamp 、level 、thread 、logger 、context 因为这样会覆盖掉内置关键字。<br>
     * 当传入String类型的时候，会覆盖messsage
     * @param args 目前仅支持Map和String类型
     */
    public void info(final Supplier args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(args, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(String.valueOf(args?.get()), (Throwable) null);
            }
        }
    }

    public void info(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(throwable, args, LocationAwareLogger.INFO_INT);
            } else {
                proxy.info(null, throwable);
            }
        }
    }
    //==============================debug=======================================
    public void debug(final String message) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(message);
            }
        }
    }

    public void debug(final String message, final Throwable throwable) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,throwable, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(message, throwable);
            }
        }
    }

    public void debug(final String message, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,args, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(message);
            }
        }
    }

    public void debug(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,throwable,args, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(message, throwable);
            }
        }
    }
    /**
     * 当传入map的时候，map的key不要定义为 timestamp 、level 、thread 、logger 、context 因为这样会覆盖掉内置关键字。<br>
     * 当传入String类型的时候，会覆盖messsage
     * @param args 目前仅支持Map和String类型
     */
    public void debug(final Supplier args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(args, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(String.valueOf(args?.get()), (Throwable) null);
            }
        }
    }

    public void debug(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(throwable,args, LocationAwareLogger.DEBUG_INT);
            } else {
                proxy.debug(null, throwable);
            }
        }
    }

//================================Trace========================================
    public void trace(final String message) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(message);
            }
        }
    }

    public void trace(final String message, final Throwable throwable) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,throwable, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(message, throwable);
            }
        }
    }

    public void trace(final String message, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,args, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(message);
            }
        }
    }

    public void trace(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(message,throwable,args, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(message, throwable);
            }
        }
    }
    /**
     * 当传入map的时候，map的key不要定义为 timestamp 、level 、thread 、logger 、context 因为这样会覆盖掉内置关键字。<br>
     * 当传入String类型的时候，会覆盖messsage
     * @param args 目前仅支持Map和String类型
     */
    public void trace(final Supplier args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(args, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(String.valueOf(args?.get()), (Throwable) null);
            }
        }
    }

    public void trace(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                proxyLog(throwable,args, LocationAwareLogger.TRACE_INT);
            } else {
                proxy.trace(null, throwable);
            }
        }
    }


//========================================================================
    public boolean isErrorEnabled() {
        if (proxy instanceof LocationAwareLogger) {
            return ((LocationAwareLogger) proxy).isErrorEnabled();
        } else {
            return proxy.isErrorEnabled();
        }
    }

    public boolean isWarnEnabled() {
        if (proxy instanceof LocationAwareLogger) {
            return ((LocationAwareLogger) proxy).isWarnEnabled();
        } else {
            return proxy.isWarnEnabled();
        }
    }

    public boolean isInfoEnabled() {
        if (proxy instanceof LocationAwareLogger) {
            return ((LocationAwareLogger) proxy).isInfoEnabled();
        } else {
            return proxy.isInfoEnabled();
        }
    }

    public boolean isDebugEnabled() {
        if (proxy instanceof LocationAwareLogger) {
            return ((LocationAwareLogger) proxy).isDebugEnabled();
        } else {
            return proxy.isDebugEnabled();
        }
    }

    public boolean isTraceEnabled() {
        if (proxy instanceof LocationAwareLogger) {
            return ((LocationAwareLogger) proxy).isTraceEnabled();
        } else {
            return proxy.isTraceEnabled();
        }
    }

    public boolean isLoggable(final Level level) {
        switch (level) {
            case Level.TRACE:
                return isTraceEnabled();
            case Level.DEBUG:
                return isDebugEnabled();
            case Level.INFO:
                return isInfoEnabled();
            case Level.WARN:
                return isWarnEnabled();
            case Level.ERROR:
                return isErrorEnabled();
            default:
                throw new IllegalStateException("Logging level [" + level + "] is invalid");
        }
    }

    private Object[] assemblyArgs(final Supplier<Map> args) {
        Map map = args?.get()
        if (!map) {
            map = new HashMap()
        }
        map.put(ServiceContextHolder.serviceContext.CALLID, ServiceContextHolder.serviceContext.getCallId())
        Object[] objects=[map]
        return objects
    }

    private void proxyLog(String message, final int level) {
        ((LocationAwareLogger) proxy).log(null, FQCN, level, message, assemblyArgs(null), null)
    }

    private void proxyLog(String message, Throwable throwable, final int level) {
        ((LocationAwareLogger) proxy).log(null, FQCN, level, message, assemblyArgs(null), throwable)
    }

    private void proxyLog(String message, Supplier<Map> args, final int level) {
        ((LocationAwareLogger) proxy).log(null, FQCN, level, message, assemblyArgs(args), null)
    }

    private void proxyLog(String message, Throwable throwable, Supplier<Map> args, final int level) {
        ((LocationAwareLogger) proxy).log(null, FQCN, level, message, assemblyArgs(args), throwable)
    }

    private void proxyLog(Throwable throwable, Supplier<Map> args, final int level) {
        ((LocationAwareLogger) proxy).log(null, FQCN, level, null, assemblyArgs(args), throwable)
    }

    private void proxyLog(final Supplier args, final int level) {
        def params = args?.get()
        if (params instanceof String || params instanceof GString) {
            ((LocationAwareLogger) proxy).log(null, FQCN, level, String.valueOf(params), assemblyArgs(null), null);
        } else if (params instanceof Map) {
            ((LocationAwareLogger) proxy).log(null, FQCN, level, null, assemblyArgs(args), null);
        }
    }
}