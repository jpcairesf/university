package com.backend.university.common.utils;

import java.util.Objects;
import java.util.function.Consumer;

public class MapperUtils {

    private MapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <V> void setIfNotNull(V value, Consumer<V> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }

}
