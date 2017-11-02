package org.nofdev.logging

import ch.qos.logback.contrib.jackson.JacksonJsonFormatter
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule

/**
 * Created by Liutengfei on 2016/11/15 0015.
 */
class CustomJacksonJsonFormatter extends JacksonJsonFormatter {
    CustomJacksonJsonFormatter() {
        super()
        SimpleModule module = new SimpleModule()
        module.addSerializer(GString.class, new GStringSerializer())
        getObjectMapper().registerModule(module)
    }

    private class GStringSerializer extends JsonSerializer<GString> {
        @Override
        void serialize(GString value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.toString())
        }
    }
}
