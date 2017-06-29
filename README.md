# service-logging

### 简单使用 logback.xml 配置示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--项目名称-->
    <property name="ProjectName" value="MyLog" />
    <!--是否需要对json格式日志美化-->
    <property name="JsonPretty" value="true" />
    <!--是否打开显示前缀的功能-->
    <property name="PrefixSwitch" value="false" />
    <!--要显示的前缀字符串-->
    <property name="PrefixText" value="~~~json~~~" />

    <contextName>${ProjectName}</contextName>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="org.nofdev.logging.CustomJsonLayout">
                <appendLineSeparator>true</appendLineSeparator>
                <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
                <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
                <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                    <prettyPrint>${JsonPretty}</prettyPrint>
                </jsonFormatter>
                <logPrefix>
                    <prefixSwitch>${PrefixSwitch}</prefixSwitch>
                    <prefixText>${PrefixText}</prefixText>
                </logPrefix>
                <!--对日志的某些键值对统一处理，具体使用方法见单元测试，如果你不需要可以注释掉这段代码-->
                <logPostProcessor class="org.nofdev.logging.CustomPostProcessor"/>
            </layout>
        </encoder>
    </appender>

    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
    </root>

    <logger name="org.nofdev" level="INFO"/>
    <logger name="org.apache" level="INFO"/>
    <logger name="org.springframework" level="INFO"/>
    <logger name="org.eclipse.jetty" level="INFO"/>
    <logger name="jndi" level="INFO"/>
    <logger name="org.hibernate" level="INFO"/>
</configuration>


```

### 与SpringBoot整合 logback-spring.xml 完整示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <!--项目名称-->
    <property name="ProjectName" value="MyLog" />
    <!--是否需要对json格式日志美化-->
    <property name="JsonPretty" value="true" />
    <!--是否打开显示前缀的功能-->
    <property name="PrefixSwitch" value="false" />
    <!--要显示的前缀字符串-->
    <property name="PrefixText" value="~~~json~~~" />

    <contextName>${ProjectName}</contextName>

     <!--development,develop 环境会激活这里的配置-->
    <springProfile name="development,develop">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
                <layout class="org.nofdev.logging.CustomJsonLayout">
                    <appendLineSeparator>true</appendLineSeparator>
                    <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
                    <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
                    <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                        <prettyPrint>${JsonPretty}</prettyPrint>
                    </jsonFormatter>
                    <logPrefix>
                        <prefixSwitch>${PrefixSwitch}</prefixSwitch>
                        <prefixText>${PrefixText}</prefixText>
                    </logPrefix>
                    <!--对日志的某些键值对统一处理，具体使用方法见单元测试，如果你不需要可以注释掉这段代码-->
                    <logPostProcessor class="com.company.logging.CustomPostProcessor"/>
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

    <!--production,pre,testing,test 环境会激活这里的配置-->
    <springProfile name="production,pre,testing,test">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
                <layout class="org.nofdev.logging.CustomJsonLayout">
                    <appendLineSeparator>true</appendLineSeparator>
                    <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
                    <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
                    <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
                        <prettyPrint>${JsonPretty}</prettyPrint>
                    </jsonFormatter>
                    <logPrefix>
                        <prefixSwitch>${PrefixSwitch}</prefixSwitch>
                        <prefixText>${PrefixText}</prefixText>
                    </logPrefix>
                    <!--对日志的某些键值对统一处理，具体使用方法见单元测试，如果你不需要可以注释掉这段代码-->
                    <logPostProcessor class="com.company.logging.CustomPostProcessor"/>
                </layout>
            </encoder>
        </appender>

        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
        </root>

        <logger name="com.company" level="INFO"/>
    </springProfile>


    <logger name="org.nofdev" level="INFO"/>
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

## 后置日志输出处理器

通过实现 LogPostProcessor 接口，
可以改变日志的输出结果，去掉一些key，或者添加一些key,
比如所有的日志都需要调用链Id打印出来，在众多开发人员无感知的情况下，把CallID统一插入到日志里面
你可以对里面的键值对做任何增删改操作，但是注意不要执行太消耗时间的功能，否则会影响日志效率

## Workaround

因为本组件是利用占位符传递参数输出闭包的返回值, 所以原来支持的 message="xxx{}{}" 这种占位符写法就失效了, 并且因为取值是在 Layout 上做的, 所以如果不用自定义的 layout 话, 闭包内的内容会丢失(其他参数不会)