package com.todorhryn.skyblox.game;

import com.todorhryn.skyblox.views.Alert;
import javafx.scene.control.PasswordField;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Password implements Serializable {
    private byte[] hash, salt;

    public Password(String password) {
        SecureRandom random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);

        hash = hashPassword(password);
    }

    private byte[] hashPassword(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            return factory.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Alert.showError("Error while generating hash", e.getLocalizedMessage());
            return null;
        }
    }

    public boolean check(String password) {
        return Arrays.equals(hash, hashPassword(password));
    }
}
