package com.jakers.mustneed;

import static org.assertj.core.api.Assertions.assertThat;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void jasypt_test() {
        String plain = "plainText";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("myPassword");
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encrypt = jasypt.encrypt(plain);
        String decrypt = jasypt.decrypt(encrypt);

        assertThat(plain).isEqualTo(decrypt);
    }

}
