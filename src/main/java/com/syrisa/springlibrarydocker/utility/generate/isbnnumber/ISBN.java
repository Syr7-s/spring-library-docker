package com.syrisa.springlibrarydocker.utility.generate.isbnnumber;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class ISBN {
    private ISBN(){

    }

    public static final Supplier<String> generateISBN = () -> {
        StringBuilder builder = new StringBuilder();
        return String.valueOf(builder.append("ISBN").append(" ").append(generateFirstThreeNumber())
                .append("-")
                .append(generateNumber(3))
                .append("-")
                .append(generateNumber(4))
                .append("-")
                .append(generateNumber(2))
                .append("-")
                .append(generateNumber(1)));

    };

    private static String generateNumber(int number) {
        StringBuilder generateNu = new StringBuilder();
        for (int i = 0; i < number; i++) {
            generateNu.append(new Random().nextInt(10));
        }
        return generateNu.toString();

    }

    private static Integer generateFirstThreeNumber() {
        StringBuilder generateNu = new StringBuilder();
        generateNu.append(1 + new Random().nextInt(9));
        for (int i = 0; i < 2; i++) {
            generateNu.append(new Random().nextInt(10));
        }
        return Integer.parseInt(generateNu.toString());
    }

    public static Function<String,Long> cleanNotNumber = isbn -> Long.parseLong(isbn.replaceAll("[^0-9]",""));
}
