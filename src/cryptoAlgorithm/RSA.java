package cryptoAlgorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.util.Base64;

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
    public static byte[] encrypt(String plaintext, Key key){
        byte[] ciphertext = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
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
    public static String decrypt(byte[] ciphertext, Key key){
        byte[] plaintext = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            plaintext = cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(plaintext);
    }/*
    public String encryptText(String msg, PrivateKey key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException {
    	Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String decryptText(String msg, PublicKey key)
			throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}
    */
    private static byte[] blockCipher(byte[] bytes, int mode) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
    	// string initialize 2 buffers.
    	// scrambled will hold intermediate results
    	byte[] scrambled = new byte[0];
    	
    	Cipher cipher = Cipher.getInstance("RSA");
    	
    	// toReturn will hold the total result
    	byte[] toReturn = new byte[0];
    	// if we encrypt we use 100 byte long blocks. Decryption requires 128 byte long blocks (because of RSA)
    	int length = (mode == Cipher.ENCRYPT_MODE)? 100 : 128;

    	// another buffer. this one will hold the bytes that have to be modified in this step
    	byte[] buffer = new byte[length];

    	for (int i=0; i< bytes.length; i++){

    		// if we filled our buffer array we have our block ready for de- or encryption
    		if ((i > 0) && (i % length == 0)){
    			//execute the operation
    			scrambled = cipher.doFinal(buffer);
    			// add the result to our total result.
    			toReturn = append(toReturn,scrambled);
    			// here we calculate the length of the next buffer required
    			int newlength = length;

    			// if newlength would be longer than remaining bytes in the bytes array we shorten it.
    			if (i + length > bytes.length) {
    				 newlength = bytes.length - i;
    			}
    			// clean the buffer array
    			buffer = new byte[newlength];
    		}
    		// copy byte into our buffer.
    		buffer[i%length] = bytes[i];
    	}

    	// this step is needed if we had a trailing buffer. should only happen when encrypting.
    	// example: we encrypt 110 bytes. 100 bytes per run means we "forgot" the last 10 bytes. they are in the buffer array
    	scrambled = cipher.doFinal(buffer);

    	// final step before we can return the modified data.
    	toReturn = append(toReturn,scrambled);

    	return toReturn;
    }
    private static byte[] append(byte[] prefix, byte[] suffix){
    	byte[] toReturn = new byte[prefix.length + suffix.length];
    	for (int i=0; i< prefix.length; i++){
    		toReturn[i] = prefix[i];
    	}
    	for (int i=0; i< suffix.length; i++){
    		toReturn[i+prefix.length] = suffix[i];
    	}
    	return toReturn;
    }
    
    public static void main(String[] arg) {
    	KeyPair kp;
    	PublicKey E4f=null;
    	PrivateKey E7h=null;
    	byte[] code = null;
    	String msg="mehdi moi",res="";
    	
    	Base64.Encoder encoder = Base64.getEncoder();
		Base64.Decoder decoder = Base64.getDecoder();
    	kp=generateKeyPair();
    	
    	System.out.println("msg : "+msg);
    	System.out.println("### BEGIN ENCRYPTION ###");
    	try {
			code=blockCipher(kp.getPublic().getEncoded(),128);
		} catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//code=encrypt(encoder.encodeToString(kp.getPublic().getEncoded()), kp.getPrivate());
		System.out.println("### END ENCRYPTION ###");
		System.out.println("code : "+code);
		System.out.println("### BEGIN DECRYPTION ###");
		res=decrypt(code, kp.getPublic());
		System.out.println("### END DECRYPTION ###");
		System.out.println("res : "+res);
		
		
		

    }
    
    
}