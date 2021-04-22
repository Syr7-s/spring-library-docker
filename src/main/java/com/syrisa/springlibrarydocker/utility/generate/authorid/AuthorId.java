package com.syrisa.springlibrarydocker.utility.generate.authorid;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public final class AuthorId {
    private AuthorId(){

    }

    public static Supplier<Long> authorIdGenerate = () ->
            Long.parseLong(LocalDateTime.now().toString().replaceAll("[^0-9]",""));
}
