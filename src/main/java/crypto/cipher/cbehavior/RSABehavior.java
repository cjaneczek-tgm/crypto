package crypto.cipher.cbehavior;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * This class defines the behavior of the encryption technique known as the Rivest-Shamir-Adleman Algorithm (RSA).
 * @author Christian Janeczek
 * @version 26.01.2015
 */
public class RSABehavior implements CipherBehavior {

	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#encryptString(java.lang.String, java.lang.String)
	 */
	public String encryptString(String text, String key) {
		return null;
	}

	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#decryptString(java.lang.String, java.lang.String)
	 */
	public String decryptString(String text, String key) {
		return null;
	}

	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#generateKey()
	 */
	public KeyPair generateKey() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
