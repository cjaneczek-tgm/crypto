package crypto.cipher.cbehavior;

import java.security.KeyPair;

/**
 * This class defines the behavior of the encryption technique known as the Data Encryption Standard (DES).
 * @author Christian Janeczek
 * @version 26.01.2015
 */
public class DESBehavior implements CipherBehavior {


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

	}

}
