package org.nofdev.logging

import groovy.transform.ToString
import org.junit.Test

/**
 * Created by Liutengfei on 2017/7/19 0019.
 */
@Logging
class LoggingTest {
    @Test
    public void slf4j(){
      log.info("哈哈哈")
    }
}
