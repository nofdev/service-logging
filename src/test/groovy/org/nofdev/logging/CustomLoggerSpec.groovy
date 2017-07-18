package org.nofdev.logging

import groovy.transform.CompileStatic
import org.junit.Ignore
import spock.lang.Specification

import java.util.concurrent.CompletableFuture

/**
 * Created by Liutengfei on 2016/8/9 0009.
 */
@CompileStatic
public class CustomLoggerSpec extends Specification {
    private final CustomLogger log = CustomLogger.getLogger(CustomLoggerSpec.class);

    def setupSpec() {}

    def setup() {}

    def cleanup() {}


    @Ignore
    def postProcessor() {
        setup:
        log.debug("我是tom"){[age:18,address:"北京",abc:"111111"]}
        log.debug("我是tom的弟弟"){[age:17,address:"北京"]}
        CompletableFuture.runAsync({
            log.debug("我是jerry"){[age:16,address:"杭州"]}
        })
        new Thread({
            log.debug("我是bill"){[age:14,address:"上海"]}
        }).start()
    }

    @Ignore
    def demo() {
        setup:

        Exception e = new RuntimeException("发生异常了")
        log.debug("错误概述")
        log.debug("错误概述", e)
        log.debug("错误概述", e) { ["custDate": "自定义键值对", "func": debugStr()] }
        log.debug(e) { ["message": "错误概述", "func": debugStr()] }
        log.debug(e) { ["message": "错误概述111111"] }
        log.debug(e) { "当传入字符串的时候，自动作为message的值" }

        //闭包里面的只会在日志级别正确的时候执行
        log.debug() { "我是错误描述，但是如果不是debug级别我里面的函数是不会执行的${debugStr()}" }
        log.debug() { [message: "注意我的key是 msg_message"] }
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
