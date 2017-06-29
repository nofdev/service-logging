package org.nofdev.logging

/**
 * 实现这个接口对日志中的键值对统一处理
 * @author tengfei liu
 * @date 2017-06-29
 */
interface LogPostProcessor {
    void invoke(Map<String, Object> slf4jMap, Map customMap)
}
