package org.nofdev.logging

import groovy.test.GroovyAssert
import org.junit.Ignore
import org.junit.Test

/**
 * Created by Liutengfei on 2017/7/19 0019.
 */

class GroovyAstTest {

    @Ignore
    @Test
    public void slf4j(){
        GroovyAssert.assertScript '''

        import groovy.util.logging.Slf4j

        @Slf4j
        class Demo {
            /**
             * 测试能否解析注释
             */
            void testLog() {
                log.info("哈哈哈哈")
            }
        }
        def c = new Demo()
        c.testLog()

        '''
    }
    @Ignore
    @Test
    public void logging(){
        GroovyAssert.assertScript '''

        import org.nofdev.logging.Logging

        @Logging
        class Demo {
            void testLog() {
                log.info("哈哈哈哈")
            }
        }
        def c = new Demo()
        c.testLog()

        '''
    }
}
