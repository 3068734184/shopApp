package com.example.shopmodel.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 自定义序列化器
 */
public class IntegerToStringSerializer extends StdSerializer<Integer> {

    public IntegerToStringSerializer() {
        this(null);
    }

    protected IntegerToStringSerializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(integer.toString());
    }
}
