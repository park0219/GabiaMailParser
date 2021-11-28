package me.park.GabiaMailParser.util;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Util {

    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static String base64Decode(String str) {

        StringBuilder returnBuffer = new StringBuilder();
        Pattern pattern = Pattern.compile("(=\\?)(.*)(\\?B\\?)(.*)(\\?=)(.*)");
        byte[] decodeBytes;

        //배열로 다중 문자열 처리
        String[] strList = str.trim().replaceAll(" +", " ").split(" ");

        for(String s : strList) {
            try {
                Matcher matcher = pattern.matcher(s);
                if(matcher.find()) {
                    String encode = matcher.group(2).trim();
                    decodeBytes = decoder.decode(matcher.group(4).trim());
                    returnBuffer.append(new String(decodeBytes, Charset.forName(encode))).append(" ").append(matcher.group(6).trim());
                }
                else {
                    returnBuffer.append(s);
                }
            }
            catch(Exception e) {
                returnBuffer.append(s);
            }
            returnBuffer.append(" ");
        }

        return returnBuffer.toString();
    }

}
