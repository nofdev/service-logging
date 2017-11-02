package org.nofdev.logging

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@java.lang.annotation.Documented
@Target([ElementType.TYPE])
@Retention(RetentionPolicy.SOURCE)
@GroovyASTTransformationClass("org.nofdev.logging.LoggingASTTransformation")
@interface Logging {
    String value() default "log"
}

