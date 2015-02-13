package crypto.env;

import crypto.cipher.cbehavior.AESBehavior;
import crypto.com.ComSocket;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.Key;
import java.security.PublicKey;
import java.util.Base64;

/**
 * The following arguments are required: port
 *
 * @author Christian Janeczek, Wolfgang Mair
 * @version 2015-02-12
 */
public class Server {

    public static void main(String[] args) {

        Logger logger = org.apache.log4j.Logger.getLogger(Server.class);
        if (args.length != 1) {
            logger.info("Invalid arguments");
            System.exit(0);
        }
        int port = Integer.parseInt(args[0]);

        PublicKey clientPuK = null;
        Key sessionKey = null;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            logger.info("Server is ready and waiting for the client...");
            Socket client = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(
                    client.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(
                    client.getOutputStream());

            ComSocket mt = new ComSocket(client, oos, ois);

            boolean running = true;

            while (running) {

                // convert ObjectInputStream object to String
                String message = "";
                try {
                    message = (String) ois.readObject();
                } catch (ClassNotFoundException e) {
                    logger.info("Client dismissed!");
                }
                logger.info("The message was successfully received: " + message);

                String[] split = message.split("/", 2);
                String mode = split[0];
                String text = split[1];

                if (mode.equals("plain")) {
                    logger.info("The message was successfully received: "
                            + text);

                } else if (mode.equals("key")) {

                    logger.info("The key is being received...");
                    // Session creation
                    clientPuK = AESBehavior.get().decodePubK(text);

                    logger.info("The public key PK is being received: " + clientPuK);

                    //Generate and store key
                    sessionKey = AESBehavior.get().genKey("AES");

                    //Encode key for transmission
                    String encodedKey = Base64.getEncoder().encodeToString(sessionKey.getEncoded());
                    logger.info("The encoded key: " + encodedKey);

                    String toSend = AESBehavior.get().encryptString(encodedKey, clientPuK, "RSA");

                    mt.sendMessage("key/" + toSend);

                } else if (mode.equals("secure")) {

                    if(clientPuK == null){
                        logger.info("To use the secure transmission, you have to generate a key first!");
                    }else{
                        logger.info("The secure message was successfully received: " + AESBehavior.get().decryptString(text, sessionKey, "AES"));
                        logger.info("Server is now shutting down... Thanks for using me!");
                        System.exit(0);
                    }

                } else {
                }

            }

            // close resources
            mt.close(); //also closes oos, ois and socket
            serverSocket.close();
        } catch (SocketException se) {
            logger.info("Client dismissed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }



}
