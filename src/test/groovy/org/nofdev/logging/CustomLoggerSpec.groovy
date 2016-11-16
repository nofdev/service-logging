package org.nofdev.logging

import groovy.transform.CompileStatic
import org.nofdev.servicefacade.CallId
import org.nofdev.servicefacade.ServiceContextHolder
import spock.lang.Specification

/**
 * Created by Liutengfei on 2016/8/9 0009.
 */
@CompileStatic
public class CustomLoggerSpec extends Specification {
    private static final CustomLogger log = CustomLogger.getLogger(CustomLoggerSpec.class);

    def setupSpec() {}

    def setup() {}

    def cleanup() {}

//    @Ignore
    def demo() {
        setup:
        ServiceContextHolder.serviceContext.setCallId(new CallId(id: "111", parent: "000", root: "000"))

        Exception e = new RuntimeException("发生异常了")
        log.debug("错误概述")
        log.debug("错误概述", e)
        log.debug("错误概述", e) { ["custDate": "自定义键值对", "func": debugStr()] }
        log.debug(e) { ["message": "错误概述", "func": debugStr()] }



        //下面这两个效果是一样的,闭包传入字符串默认覆盖掉message
        log.debug("错误概述") { [message: "我会覆盖前面的错误概述"] }
        log.debug("错误概述") { def a = "我会覆盖前面的错误概述"; ["message": "测试GString的序列化,${a}"] }

        //闭包里面的只会在日志级别正确的时候执行
        log.debug() { "我是错误描述，但是如果不是debug级别我里面的函数是不会执行的${debugStr()}" }
        log.debug() { [message: "我是错误描述，不是Debug级别我是不会执行的"] }
        log.debug("错误概述") { ["custDate": "自定义键值对", "func": debugStr()] }
        log.debug("测试 map 的嵌套") {
            def a = [bb: 2, cc: 3]
            [aa: a]
        }

    }

    public static String infoStr() {
        return "---------------infoStr------------"
    }

    public static String debugStr() {
        println "debugStr Method执行了"
        return "---------------debugStr------------"
    }

}
