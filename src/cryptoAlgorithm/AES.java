package cryptoAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by Gabriel Wittes on 3/15/2016.
 * A class to encrypt and decrypt AES plaintext and ciphertext, as well as to generate AES keys.
 */
public class AES {
    /**
     * Returns a new secret AES key.
     * @return a secret AES key
     */
    public static SecretKey generateKey(){
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyGenerator.generateKey();
    }

    /**
     * Returns an AES-encrypted byte array given a plaintext string and a secret AES key.
     * @param plaintext a plaintext string
     * @param key a secret AES key
     * @return ciphertext
     */
    public static byte[] encrypt(String plaintext, SecretKey key){
        byte[] ciphertext = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            ciphertext = cipher.doFinal(plaintext.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    /**
     * Returns the plaintext of a given AES-encrypted string, and a secret AES key.
     * @param ciphertext an AES encrypted byte array
     * @param key a secret AES key
     * @return plaintext
     */
    public static String decrypt(byte[] ciphertext, SecretKey key){
        byte[] plaintext = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            plaintext = cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(plaintext);
    }
    
    public static void main(String[] arg) {
    	SecretKey sk;
    	PublicKey E4f=null;
    	PrivateKey E7h=null;
    	byte[] code;
    	String msg="mehdi moi",res="";
    	
    	System.out.println("msg.toString(): "+msg.toString());
		System.out.println("msg: "+msg);
		
    	sk=generateKey();
    	System.out.println("SecretKeyAlgorithm: "+sk.getAlgorithm());
    	System.out.println("SecretKeyFormat: "+sk.getFormat());
    	System.out.println("SecretKeyHashCode: "+sk.hashCode());
    	System.out.println("SecretKeyTostring: "+sk.toString());
    	System.out.println("SecretKey: "+sk);
    	
		code=encrypt(msg, sk);
		System.out.println("code:"+code);

		res=decrypt(code, sk);
		System.out.println("res:"+res);


    }
}