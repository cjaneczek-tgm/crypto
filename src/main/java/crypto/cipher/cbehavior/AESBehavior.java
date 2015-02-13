package crypto.cipher.cbehavior;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.apache.log4j.Logger;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * This class defines the behavior of the encryption technique known as the Advanced Encryption Standard (AES).
 *
 * @author Wolfgang Mair, Christian Janeczek
 * @version 27.01.2015
 */
public class AESBehavior implements CipherBehavior {

    Logger logger = org.apache.log4j.Logger.getLogger(AESBehavior.class);
    private String IV = "AAAAAAAAAAAAAAAA";
    private static AESBehavior instance = new AESBehavior();

    /**
     * AES happens
     */
    public String encryptString(String text, Key key, String algorithm) {

        try {

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] enc = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(enc);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES happens
     */
    public String decryptString(String text, Key key, String algorithm) {
        try {

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] dec = Base64.getDecoder().decode(text);
            return new String(cipher.doFinal(dec));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES Key-generation
     */
    public Key generateKey(String algorithm) {
        try {
            return KeyGenerator.getInstance(algorithm).generateKey();
        } catch (NoSuchAlgorithmException e) {
            logger.info("The algorithm known as AES could not be found!");
        }
        return null;
    }
    /**
     * Generates a symmetric key (AES for example)
     *
     * @param alg
     *            the Algorithm
     * @return the Key
     */
    public Key genKey(String alg) {
        try {
            return KeyGenerator.getInstance(alg).generateKey();
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }

    /**
     * Encodes the Public key to B64
     *
     * @param pubk
     *            the key
     * @return the encoded Key as String
     */
    public String encodePubK(PublicKey pubk) {
        KeyFactory fact = null;
        try {
            fact = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        X509EncodedKeySpec spec = null;
        try {
            spec = fact.getKeySpec(pubk, X509EncodedKeySpec.class);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(spec.getEncoded());
    }

    /**
     * Decodes the B64 Public key
     *
     * @param pubk
     *            the key as String
     * @return the decoded String/Key
     */
    public PublicKey decodePubK(String pk) {
        byte[] keyBytes = null;
        try {
            keyBytes = Base64.getDecoder().decode(pk.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e1) {

        }

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;

        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e1) {

        }

        try {
            return keyFactory.generatePublic(spec);
        } catch (InvalidKeySpecException e) {

        }

        return null;
    }
    public static AESBehavior get(){
        return instance;
    }

    @Override
    public String encryptString(String text, Key key) {
        return null;
    }

    @Override
    public String decryptString(String text, Key key) {
        return null;
    }

    @Override
    public Key generateKey() {
        return null;
    }
}
