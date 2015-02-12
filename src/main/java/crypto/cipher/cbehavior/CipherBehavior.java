package crypto.cipher.cbehavior;

import java.security.Key;
import java.security.KeyPair;

/**
 * The CipherBehavior Interface which defines method to encrypt and decrypt Strings as well as
 * generating keys for the specified technique.
 */
public abstract interface CipherBehavior {

	public String encryptString(String text, Key key, String alg);

	public String decryptString(String text, Key key, String alg);

	public Key generateKey(String alg);

}
