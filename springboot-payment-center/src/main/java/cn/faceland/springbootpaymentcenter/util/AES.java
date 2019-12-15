package cn.faceland.springbootpaymentcenter.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 加解密工具类
 * 原文链接：https://blog.csdn.net/u013314786/article/details/80324358
 * @author lixk
 */
public class AES {
    //加解密算法名称
    private static final String ALGORITHM = "AES";
    //字符编码
    private static final String CHARSET = "UTF-8";

    /**
     * 生成秘钥
     *
     * @param password 加解密密码
     * @return
     * @throws Exception
     */
    private static SecretKey getSecretKey(String password) throws Exception {
        //获取密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        //初始化秘钥生成器，AES 生成的秘钥长度必须是 128
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes(CHARSET));
        keyGenerator.init(128, secureRandom);
        //生成密钥
        return keyGenerator.generateKey();
    }

    /**
     * AES 加密
     *
     * @param data     待加密的数据
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String password) throws Exception {
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
        //返回加密数据
        return cipher.doFinal(data);
    }

    /**
     * AES 加密
     *
     * @param data     待加密的数据
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String password) throws Exception {
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes(CHARSET), password));
    }

    /**
     * AES 解密
     *
     * @param data     待解密的数据
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String password) throws Exception {
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化为解密模式的密码器
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
        //返回加密数据
        return cipher.doFinal(data);
    }

    /**
     * AES 解密
     *
     * @param data     Base64加密的待解密的数据
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String password) throws Exception {
        return new String(decrypt(Base64.getDecoder().decode(data), password), CHARSET);
    }

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String data = "AES 加解密测试！";
        String password = "123456";
        String s1 = AES.encrypt(data, password);
        System.out.println("加密后的数据:" + s1);
        String s2 = AES.decrypt(s1, password);
        System.out.println("解密后的数据:" + s2);
    }
}