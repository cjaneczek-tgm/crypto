package crypto.env.start;

import crypto.cipher.cbehavior.AESBehavior;
import org.apache.log4j.Logger;

public class StartEncryption {

	public static void main(String[] args) {

        Logger logger = org.apache.log4j.Logger.getLogger(StartEncryption.class);
//		String pseudoServerName = "localhost";
//		String pseudoPublicKey = "01001010101";
//		String pseudoSharedKey = "10110101001";
//		KeyPair keyPair;
//
//		int pseudoServerPort = 8080;
//		Server server = new Server(8080, new AESBehavior());
//		Client client = new Client(pseudoServerName, pseudoServerPort, new AESBehavior());
//		keyPair = server.generatePairedKey();
//		server.encryptString(server.getPublicKey(),keyPair.getPublic().toString());
        AESBehavior aesBehavior = new AESBehavior();

        String message = "Hello, I am a message!";
        String key = aesBehavior.generateKey().toString();
        logger.info("Message: '"+message+"'");
        logger.info("Key: "+key);
        logger.info(aesBehavior.encryptString(message, key));

	}
}
