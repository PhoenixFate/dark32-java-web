package com.phoenix.common.utils;

import java.util.Optional;

public class OptionalFactory {

    public static Optional<String> getOptional(){
        Optional<String> optional=Optional.of("/");
        return optional;
    }

}
