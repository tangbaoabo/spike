package com.tangbaobao.spike.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author tangxuejun
 * @version 2018/10/9 6:31 PM
 */
public class MD5Util {

    private static final String SALT = "CAFE_BABY";

    /**
     * 用户输入的密码到代码
     * @param inputPass
     * @return
     */
    private static String inputPass2FromPass(String inputPass) {
        return formPass2DBPass(inputPass, SALT);
    }

    public static String formPass2DBPass(String inputPass, String slat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SALT.length(); i++) {
            sb.append(SALT.charAt(i));
            if (i == 3) {
                sb.append(inputPass);
            }
        }
        return convert2MD5(sb.toString());
    }

    public static String inputPass2DBPass(String pass, String slat) {
        return formPass2DBPass(inputPass2FromPass(pass), slat);
    }
    private static String convert2MD5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static void main(String[] args) {
        System.out.println(inputPass2FromPass("123456"));
    }

}
