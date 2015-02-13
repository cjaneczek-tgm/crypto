package crypto.env.start;

import crypto.cipher.cbehavior.AESBehavior;
import crypto.env.Client;
import crypto.env.Server;
import org.apache.log4j.Logger;

import java.security.Key;

public class StartEncryption {

	public static void main(String[] args) {

        Logger logger = org.apache.log4j.Logger.getLogger(StartEncryption.class);
        AESBehavior aesBehavior = new AESBehavior();

//        String[] clientArgs = {"127.0.0.1","202","secure"};
//        String[] serverArgs = {"202"};
//        Server.main(serverArgs);
//        Client.main(clientArgs);
//        String message = "Hello, I am a message!";
//        Key key = aesBehavior.generateKey();
//        logger.info("Message: '"+message+"'");
//        logger.info("Key: "+key);
//        String encrypted = aesBehavior.encryptString(message, key);
//        logger.info(encrypted);
//        String decrypted = aesBehavior.decryptString(encrypted, key);
//        logger.info(decrypted);
	}
}
