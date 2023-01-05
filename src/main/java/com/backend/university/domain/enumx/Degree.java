package com.backend.university.domain.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Degree {

    GRADUATE(1),
    MASTER(2),
    PHD(3);

    private final int value;

}
