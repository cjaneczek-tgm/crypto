package crypto.env;

import crypto.com.ComSocket;
import crypto.cipher.cbehavior.CipherBehavior;

public abstract class Client implements CipherBehavior {

	private String serverName;

	private int serverPort;

	private String publicKey;

	private String sharedKey;


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
	public void generateKey() {

	}

}
