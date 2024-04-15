package com.evan.evanrpc.serializer;

import java.util.HashMap;

public class SerializerFactory {
    private static final HashMap<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {{
        put(SerializerKeys.JSON, new JsonSerializer());
        put(SerializerKeys.JDK, new JdkSerializer());
        put(SerializerKeys.HESSIAN, new HessianSerializer());
        put(SerializerKeys.KRYO, new KryoSerializer());
    }};

    private static final Serializer DEFAULT_SERIALIZER = KEY_SERIALIZER_MAP.get("jdk");

    public static Serializer getInstance(String key) {
        return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
    }
}
