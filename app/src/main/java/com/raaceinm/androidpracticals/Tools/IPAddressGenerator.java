package com.raaceinm.androidpracticals.Tools;

import java.util.Random;

public class IPAddressGenerator {
    public static String generateRandomIPv4() {
        Random random = new Random();
        StringBuilder ipAddress = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            ipAddress.append(random.nextInt(256));
            if (i < 3) {
                ipAddress.append(".");
            }
        }

        return ipAddress.toString();
    }
}