package br.com.victor.smite.smitestatus.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String getHash(String devId, String path, String authKey, String date) throws NoSuchAlgorithmException {

        String toHash = devId + path + authKey + date;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(toHash.getBytes());

        StringBuilder stringBuider = new StringBuilder();
        for (byte b : messageDigest.digest())
            stringBuider.append(String.format("%02x", b & 0xff));

        return stringBuider.toString();

    }

    public static String getTimestampFormatted(){

        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(now);

    }
}
