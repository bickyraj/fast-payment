package com.bicky.demopayment.productservice.utils;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileUtils {
    private static final Pattern ILLEGAL_CHARACTERS = Pattern.compile("[^a-zA-Z0-9._-]");

    public static String sanitizeFileName(String fileName) {
        String sanitized = Paths.get(fileName).getFileName().toString();
        sanitized = ILLEGAL_CHARACTERS.matcher(sanitized).replaceAll("_");
        if (sanitized.length() > 255) {
            sanitized = sanitized.substring(0, 255);
        }

        return sanitized;
    }
}
