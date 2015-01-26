package crypto.cipher.cbehavior;

/**
 * The CipherBehavior Interface which defines method to encrypt and decrypt Strings as well as
 * generating keys for the specified technique.
 */
public abstract interface CipherBehavior {

	public String encryptString(String text, String key);

	public String decryptString(String text, String key);

	public void generateKey();

}
