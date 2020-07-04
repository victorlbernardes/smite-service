package br.com.victor.smite.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String getHash(String devId, String path, String authKey, String date) {

        String toHash = devId + path + authKey + date;

        StringBuilder signatureBuilder = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toHash.getBytes());
            byte[] bytes = md.digest();

            for (byte bit : bytes) {
                String hex = Integer.toHexString(0xff & bit);
                if (hex.length() == 1) {
                    signatureBuilder.append("0");
                }
                signatureBuilder.append(hex);
            }
        } catch (NoSuchAlgorithmException ignore) {
        }
        return signatureBuilder.toString();
    }

    public static String getTimestampFormatted() {

        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(now);

    }
}
