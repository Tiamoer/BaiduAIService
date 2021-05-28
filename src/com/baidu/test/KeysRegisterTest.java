package com.baidu.test;

import com.baidu.register.KeysRegister;

import java.io.IOException;

public class KeysRegisterTest {
    public static void main(String[] args) throws IOException {
        String[] keys = KeysRegister.getKeys(KeysRegister.Nlp);

        System.out.println(keys[0]);
        System.out.println(keys[1]);
        System.out.println(keys[2]);
    }
}
