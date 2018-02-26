package process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import Rsa.Rsa;
import javafx.scene.control.TextArea;


public class BandElectronicCardSim {

	    public class Bank {
	    	//cards is a set of cards with there key(public,private)
	        ArrayList<Map> cards;
	        
	        public Bank() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	        	cards = new ArrayList<Map>();
	        }
	        
	        public Card createCard(String name, String PIN, CertificatAuthentification cert) throws Exception {
	        	
	    		Rsa rsa = new Rsa(512);
	    		Card c = new Card(name, PIN);
	    		String v = rsa.Crypter(c.getInfo(), rsa.getPrivateKey());
	    		String ec = cert.createCertification(c, rsa.getPublicKey());
	    		c.init(v, rsa, ec);
		    	cards.add(new Map(c, rsa));
		    	return c;
	        }
	        
	        public Rsa exist(Card c) {
	    		for(int i = 0; i < cards.size() ; i++) {
	    			if(cards.get(i).getC().equals(c))
	    				return cards.get(i).getR();
	    		}
	    		return null;
	    	}
	        
	        public ArrayList<Map> getCardList(){
	        	return cards;
	        }
	        
	    }
	    
	    public class BankDDA {
	    	//cards is a set of cards with there key(public,private)
	        ArrayList<Map> cards;
	        
	        public BankDDA() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	        	cards = new ArrayList<Map>();
	        }
	        
	        public CardDDA createCard(String name, String PIN, CertificatAuthentification cert) throws Exception {
	        	
	    		Rsa rsaC = new Rsa(512);
	    		Rsa rsaB = new Rsa(512);
	    		CardDDA c = new CardDDA(name, PIN);
	    		String ccert = rsaB.Crypter(rsaC.getPublicKey().toString(), rsaB.getPrivateKey());
	    		String ecert = cert.createCertification(c, rsaB.getPublicKey());
	    		c.init(rsaC, ccert, rsaB, ecert);;
		    	cards.add(new Map(c, rsaB));
		    	return c;
	        }
	        
	        public Rsa exist(CardDDA c) {
	    		for(int i = 0; i < cards.size() ; i++) {
	    			if(cards.get(i).getC().equals(c))
	    				return cards.get(i).getR();
	    		}
	    		return null;
	    	}
	        
	        public ArrayList<Map> getCardList(){
	        	return cards;
	        }
	        
	    }
	    
	    public static class Card {
	    	private int nCard;
	    	public static int nbr=0;
	    	private String nameHolder;
	    	private String PIN;
	    	private String info;
	    	private int util=0;
	    	public void setnCard(int nCard) {
				this.nCard = nCard;
			}

			public int getUtil() {
				return util;
			}
			
			public void setUtil(int util) {
				this.util = util;
			}

			private String VA;
	    	private Rsa Epub;
	    	private String Ecert;
	    	
	    	public Card(String name, String PIN){
	    		nbr++;
	    		this.nCard=nbr;										
	    		this.nameHolder=name;								
	    		this.info=nCard+"_"+nameHolder;		
	    		this.PIN=PIN;
	    	}
	    	
	    	public String getNameHolder() {
				return nameHolder;
			}

			public String getPIN() {
				return PIN;
			}

			public String getVA() {
				return VA;
			}

			public Rsa getEpub() {
				return Epub;
			}

			public void init(String v, Rsa e , String ec) {
	    		VA = v;
	    		Epub =e ;
	    		Ecert = ec;
	    	}
			
			public void init(Rsa e , String ec) {
	    		VA = null;
	    		Epub =e ;
	    		Ecert = ec;
	    	}
	    	
	    	public boolean verifierPIN(String PIN) {
	    		return this.PIN.equals(PIN);
	    	}
	    	
	    	public String getInfo() {
				return info;
			}



			public String getEcert() {
				return Ecert;
			}
			
			public boolean equals(Card c) {
				return this.nCard == c.getnCard();
			}

			public int getnCard() {
				return nCard;
			}
			
			public String toString() {
				return nCard+nameHolder;
			}
			
	    }
	    
	    public class CardDDA extends Card{
	    	private Rsa rsaC;
	    	private String Ccert;
	    	private BigInteger Calt;
	    	
	    	
	    	public BigInteger setCalt() {
	    		Calt = BigInteger.probablePrime(128, new Random());
				return BigInteger.probablePrime(128, new Random());
			}
	    	
	    	public BigInteger getCalt() {
	    		return Calt;
	    	}
	    	
	    	public CardDDA(String name, String PIN){
	    		super(name, PIN);
	    	}
	    	
			public String getCcert() {
				return Ccert;
			}
			
			public Rsa getRsaC() {
				return rsaC;
			}

			public void init(Rsa c ,String ccert , Rsa e , String ecert) {
	    		this.init(e, ecert);
				rsaC = c;
	    		Ccert = ccert;
	    	}
			
	    	
	    }

	    public class TerminalOperator {
	    	
	    	CertificatAuthentification authorety;
	    	BigInteger Talt;
	    	
	    	public TerminalOperator(CertificatAuthentification c) {
	    		authorety = c;
	    		
			}
	    	
	    	
	    	
	    	public BigInteger setTalt() {
	    		Talt = BigInteger.probablePrime(128, new Random());
				return BigInteger.probablePrime(128, new Random());
			}
	    	
	    	public BigInteger getTalt() {
	    		return Talt;
	    	}
	    	
	    	public boolean verifierInfo(Card c,TextArea t) throws Exception {
	    		System.out.println("aaa");
	    		t.appendText("###########################################################\n"
	    				+"Card Info : "+c.getInfo()
	    				+"\nCard VA : "+c.getVA()
	    				+"\nCard Ecert : "+c.getEcert()
	    				+"\nLooking for CApub!!!\n");
	    		System.out.println("aaa");
	    		Rsa Cpub = authorety.exist(c);
	    		if(Cpub != null) {
	    			t.appendText("###########################################################\n"
		    				+"CApub : "+c.getInfo()
		    				+"\nCalcule Epub!!!\n");
	    			String EpubS = Cpub.Decrypter(c.getEcert(), Cpub.getPublicKey());
	    			t.appendText("###########################################################\n"
		    				+"Epub : "+EpubS
		    				+"\nCalcule Info!!!\n");
	    			String info = c.getEpub().Decrypter(c.getVA(), new BigInteger(EpubS));
	    			t.appendText("###########################################################\n"
		    				+"Info : "+EpubS
		    				+"\n");
	    			if(info.equals(c.getInfo())) {
	    				t.appendText("Do transaction...");
	    				return true;
	    			}throw new Exception("Error: Hacked card");
	    		} throw new Exception("Error: card note authorized");
	    	}
	    	
	    	
	    	
	    }
	    public class CertificatAuthentification{
	    	
	    	private ArrayList<Map> keys;
	    	
	    	public CertificatAuthentification() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	    		keys = new ArrayList<Map>();
	    	}
	    	
	    	public String createCertification(Card c,BigInteger Epub) throws Exception {
	    		if(this.exist(c)==null) {
	    			Rsa rsa = new Rsa(512);
		    		keys.add(new Map(c, rsa));
		    		return rsa.Crypter(Epub.toString(), rsa.getPrivateKey());
	    		}else throw new Exception("Card all ready exist");
	    		
	    	}
	    	
	    	public Rsa exist(Card c) {
	    		for(int i = 0; i < keys.size() ; i++) {
	    			if(keys.get(i).getC().equals(c))
	    				return keys.get(i).getR();
	    		}
	    		return null;
	    	}
	    	
	    	public ArrayList<Map> getCardList(){
	        	return keys;
	        }
	    }
}
