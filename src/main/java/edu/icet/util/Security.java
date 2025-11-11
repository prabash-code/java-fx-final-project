package edu.icet.util;

import org.mindrot.jbcrypt.BCrypt;

public class Security {

    //hashing plain pw
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt(12));
    }

    //verifyng plain pw
    public static boolean verifyPassword(String plainPassword, String hashedPassword){
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash format");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
