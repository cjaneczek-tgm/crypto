package crypto.cipher.cbehavior;

import java.security.KeyPair;

/**
 * This class defines the behavior of the encryption technique known as the CaesarEncryption.
 * @author Wolfgang Mair
 * @version 27.01.2015
 */
public class CaesarBehavior implements CipherBehavior {

    private int indexKey;

    public CaesarBehavior(int indexKey){
        this.indexKey = indexKey;
    }

    /**
     * A Method which shifts the given text an by the Constructor specified amount. It also is possible to define
     * the shift amount via String at the key parameter.
     * @param text The to be shifted text
     * @param key The shift amount as a String
     * @return   The shifted Text
     */
    public String encryptString(String text, String key) {

        try{
            this.indexKey = Integer.parseInt(key);
        }
        catch(NumberFormatException nfe){

        }
        catch(NullPointerException npe){

        }

        String s = "";
        int len = text.length();
        for(int x = 0; x < len; x++) {
            if (text.charAt(x) >= 'a' && text.charAt(x) <= 'z') {
                char c = (char) (text.charAt(x) + indexKey);
                if (c > 'z')
                    s += (char) (text.charAt(x) - (26 - indexKey));
                else
                    s += (char) (text.charAt(x) + indexKey);
            }
            else
                s += (char) (text.charAt(x));
        }
        return s;
    }

    /**
     * A Method which de-Shifts the given text an by the Constructor specified amount. It also is possible to define
     * the shift amount via String at the key parameter.
     * @param text The to be shifted text
     * @param key The shift amount as a String
     * @return   The shifted Text
     */
    public String decryptString(String text, String key) {

        try{
            this.indexKey = Integer.parseInt(key);
        }
        catch(NumberFormatException nfe){

        }
        catch(NullPointerException npe){

        }

        String s = "";
        int len = text.length();
        for(int x = 0; x < len; x++) {
            if (text.charAt(x) >= 'a' && text.charAt(x) <= 'z') {
                char c = (char) (text.charAt(x) - indexKey);
                if (c < 'a')
                    s += (char) (text.charAt(x) + (26 - indexKey));
                else
                    s += (char) (text.charAt(x) - indexKey);
            }
            else
                s += (char) (text.charAt(x));
        }
        return s;
    }

    /**
     * @see crypto.cipher.cbehavior.CipherBehavior#generateKey()
     */
    public KeyPair generateKey() {
        return null;
    }

}