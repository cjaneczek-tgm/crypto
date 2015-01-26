package crypto.cipher.cbehavior;

public abstract interface CipherBehavior {

	public String encryptString(String text, String key);

	public String decryptString(String text, String key);

	public void generateKey();

}
