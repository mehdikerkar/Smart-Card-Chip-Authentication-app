package cryptoAlgorithm;

import javax.crypto.Cipher;
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
     */
    public static byte[] encrypt(String plaintext, PublicKey key){
        byte[] ciphertext = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OASP");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            ciphertext = cipher.doFinal(plaintext.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    /**
     * Returns the plaintext of a given RSA-encrypted string and a private RSA key.
     * @param ciphertext an RSA-encrypted byte array
     * @param key a private RSA key
     * @return plaintext
     */
    public static String decrypt(byte[] ciphertext, PrivateKey key){
        byte[] plaintext = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OASP");
            cipher.init(Cipher.DECRYPT_MODE, key);
            plaintext = cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(plaintext);
    }
    
    public static void main(String[] arg) {
    	KeyPair kp;
    	PublicKey E4f=null;
    	PrivateKey E7h=null;
    	byte[] code;
    	String msg="mehdi moi",res="";
    	
    	kp=generateKeyPair();
    	System.out.println("keypair.toString() : "+kp.toString());
    	System.out.println("keypair.hashCode() : "+kp.hashCode());
    	
    	System.out.println("keypair.getPrivate() : "+kp.getPrivate());
    	System.out.println("keypair.getPrivate().getAlgorithm() : "+kp.getPrivate().getAlgorithm());
    	System.out.println("keypair.getPrivate().getFormat() : "+kp.getPrivate().getFormat());
    	System.out.println("keypair.getPrivate().hashCode() : "+kp.getPrivate().hashCode());
    	System.out.println("keypair.getPrivate().toString() : "+kp.getPrivate().toString());
    	
    	System.out.println("keypair.getPublic() : "+kp.getPublic());
    	System.out.println("keypair.getPublic().getAlgorithm() : "+kp.getPublic().getAlgorithm());
    	System.out.println("keypair.getPublic().getFormat() : "+kp.getPublic().getFormat());
    	System.out.println("keypair.getPublic().hashCode() : "+kp.getPublic().hashCode());
    	System.out.println("keypair.getPublic().toString() : "+kp.getPublic().toString());
    	
		code=encrypt(msg, kp.getPublic());
		//System.out.println("code.toString() : "+code.toString());
		//System.out.println("code.hashCode() : "+code.hashCode());
		//System.out.println("code.length : "+code.length);
		//System.out.println("code.getClass() : "+code.getClass());
		
		res=decrypt(code, kp.getPrivate());
		System.out.println("résultat.toString() : "+res.toString());
		System.out.println("résultat.hashCode() : "+res.hashCode());
		System.out.println("résultat.length : "+res.length());
		System.out.println("résultat.getClass() : "+res.getClass());
		
		
		

    }
    
    
}