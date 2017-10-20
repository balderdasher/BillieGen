package com.billiegen.common.security.shiro;

import com.billiegen.utils.security.DigestsUtil;
import com.billiegen.utils.security.EncodeUtil;

import static com.billiegen.common.security.shiro.ShiroConfig.HASH_INTERATIONS;
import static com.billiegen.common.security.shiro.ShiroConfig.SALT_SIZE;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
public class PasswordHelper {
    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = DigestsUtil.generateSalt(SALT_SIZE);
        byte[] hashPassword = DigestsUtil.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return EncodeUtil.encodeHex(salt) + EncodeUtil.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = EncodeUtil.decodeHex(password.substring(0, 16));
        byte[] hashPassword = DigestsUtil.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(EncodeUtil.encodeHex(salt) + EncodeUtil.encodeHex(hashPassword));
    }
}
