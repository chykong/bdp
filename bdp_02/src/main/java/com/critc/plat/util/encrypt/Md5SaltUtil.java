package com.critc.plat.util.encrypt;

import java.security.MessageDigest;

/**
 * MD5加Salt加密用户密码
 *
 * @author 孔垂云
 * @date 2017年2月7日
 */
public class Md5SaltUtil {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private Object salt;
    private String algorithm = "MD5";

    public Md5SaltUtil(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public Md5SaltUtil(Object salt) {
        this.salt = salt;
    }

    /**
     * 加密密码
     *
     * @param rawPass
     * @return
     */
    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);
        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        String salt = "123456";
        Md5SaltUtil encoderMd5 = new Md5SaltUtil(salt);
        String encode = encoderMd5.encode("123456");
        System.out.println(encode);
        boolean passwordValid = encoderMd5.isPasswordValid("083a8db3ff5b9b4ece3ef2bde03226c8", "test");
        System.out.println(passwordValid);

		/*Md5SaltUtil encoderSha = new Md5SaltUtil(salt, "SHA");
        String pass2 = encoderSha.encode("test");
		System.out.println(pass2);
		boolean passwordValid2 = encoderSha.isPasswordValid("1bd98ed329aebc7b2f89424b5a38926e", "test");
		System.out.println(passwordValid2);*/
    }
}
