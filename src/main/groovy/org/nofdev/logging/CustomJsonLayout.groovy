package org.nofdev.logging

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.contrib.json.classic.JsonLayout

/**
 * Created by Liutengfei on 2016/8/11 0011.
 */
public class CustomJsonLayout extends JsonLayout {
    protected LogPostProcessor logPostProcessor
    protected LogPrefix logPrefix

    CustomJsonLayout() {
        super();
    }

    /**
     * 利用占位符传递参数输出闭包的返回值, 所以原来支持的 message="xxx{}{}" 这种占位符写法就失效了
     * @param map
     * @param event
     */
    @Override
    protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
        if (event.getArgumentArray()) {
            def customKV = event.getArgumentArray()[0]
            if (customKV instanceof Map) {
                logPostProcessor?.invoke(map, customKV)
                map.putAll(customKV)
            }
        }
    }

    @Override
    String doLayout(ILoggingEvent event) {
        if (logPrefix?.prefixSwitch) {
            return "${logPrefix.prefixText}${super.doLayout(event)}"
        } else {
            return super.doLayout(event)
        }
    }

    LogPostProcessor getLogPostProcessor() {
        return logPostProcessor
    }

    void setLogPostProcessor(LogPostProcessor logPostProcessor) {
        this.logPostProcessor = logPostProcessor
    }

    LogPrefix getLogPrefix() {
        return logPrefix
    }

    void setLogPrefix(LogPrefix logPrefix) {
        this.logPrefix = logPrefix
    }

    static class LogPrefix {
        /**
         * 是否要显示前缀
         */
        protected boolean prefixSwitch;
        /**
         * 要显示的前缀字符串
         */
        protected String prefixText

        LogPrefix() {
            prefixText = ""
            prefixSwitch = false
        }

        boolean getPrefixSwitch() {
            return prefixSwitch
        }

        void setPrefixSwitch(boolean prefixSwitch) {
            this.prefixSwitch = prefixSwitch
        }

        String getPrefixText() {
            return prefixText
        }

        void setPrefixText(String prefixText) {
            this.prefixText = prefixText
        }
    }

}


