package crypto.cipher.cbehavior;

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

    public void generateKey() {
        //no key required for PlainText
    }
}
