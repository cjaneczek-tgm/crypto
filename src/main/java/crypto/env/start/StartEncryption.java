package crypto.env.start;

import crypto.cipher.cbehavior.PTBehavior;
import crypto.env.Client;

public class StartEncryption {

	public void main(String[] args) {

		String pseudoServerName = "localhost";
		String pseudoPublicKey = "01001010101";
		String pseudoSharedKey = "10110101001";
		int pseudoServerPort = 8080;
		Client client = new Client(pseudoServerName, pseudoServerPort, new PTBehavior());

	}
}
