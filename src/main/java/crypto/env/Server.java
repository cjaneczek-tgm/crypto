package crypto.env;

import crypto.com.ComSocket;
import crypto.cipher.cbehavior.CipherBehavior;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

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
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Waiting for client");
            Socket client = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(
                    client.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(
                    client.getOutputStream());

            MessageTransmitter mt = new MessageTransmitter(client, oos, ois);

            boolean running = true;

            while (running) {

                // convert ObjectInputStream object to String
                String message = "";
                try {
                    message = (String) ois.readObject();
                } catch (ClassNotFoundException e) {
                    System.out.println("Client disconnected!");
                }
                System.out.println("DEBUG: Message Received: " + message);

                String[] split = message.split("/", 2);
                String mode = split[0];
                String text = split[1];

                if (mode.equals("plain")) {
                    System.out.println("Plaintext Message Received! Content: "
                            + text);

                } else if (mode.equals("key")) {

                    System.out.println("Receiving Key...");
                    // Session creation
                    clientPuK = CryptoToolkit.get().decodePubK(text);

                    System.out.println("Public Key received: " + clientPuK);

                    //Generate and store key
                    sharedKey = CryptoToolkit.get().genKey("AES");

                    //Encode key for transmission
                    String encodedKey = Base64.getEncoder().encodeToString(sessionKey.getEncoded());
                    System.out.println("Encoded Key: " + encodedKey);

                    String toSend = CryptoToolkit.get().encrypt(encodedKey, clientPuK, "RSA");

                    mt.sendMessage("key/" + toSend);

                } else if (mode.equals("secure")) {

                    if(clientPuK == null){
                        System.out.println("Generate a key first!");
                    }else{
                        System.out.println("Secure Message received: " + CryptoToolkit.get().decrypt(text, sessionKey, "AES"));
                    }

                } else {
                    // This message is using the wrong protocol or an attack
                }

            }

            // close resources
            mt.close(); //also closes oos, ois and socket
            serverSocket.close();
        } catch (SocketException se) {
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	public String encryptString(String text, Key key, String alg) {
		return this.cipherBehavior.encryptString(text, key, alg);
	}


	public String decryptString(String text, Key key, String alg) {
		return this.cipherBehavior.encryptString(text, key, alg);
	}


	public KeyPair generatePairedKey() {
        // RSA key generation
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator
                    .getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {

        }
        keyGen.initialize(1024);

       return keyGen.generateKeyPair();

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
