package crypto.cipher.cbehavior;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * This class defines the behavior of the encryption technique known as the Rivest-Shamir-Adleman Algorithm (RSA).
 * @author Christian Janeczek
 * @version 26.01.2015
 */
public class RSABehavior implements CipherBehavior {

	public String encryptString(String text, Key key) {
		return null;
	}
    
	public String decryptString(String text, Key key) {
		return null;
	}

	public Key generateKey() {
//		try {
//			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//			kpg.initialize(2048);
//			return kpg.genKeyPair();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
        return null;
//	}
    }
}
