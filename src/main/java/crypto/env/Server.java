package crypto.env;

import crypto.com.ComSocket;
import crypto.cipher.cbehavior.CipherBehavior;

import java.security.KeyPair;

public class Server implements Runnable{

	private String privateKey, publicKey, sharedKey;
	private int port;
	private CipherBehavior cipherBehavior;

	public Server(int port, CipherBehavior cipherBehavior){
		this.port = port;
		this.cipherBehavior = cipherBehavior;
	}


	@Override
	public void run() {
        //TODO run()-method declaration missing
	}


	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#encryptString(java.lang.String, java.lang.String)
	 */
	public String encryptString(String text, String key) {
		return this.cipherBehavior.encryptString(text, key);
	}

	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#decryptString(java.lang.String, java.lang.String)
	 */
	public String decryptString(String text, String key) {
		return this.cipherBehavior.encryptString(text, key);
	}

	/**
	 * @see crypto.cipher.cbehavior.CipherBehavior#generateKey()
	 */
	public KeyPair generatePairedKey() {
		return cipherBehavior.generateKey();
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CipherBehavior getCipherBehavior() {
		return cipherBehavior;
	}

	public void setCipherBehavior(CipherBehavior cipherBehavior) {
		this.cipherBehavior = cipherBehavior;
	}


}
