package com.example.blog;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Value("${admin.jasypt.encryptKey}")
    private String encryptKey;

    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;

    @Test
    public void Encrypt() {
        String plainText = "DQAqWyciU2";
        String encryptText = stringEncryptor.encrypt(plainText);
        System.out.println(plainText + " : " + encryptText);
        plainText = "uyfr1doxc9";
        encryptText = stringEncryptor.encrypt(plainText);
        System.out.println(plainText + " : " + encryptText);
        plainText = "2f424e16978d71f3cf8e447554a3f3ff";
        encryptText = stringEncryptor.encrypt(plainText);
        System.out.println(plainText + " : " + encryptText);
        plainText = "root";
        encryptText = stringEncryptor.encrypt(plainText);
        System.out.println(plainText + " : " + encryptText);
        plainText = "0880";
        encryptText = stringEncryptor.encrypt(plainText);
        System.out.println(plainText + " : " + encryptText);
    }

}
