package Utills;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Hash Password using BCrypt Algorithm
 *
 * @author KhoiLeQuoc
 */
public class HashPW {

    /**
     * encoding password plaintext to encrypted password
     *
     * @param input
     * @return hash
     */
    public static String encode(String input) {
        String hash = BCrypt.hashpw(input, BCrypt.gensalt(9));
        return hash;
    }

    /**
     * check password is match with encrypted password
     *
     * @param input
     * @param password
     * @return
     */
    public static boolean decode(String input, String password) {
        return BCrypt.checkpw(input, password);
    }

}
