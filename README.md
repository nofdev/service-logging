# service-logging

## 使用说明

在 logback 上测试通过, 需要如下配置

```xml
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
        <layout class="org.nofdev.logging.CustomJsonLayout">
            <appendLineSeparator>true</appendLineSeparator>
            <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
            <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
            <jsonFormatter class="org.nofdev.logging.CustomJacksonJsonFormatter">
            </jsonFormatter>
            <logPrefix>~~~json~~~</logPrefix>
        </layout>
    </encoder>
</appender>
```

```java
private static final CustomLogger logger = CustomLogger.getLogger(HelloFacadeImpl.class);

logger.debug("错误概述", e) { ["custDate": "自定义键值对", "func": debugStr()] }
```

更多用法参考 `CustomLoggerSpec`

## Workaround
因为本组件是利用占位符传递参数输出闭包的返回值, 所以原来支持的 message="xxx{}{}" 这种占位符写法就失效了, 并且因为取值是在 Layout 上做的, 所以如果不用自定义的 layout 话, 闭包内的内容会丢失(其他参数不会)