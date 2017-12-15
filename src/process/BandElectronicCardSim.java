package process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import cryptoAlgorithm.RSA;


public class BandElectronicCardSim {

	    public class Bank {
	    	//cards is a set of cards with there key(public,private)
	        ArrayList<Map> cards;
	        
	        public Bank() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	        	cards = new ArrayList<Map>();
	        }
	        
	        public boolean createCard(String name, String PIN, CertificatAuthentification cert) throws Exception {
	        	
	    		RSAKeys rsa = new RSAKeys(2024);
	    		Card c = new Card(name, PIN);
	    		byte[] v = RSA.encrypt(c.getInfo(), rsa.getPrivateKey());
	    		byte[] ec = cert.createCertification(c, rsa.getPublicKey());
	    		c.init(v, rsa.getPublicKey(), ec);
		    	cards.add(new Map(c, rsa));
		    	return true;
	        }
	        
	        public Key exist(Card c) {
	    		for(int i = 0; i < cards.size() ; i++) {
	    			if(cards.get(i).getC().equals(c))
	    				return cards.get(i).getR().getPublicKey();
	    		}
	    		return null;
	    	}
	        
	    }
	    
	    

	    public class Card {
	    	private int nCard;
	    	private int nbr=0;
	    	private String nameHolder;
	    	private String PIN;
	    	private String info;

	    	private byte[] VA;
	    	private Key Epub;
	    	private byte[] Ecert;
	    	
	    	public Card(String name, String PIN){
	    		nbr++;
	    		this.nCard=nbr;										
	    		this.nameHolder=name;								
	    		this.info=nameHolder+PIN+nbr;		
	    		this.PIN=PIN;
	    	}
	    	
	    	public String getNameHolder() {
				return nameHolder;
			}

			public String getPIN() {
				return PIN;
			}

			public byte[] getVA() {
				return VA;
			}

			public Key getEpub() {
				return Epub;
			}

			public void init(byte[] v, Key e , byte[] ec) {
	    		VA = v;
	    		Epub =e ;
	    		Ecert = ec;
	    	}
	    	
	    	public boolean verifierPIN(String PIN) {
	    		return this.PIN.equals(PIN);
	    	}
	    	
	    	public String getInfo() {
				return info;
			}



			public byte[] getEcert() {
				return Ecert;
			}
			
			public boolean equals(Card c) {
				return this.nCard == c.getnCard();
			}

			public int getnCard() {
				return nCard;
			}
			
	    }

	    public class TerminalOperator {
	    	
	    	CertificatAuthentification authorety;
	    	
	    	public TerminalOperator(CertificatAuthentification c) {
	    		authorety = c;
			}
	    	
	    	
	    	public boolean verifierInfo(Card c) throws Exception {
	    		Key Cpub = authorety.exist(c);
	    		if(Cpub != null) {
	    			String EpubS = RSA.decrypt(c.getEcert(), Cpub);
	    			Key Epub = new SecretKeySpec(Base64.decode(EpubS),0,Base64.decode(EpubS).length,"RSA");
	    			String info = RSA.decrypt(c.getVA(), Epub);
	    			if(info.equals(c.getInfo())) {
	    				return true;
	    			}return false;
	    		} throw new Exception("Error: card note authorized");
	    	}
	    	
	    	
	    	
	    }
	    public class CertificatAuthentification{
	    	
	    	private ArrayList<Map> keys;
	    	
	    	public CertificatAuthentification() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	    		keys = new ArrayList<Map>();
	    	}
	    	
	    	public byte[] createCertification(Card c,Key Epub) throws Exception {
	    		if(this.exist(c)==null) {
	    			RSAKeys rsa = new RSAKeys(2024);
		    		keys.add(new Map(c, rsa));
		    		return RSA.encrypt(Base64.encode(Epub.getEncoded()), rsa.getPrivateKey());
	    		}else throw new Exception("Card all ready exist");
	    		
	    	}
	    	
	    	public Key exist(Card c) {
	    		for(int i = 0; i < keys.size() ; i++) {
	    			if(keys.get(i).getC().equals(c))
	    				return keys.get(i).getR().getPublicKey();
	    		}
	    		return null;
	    	}
	    }
}
