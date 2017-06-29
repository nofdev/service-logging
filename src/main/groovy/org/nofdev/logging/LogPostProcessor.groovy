package org.nofdev.logging

/**
 *
 * @author tengfei liu
 * @date 2017-06-29
 */
interface LogPostProcessor {
    void invoke(Map<String, Object> slf4jMap, Map customMap)
}
