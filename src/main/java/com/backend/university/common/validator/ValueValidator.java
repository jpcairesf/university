package com.backend.university.common.validator;

public interface ValueValidator<T> {

    void validate(T value);

}
