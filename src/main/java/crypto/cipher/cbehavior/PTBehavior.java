package crypto.cipher.cbehavior;

import java.security.KeyPair;

/**
 * This class defines the behavior of an insecure transmission without the use of any encryption technique.
 * @author Christian Janeczek
 * @version 26.01.2015
 */
public class PTBehavior implements CipherBehavior {

    public String encryptString(String text, String key) {
        return text;
    }

    public String decryptString(String text, String key) {
        return text;
    }

    public KeyPair generateKey() {
        return null;
    }
}
