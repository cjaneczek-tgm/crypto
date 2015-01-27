package crypto.env.start;

import crypto.cipher.cbehavior.AESBehavior;
import crypto.cipher.cbehavior.PTBehavior;
import crypto.cipher.cbehavior.RSABehavior;
import crypto.env.Client;
import crypto.env.Server;

import java.security.KeyPair;

public class StartEncryption {

	public void main(String[] args) {

		String pseudoServerName = "localhost";
		String pseudoPublicKey = "01001010101";
		String pseudoSharedKey = "10110101001";
		KeyPair keyPair;

		int pseudoServerPort = 8080;
		Server server = new Server(8080, new AESBehavior());
		Client client = new Client(pseudoServerName, pseudoServerPort, new AESBehavior());
		keyPair = server.generatePairedKey();
		server.encryptString(server.getPublicKey(),keyPair.getPublic().toString());
	}
}
