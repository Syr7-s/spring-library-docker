package com.syrisa.springlibrarydocker.utility.generate.authorid;

import com.syrisa.springlibrarydocker.utility.generate.NumberGenerate;

import java.util.Random;
import java.util.function.Supplier;

public final class AuthorId {
    private AuthorId() {
    }

    /* public static Supplier<Long> authorIdGenerate = () -> {
         StringBuilder builder = new StringBuilder();
         builder.append(1 + new Random().nextInt(9));
         for (int i = 0; i < 16; i++) {
             builder.append(new Random().nextInt(10));
         }
         return Long.parseLong(builder.toString());
     };*/
    public static final NumberGenerate numberGenerate = numLength -> {
        StringBuilder builder = new StringBuilder();
        builder.append(10);
        for (int i = 0; i < numLength; i++) {
            builder.append(new Random().nextInt(10));
        }
        return Long.parseLong(builder.toString());
    };

}
