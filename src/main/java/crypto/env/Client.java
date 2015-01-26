package crypto.env;

import crypto.com.ComSocket;
import crypto.cipher.cbehavior.CipherBehavior;

public class Client {

    private String serverName, publicKey, sharedKey;
    private int serverPort;
    private CipherBehavior cipherBehavior;

    /**
     * The Client constructor with the insecure plain-text transmission
     * @param serverName The server's address
     * @param serverPort The server's port
     * @param cipherBehavior The specified behavior of the cipher
     */
    public Client(String serverName, int serverPort, CipherBehavior cipherBehavior){
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.cipherBehavior = cipherBehavior;
    }

    /**
     * @see crypto.cipher.cbehavior.CipherBehavior#encryptString(java.lang.String, java.lang.String)
     */
    public String encryptString(String text, String key) {
        return this.cipherBehavior.encryptString(text,key);
    }

    /**
     * @see crypto.cipher.cbehavior.CipherBehavior#decryptString(java.lang.String, java.lang.String)
     */
    public String decryptString(String text, String key) {
        return this.cipherBehavior.decryptString(text,key);
    }

    /**
     * @see crypto.cipher.cbehavior.CipherBehavior#generateKey()
     */
    public void generateSharedKey() {
        //TODO The key-generation is missing... for now!
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public CipherBehavior getCipherBehavior() {
        return cipherBehavior;
    }

    public void setCipherBehavior(CipherBehavior cipherBehavior) {
        this.cipherBehavior = cipherBehavior;
    }
}
