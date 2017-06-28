# service-logging

## 使用说明

### logback.xml 中需要如下配置

```xml
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
        <layout class="org.nofdev.logging.CustomJsonLayout">
            <appendLineSeparator>true</appendLineSeparator>
            <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
            <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
            <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                <!--是否需要对json格式打印-->
                <prettyPrint>true</prettyPrint>
            </jsonFormatter>
            <!--日志的前缀-->
            <logPrefix>~~~json~~~</logPrefix>
            <!--是否打开显示前缀的功能-->
            <logPrefixSwitch>false</logPrefixSwitch>
        </layout>
    </encoder>
</appender>
```

### 与SpringBoot整合 logback-spring.xml 完整案例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <contextName>ProjectName</contextName>

    <springProfile name="development,develop">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
                <layout class="org.nofdev.logging.CustomJsonLayout">
                    <appendLineSeparator>true</appendLineSeparator>
                    <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
                    <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
                    <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                        <!--是否需要对json格式打印-->
                        <prettyPrint>true</prettyPrint>
                    </jsonFormatter>
                    <!--日志的前缀-->
                    <logPrefix>~~~json~~~</logPrefix>
                    <!--是否打开显示前缀的功能-->
                    <logPrefixSwitch>false</logPrefixSwitch>
                </layout>
            </encoder>
        </appender>

        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
        </root>
        <logger name="com.company" level="DEBUG"/>

        <!--JPA 显示执行的SQL和参数-->
        <logger name="org.hibernate.SQL" level="DEBUG"/>
        <logger name="org.hibernate.type" level="TRACE"/>
    </springProfile>

    <springProfile name="production,pre,testing,test">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
                <layout class="org.nofdev.logging.CustomJsonLayout">
                    <appendLineSeparator>true</appendLineSeparator>
                    <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
                    <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
                    <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                        <!--是否需要对json格式打印-->
                        <prettyPrint>false</prettyPrint>
                    </jsonFormatter>
                    <!--日志的前缀-->
                    <logPrefix>~~~json~~~</logPrefix>
                    <!--是否打开显示前缀的功能-->
                    <logPrefixSwitch>false</logPrefixSwitch>
                </layout>
            </encoder>
        </appender>

        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
        </root>

        <logger name="com.company" level="INFO"/>
    </springProfile>


    <logger name="org.apache" level="INFO"/>
    <logger name="org.springframework" level="INFO"/>
    <logger name="org.eclipse.jetty" level="INFO"/>
    <logger name="jndi" level="INFO"/>
    <logger name="org.hibernate" level="INFO"/>
</configuration>
```

```java
private static final CustomLogger logger = CustomLogger.getLogger(HelloFacadeImpl.class);

logger.debug("错误概述", e) { ["custDate": "自定义键值对", "func": debugStr()] }
```

更多用法参考测试代码 `CustomLoggerSpec`

## Workaround

因为本组件是利用占位符传递参数输出闭包的返回值, 所以原来支持的 message="xxx{}{}" 这种占位符写法就失效了, 并且因为取值是在 Layout 上做的, 所以如果不用自定义的 layout 话, 闭包内的内容会丢失(其他参数不会)