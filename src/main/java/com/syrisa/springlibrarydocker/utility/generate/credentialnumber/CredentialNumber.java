package com.syrisa.springlibrarydocker.utility.generate.credentialnumber;

import com.syrisa.springlibrarydocker.utility.generate.NumberGenerate;

import java.util.Random;

public final class CredentialNumber {
    private CredentialNumber() {

    }

    public static final NumberGenerate numberGenerate =  numLength -> {
        StringBuilder builder = new StringBuilder();
        builder.append(1 + new Random().nextInt(9));
        for (int i = 0; i < numLength; i++) {
            builder.append(new Random().nextInt(10));
        }
        return Long.parseLong(builder.toString());
    };
}
