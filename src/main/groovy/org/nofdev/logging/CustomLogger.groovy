package org.nofdev.logging

import groovy.transform.CompileStatic
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
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, message, null, null);
            } else {
                proxy.error(message);
            }
        }
    }

    public void error(final String message, final Throwable throwable) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, message, null, throwable);
            } else {
                proxy.error(message, throwable);
            }
        }
    }

    public void error(final String message, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, message, params, null);
            } else {
                proxy.error(message);
            }
        }
    }

    public void error(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, message, params, throwable);
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
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, null, params, null);
            } else {
                //String类型的参数会当做message传入
                if (args.get() instanceof String) {
                    proxy.error(String.valueOf(args?.get()), (Throwable) null);
                } else {
                    proxy.error("null", (Throwable) null);
                }
            }
        }
    }

    public void error(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isErrorEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.ERROR_INT, null, params, throwable);
            } else {
                proxy.error(null, throwable);
            }
        }
    }
    //==============================warn==========================================
    public void warn(final String message) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, message, null, null);
            } else {
                proxy.warn(message);
            }
        }
    }

    public void warn(final String message, final Throwable throwable) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, message, null, throwable);
            } else {
                proxy.warn(message, throwable);
            }
        }
    }

    public void warn(final String message, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, message, params, null);
            } else {
                proxy.warn(message);
            }
        }
    }

    public void warn(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, message, params, throwable);
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
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, null, params, null);
            } else {
                //String类型的参数会当做message传入
                if (args.get() instanceof String) {
                    proxy.warn(String.valueOf(args?.get()), (Throwable) null);
                } else {
                    proxy.warn("null", (Throwable) null);
                }
            }
        }
    }

    public void warn(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isWarnEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.WARN_INT, null, params, throwable);
            } else {
                proxy.warn(null, throwable);
            }
        }
    }
//==================================info======================================
    public void info(final String message) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, message, null, null);
            } else {
                proxy.info(message);
            }
        }
    }

    public void info(final String message, final Throwable throwable) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, message, null, throwable);
            } else {
                proxy.info(message, throwable);
            }
        }
    }

    public void info(final String message, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, message, params, null);
            } else {
                proxy.info(message);
            }
        }
    }

    public void info(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, message, params, throwable);
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
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, null, params, null);
            } else {
                //String类型的参数会当做message传入
                if (args.get() instanceof String) {
                    proxy.info(String.valueOf(args?.get()), (Throwable) null);
                } else {
                    proxy.info("null", (Throwable) null);
                }
            }
        }
    }

    public void info(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isInfoEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.INFO_INT, null, params, throwable);
            } else {
                proxy.info(null, throwable);
            }
        }
    }
    //==============================debug=======================================
    public void debug(final String message) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, message, null, null);
            } else {
                proxy.debug(message);
            }
        }
    }

    public void debug(final String message, final Throwable throwable) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, message, null, throwable);
            } else {
                proxy.debug(message, throwable);
            }
        }
    }

    public void debug(final String message, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, message, params, null);
            } else {
                proxy.debug(message);
            }
        }
    }

    public void debug(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, message, params, throwable);
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
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, null, params, null);
            } else {
                //String类型的参数会当做message传入
                if (args.get() instanceof String) {
                    proxy.debug(String.valueOf(args?.get()), (Throwable) null);
                } else {
                    proxy.debug("null", (Throwable) null);
                }
            }
        }
    }

    public void debug(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isDebugEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.DEBUG_INT, null, params, throwable);
            } else {
                proxy.debug(null, throwable);
            }
        }
    }

//================================Trace========================================
    public void trace(final String message) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, message, null, null);
            } else {
                proxy.trace(message);
            }
        }
    }

    public void trace(final String message, final Throwable throwable) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, message, null, throwable);
            } else {
                proxy.trace(message, throwable);
            }
        }
    }

    public void trace(final String message, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, message, params, null);
            } else {
                proxy.trace(message);
            }
        }
    }

    public void trace(final String message, final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, message, params, throwable);
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
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, null, params, null);
            } else {
                //String类型的参数会当做message传入
                if (args.get() instanceof String) {
                    proxy.trace(String.valueOf(args?.get()), (Throwable) null);
                } else {
                    proxy.trace("null", (Throwable) null);
                }
            }
        }
    }

    public void trace(final Throwable throwable, final Supplier<Map> args) {
        if (proxy.isTraceEnabled()) {
            if (proxy instanceof LocationAwareLogger) {
                Object[] params = [args?.get()]
                ((LocationAwareLogger) proxy).log(null, FQCN, LocationAwareLogger.TRACE_INT, null, params, throwable);
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
}
