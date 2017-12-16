package cryptoAlgorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.*;

/**
 * Created by Gabriel Wittes on 3/15/2016.
 * A class to encrypt and decrypt RSA plaintext and ciphertext, as well as to generate RSA key pairs.
 */
public class RSA {
    /**
     * Returns a new RSA key pair.
     * @return public and private RSA keys
     */
    public static KeyPair generateKeyPair(){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Returns an RSA-encrypted byte array given a plaintext string and a public RSA key.
     * @param plaintext a plaintext string
     * @param key a public RSA key
     * @return ciphertext
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     */
    public static byte[] encrypt(String plaintext, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        byte[] ciphertext = null;
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            ciphertext = cipher.doFinal(plaintext.getBytes());
        return ciphertext;
    }

    /**
     * Returns the plaintext of a given RSA-encrypted string and a private RSA key.
     * @param ciphertext an RSA-encrypted byte array
     * @param key a private RSA key
     * @return plaintext
     * @throws Exception 
     * @throws NoSuchAlgorithmException 
     */
    public static String decrypt(byte[] ciphertext, Key key) throws NoSuchAlgorithmException, Exception{
        byte[] plaintext = null;
        
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            plaintext = cipher.doFinal(ciphertext);
        
        return new String(plaintext);
    }
    
    public static void main(String[] arg) {
    	KeyPair kp;
    	PublicKey E4f=null;
    	PrivateKey E7h=null;
    	byte[] code;
    	String msg="mehdi moi",res="";
    	
    	kp=generateKeyPair();
    	
    	System.out.println("msg : "+msg);
    	System.out.println("### BEGIN ENCRYPTION ###");
		code=encrypt(msg, kp.getPrivate());
		System.out.println("### END ENCRYPTION ###");
		System.out.println("code : "+code);
		System.out.println("### BEGIN DECRYPTION ###");
		res=decrypt(code, kp.getPublic());
		System.out.println("### END DECRYPTION ###");
		System.out.println("res : "+res);
		
		
		

    }
    
    
}