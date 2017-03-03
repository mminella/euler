package com.jtig.euler.problem;

public interface Problem {

    String getName();

    String getDescription();

    Number getResult();

    default boolean getSolved() {
        return false;
    }
}
