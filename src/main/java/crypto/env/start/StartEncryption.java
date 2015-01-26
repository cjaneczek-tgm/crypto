package crypto.env.start;

import crypto.cipher.cbehavior.AESBehavior;
import crypto.cipher.cbehavior.CipherBehavior;
import crypto.env.Server;
import crypto.env.Client;
import crypto.sniff.Sniffer;

public class StartEncryption {

	public void main(String[] args) {
		String pseudoServerName = "localhost";
		String pseudoPublicKey = "01001010101";
		String pseudoSharedKey = "10110101001";
		int pseudoServerPort = 8080;
		Client client = new Client(pseudoServerName, pseudoPublicKey, pseudoSharedKey, pseudoServerPort, new AESBehavior());
	}

}
