package process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64.Decoder;
import java.util.Base64;

class RSAKeys {
    private Key publicKey;
    private Key privateKey;
    private boolean debug;
    private KeyFactory keyfactory;

    public void debug(String msg) {
        if (this.debug) {
            System.out.println(msg);
        }
    }
    public String pathWithoutLastSlash(String path) {
        int pathLength = path.length();
        if (path.charAt(pathLength - 1) == '/') {
            return path.substring(0, pathLength - 1);
        }
        return path;
    }
    public void init() throws NoSuchAlgorithmException {
        this.publicKey = null;
        this.privateKey = null;
        this.debug = false;
        this.keyfactory = KeyFactory.getInstance("RSA");
    }
    RSAKeys() throws NoSuchAlgorithmException {
        init();
    }
    RSAKeys(int size) throws NoSuchAlgorithmException {
        init();
        generatePaire(size);
    }

    RSAKeys(String fileName, String diroPath) throws NoSuchAlgorithmException, InvalidKeySpecException {
        init();
        loadKeys(fileName, diroPath);
    }

    public void generatePaire(int size) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.generateKeyPair();
        this.publicKey = kp.getPublic();
        this.privateKey = kp.getPrivate();
    }
    /**
     * String fileName  (name of file without extension)
     * */
    public Boolean save(String fileName) throws FileNotFoundException, IOException { //***********note you need to treat exception and return false in case of an exception */
        FileOutputStream out = new FileOutputStream(fileName + ".key");
        out.write(this.privateKey.getEncoded());
        out.close();

        out = new FileOutputStream(fileName + ".pub.key");
        out.write(this.publicKey.getEncoded());
        out.close();

        return true;
    }

    /**
     * String fileName  (name of file without extension)
     * String diroPath (path of the directory where to save the file)
     */
    public Boolean save(String fileName, String diroPath) throws FileNotFoundException, IOException {
        diroPath = pathWithoutLastSlash(diroPath);
        return save(diroPath + "/" + fileName);
    }

    public byte[] readBytesFromFile(String filePath) {
        Path path = Paths.get(filePath); // creating a Path object from the string path
        try {
            byte[] bytes = Files.readAllBytes(path); // reading the file pointed by the Path object, and store it in a byte[] array   
            return bytes;
        } catch (IOException e) {
            System.out.println("failed to open key files! may not exist or permission needed!");
            return null;
        }
    }

    public Key privateKeyFromKeySpec(byte[] bytes) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(bytes);
        return this.keyfactory.generatePrivate(priKeySpec);
    }

    public Key publicKeyFromKeySpec(byte[] bytes) throws InvalidKeySpecException {
        X509EncodedKeySpec pubkeySpec = new X509EncodedKeySpec(bytes);
        return this.keyfactory.generatePublic(pubkeySpec);
    }

    public Boolean loadKeys(String fileName, String diroPath) throws NoSuchAlgorithmException, InvalidKeySpecException {
        diroPath = pathWithoutLastSlash(diroPath);

        //------------
        /* Read all the private key bytes */
        byte[] bytes = readBytesFromFile(diroPath + "/" + fileName + ".key");
        if (bytes == null) {
            return false;
        }
        /* Generate private key. */
        this.privateKey = privateKeyFromKeySpec(bytes);

        //-------------

        /* reading public key bytes */
        bytes = readBytesFromFile(diroPath + "/" + fileName + ".pub.key");
        if (bytes == null) {
            return false;
        }
        /* Generate public key. */
        this.publicKey = publicKeyFromKeySpec(bytes);

        return true;
    }

    public Boolean loadPublicKey(String file) throws NoSuchAlgorithmException, InvalidKeySpecException { // you give the fall file path
        byte[] bytes = readBytesFromFile(file);
        if (bytes == null) {
            return false;
        }
        /* Generate public key. */
        this.publicKey = publicKeyFromKeySpec(bytes);
        return true;
    }

    public Boolean loadPrivateKEY(String file) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = readBytesFromFile(file);
        if (bytes == null) {
            return false;
        }

        /* Generate private key. */
        this.privateKey = privateKeyFromKeySpec(bytes);

        return true;
    }


    public Key getPublicKey() {
        return this.publicKey;
    }

    public Key getPrivateKey() {
        return this.privateKey;
    }

    public Boolean isPrivateReady() {
        if (this.privateKey == null) {
            return false;
        }
        return true;
    }

    public Boolean isPublicReady() {
        if (this.publicKey == null) {
            return false;
        }
        return true;
    }

    public void testKeySaveFormat() {
        System.err.println("Private key format: " + this.privateKey.getFormat());
        // prints "Private key format: PKCS#8" on my machine

        System.err.println("Public key format: " + this.publicKey.getFormat());
        // prints "Public key format: X.509" on my machine
    }

    public String getBase64PublicKeyRepresentation() {
        Base64.Encoder encoder = Base64.getEncoder();
        String base64PublicKeyRep_str = "";
        base64PublicKeyRep_str += "-----BEGIN RSA PUBLIC KEY-----\n";
        base64PublicKeyRep_str += encoder.encodeToString(this.publicKey.getEncoded());
        base64PublicKeyRep_str += "\n-----END RSA PUBLIC KEY-----\n";
        return base64PublicKeyRep_str;
    }

    public String getBase64privateKeyRepresentation() {
        Base64.Encoder encoder = Base64.getEncoder();
        String base64privateKeyRep_str = "";
        base64privateKeyRep_str += "-----BEGIN RSA PRIVATE KEY-----\n";
        base64privateKeyRep_str += encoder.encodeToString(this.privateKey.getEncoded());
        base64privateKeyRep_str += "\n-----END RSA PRIVATE KEY-----\n";
        return base64privateKeyRep_str;
    }

    public Boolean saveBase64(String fileName, String diroPath) {
        diroPath = pathWithoutLastSlash(diroPath);
        try {
            // writing the public file
            PrintWriter out = new PrintWriter(diroPath + "/" + fileName + ".base64.pub.key");
            out.write(this.getBase64PublicKeyRepresentation());
            out.close();
            // writing the private file
            out = new PrintWriter(diroPath + "/" + fileName + ".base64.key");
            out.write(this.getBase64privateKeyRepresentation());
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * file the complete path to the file
     */
    public Boolean loadBase64_privatekey(String file) throws InvalidKeySpecException {
        // loading private key 
        try {
            String privateKeyBase64_str = new String(Files.readAllBytes(Paths.get(file)));
            //decoding base 64
            privateKeyBase64_str = privateKeyBase64_str.replace("-----BEGIN RSA PRIVATE KEY-----\n", "");
            privateKeyBase64_str = privateKeyBase64_str.replace("\n-----END RSA PRIVATE KEY-----\n", "");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] privateKeySpec = decoder.decode(privateKeyBase64_str);
            //getting the privatekey from the spec (generating)
            this.privateKey = privateKeyFromKeySpec(privateKeySpec);

            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public Boolean loadBase64_publickey(String file) throws InvalidKeySpecException {
        // loading private key 

        try {
            String publicKeyBase64_str = new String(Files.readAllBytes(Paths.get(file)));
            //decoding base 64
            publicKeyBase64_str = publicKeyBase64_str.replace("-----BEGIN RSA PUBLIC KEY-----\n", "");
            publicKeyBase64_str = publicKeyBase64_str.replace("\n-----END RSA PUBLIC KEY-----\n", "");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] publicKeySpec = decoder.decode(publicKeyBase64_str);
            //getting the privatekey from the spec (generating)
            this.publicKey = publicKeyFromKeySpec(publicKeySpec);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean loadBase64(String fileName, String diroPath) throws IOException, InvalidKeySpecException {
        diroPath = pathWithoutLastSlash(diroPath);

        // loading private key
        if (!loadBase64_privatekey(diroPath + "/" + fileName + ".base64.key")) {
            return false;
        }

        // loading public key
        if (!loadBase64_publickey(diroPath + "/" + fileName + ".base64.pub.key")) {
            return false;
        }

        return true;
    }

}



public class MyRSAKeys {

    public static void main(String[] argv) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeySpecException {
        //generating test
        // RSAKeys rsakey = new RSAKeys();
        // rsakey.generatePaire(2048);
        // System.out.println(rsakey.getBase64privateKeyRepresentation());
        // System.out.println("---------------");
        // System.out.println(rsakey.getBase64PublicKeyRepresentation());
        // rsakey.save("test","./Keys");


        // // loading test
        // RSAKeys rsakey = new RSAKeys("test", "./Keys");
        // System.out.println(rsakey.getBase64privateKeyRepresentation());
        // System.out.println("---------------");
        // System.out.println(rsakey.getBase64PublicKeyRepresentation());

        // // // saving base64
        // rsakey.saveBase64("test","./Keys");

        //loading only publicKey
        // RSAKeys rsakey = new RSAKeys();
        // rsakey.loadPublicKey("./Keys/test.pub.key");
        // System.out.println(rsakey.getBase64PublicKeyRepresentation());

        // //loading base64
    	/**
    	 * RSAKeys rsakey = new RSAKeys();
        rsakey.loadBase64("test", "./Keys");
        System.out.println(rsakey.getBase64privateKeyRepresentation());
        System.out.println("---------------");
        System.out.println(rsakey.getBase64PublicKeyRepresentation());
    	 * 
    	 * 
    	 * 
    	 * */
        
    }
}