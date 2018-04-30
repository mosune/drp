package com.drp.util;

import com.drp.data.entity.AdminUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 *
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public class PasswordHelperUtil {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static AdminUser encryptPassword(AdminUser user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getAccount() + user.getSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);

        return user;
    }
}
