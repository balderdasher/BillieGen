package com.billiegen.common.security.shiro;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
public class PasswordHelperTest {
    @Test
    public void entryptPassword() throws Exception {
        String passwordInput = "123456";
        String password = PasswordHelper.entryptPassword(passwordInput);
        System.out.println(password);
    }

    @Test
    public void validatePassword() throws Exception {
        String password = "c8032de716223f488c2bd2dac80aa475081bed83ad829846f1aed6af";
        assertTrue(PasswordHelper.validatePassword("123456", password));
    }
}