package crypto.env;

import crypto.cipher.cbehavior.AESBehavior;
import crypto.com.ComSocket;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

/**
 * The following arguments are required:
 * ip, port, mode(plain/secure)
 *
 * @author Christian Janeczek, Wolfgang Mair
 * @version 2015-02-12
 */
public class Client {

    public static void main(String[] args) {

        Logger logger = org.apache.log4j.Logger.getLogger(Client.class);
        if (args.length != 3) {
            logger.info("Invalid arguments");
            System.exit(0);
        }

        String ip = args[0];
        int port = Integer.parseInt(args[1]);

        String mode = args[2];

        logger.info("Using the following mode (plain/secure): " + mode);

        ComSocket mt = new ComSocket(ip, port);

        String message = "Hello, I am a message!";
        Key sessionKey = null; //sessionKey Key use for secure com

        //Start messaging
        if (mode.equals("plain")) {
            mt.sendMessage("plain/" + message);
        } else if (mode.equals("secure")) {

            // RSA key generation
            KeyPairGenerator keyGen = null;
            try {
                keyGen = KeyPairGenerator
                        .getInstance("RSA");
            } catch (NoSuchAlgorithmException e) {

            }
            keyGen.initialize(1024);

            KeyPair keyPair = keyGen.generateKeyPair();

            mt.sendMessage("key/" + AESBehavior.get().encodePubK(keyPair.getPublic()));

            boolean session = true;

            while (session) {

                // convert ObjectInputStream object to String
                String response = "";
                try {
                    response = (String) mt.getOis().readObject();
                } catch (ClassNotFoundException cnfe){
                } catch (IOException e) {

                }

                logger.info("DEBUG: Message Received: " + response);

                String[] split = response.split("/", 2);
                String type = split[0];
                String text = split[1];

                if(type.equals("key")){

                    String sessionKeyString = AESBehavior.get().decryptString(text, keyPair.getPrivate(), "RSA");
                    logger.info("AESKey: " + sessionKeyString);

                    // decode the base64 encoded string
                    byte[] decodedKey = Base64.getDecoder().decode(sessionKeyString);
                    // rebuild key using SecretKeySpec
                    sessionKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                    //Session generation finished!
                    session = false;
                }
            }

            //Starting secure communication
            mt.sendMessage("secure/" + AESBehavior.get().encryptString(message, sessionKey, "AES"));
        }

        mt.close();
    }

}
