package crypto.cipher.cbehavior;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

/**
 * This class defines the behavior of the encryption technique known as the Rivest-Shamir-Adleman Algorithm (RSA).
 * @author Christian Janeczek
 * @version 26.01.2015
 */
public class RSABehavior implements CipherBehavior {


	public String encryptString(String text, Key key, String alg) {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(alg);
        }
        catch (NoSuchAlgorithmException nsae) {
        }
        catch( NoSuchPaddingException nspe) {

        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key");
        }

        byte[] encVal = null;

        try {
            encVal = cipher.doFinal(text.getBytes());
        } catch (IllegalBlockSizeException e) {
            System.out.println("Wrong Blocksize!");
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(encVal);
	}


	public String decryptString(String text, Key key, String alg) {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(alg);
        }
        catch (NoSuchAlgorithmException nsae) {
        }
        catch( NoSuchPaddingException nspe) {

        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            System.out.println("Wrong key");
        }
        byte[] decodedValue = Base64.getDecoder().decode(text);

        byte[] decValue = null;
        try {
            decValue = cipher.doFinal(decodedValue);
        }
        catch (IllegalBlockSizeException ibse) {
        }
        catch( BadPaddingException bpe) {
        }
        String decryptedValue = new String(decValue);
        return decryptedValue;

	}


	public Key generateKey(String alg) {
        try {
            return KeyGenerator.getInstance(alg).generateKey();
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
	}

}
