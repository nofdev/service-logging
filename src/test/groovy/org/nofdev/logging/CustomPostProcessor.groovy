package org.nofdev.logging

import groovy.json.JsonOutput
import org.nofdev.servicefacade.context.CallId
import org.nofdev.servicefacade.context.ServiceContextHolder

/**
 *  这是一个示例，
 *  源自于我们的一个需求：要求所有的日志都要加上调用链Id
 * @author tengfei liu
 * @date 2017-06-29
 */
class CustomPostProcessor implements LogPostProcessor{
    @Override
    void invoke(Map<String, Object> slf4jMap, Map customMap) {
        if("我是tom"==slf4jMap.get("message")){
            customMap.remove("abc") //移除message
        }
        CallId callId=ServiceContextHolder.serviceContext.generatCallIdIfAbsente()
        customMap.put("callId",JsonOutput.toJson(callId))//插入一个新的自定义key
    }
}
