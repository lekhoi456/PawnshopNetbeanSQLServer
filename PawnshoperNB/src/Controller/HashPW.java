/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author KhoiLeQuoc
 */
public class HashPW {

    public String encode(String input) {
        String hash = BCrypt.hashpw(input, BCrypt.gensalt(9));
        return hash;
    }

    public boolean decode(String input, String password) {
        return BCrypt.checkpw(input, password);
    }

}
