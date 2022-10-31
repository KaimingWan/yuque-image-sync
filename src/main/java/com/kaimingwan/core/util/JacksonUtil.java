package com.kaimingwan.core.util;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * @author wanshao create time is 2021/3/24
 **/
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static String toJsonIgnoreAnnotation(Object obj) {
        ObjectMapper ignoreAnnotationMapper = new ObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, false);
        return ignoreAnnotationMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static Object toObj(String jsonStr, Class clz) {
        return objectMapper.readValue(jsonStr, clz);
    }

}
