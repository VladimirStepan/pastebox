package ru.education.pastebox.math;

import java.util.Random;

public class GenerateRandomHash {
    public static String generateRandomHash() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(Integer.toHexString(random.nextInt(25)));
        }
        return sb.toString();
    }
}
