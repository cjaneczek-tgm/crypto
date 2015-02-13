package crypto.com;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Sends Strings via a Socket to a certain ip/port combination
 * @author Christian Janeczek, Wolfgang Mair
 * @version 2015-02-12
 */
public class ComSocket implements Closeable{

    Logger logger = org.apache.log4j.Logger.getLogger(ComSocket.class);
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ComSocket(String linkip, int linkport) {

        try {
            socket = new Socket(linkip, linkport);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            logger.info("Server not reached");
        }
    }

    public ComSocket(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
    }

    /**
     * Sends a message
     * @param message the message
     */
    public void sendMessage(String message) {
        try {
            logger.info("Sending message: " + message);
            oos.writeObject(message);
            logger.info("Message sent");
        } catch (IOException e) {
            logger.info("Error at sending message");
        }
    }

    /**
     * Closes everything that is used
     */
    @Override
    public void close(){
        try {
            socket.close();
            oos.close();
            ois.close();
        } catch (IOException e) {
            //...
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

}
