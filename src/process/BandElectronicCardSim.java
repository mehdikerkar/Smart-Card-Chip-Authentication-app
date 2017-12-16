package process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import process.BandPhysicalProcess.compt;


public class BandElectronicCardSim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	    class bank {
	    	int nBank;
	        String name;
	        long caisse;
	        //ArrayList<compt> compts;
	        RSAKeys BankKeyPair;
			int nbr=0;
			int nbrC=1;

			public RSAKeys getBankKeyPair() {
				return this.BankKeyPair;
			}
			public void setBankKeyPar(RSAKeys bankKeyPair) {
				this.BankKeyPair = bankKeyPair;
			}
			//ArrayList<KeyPair> listbankKeyPair;
	        
	        
	        bank() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	        	this.nBank=nbrC;
	        	this.name="";
	        	this.caisse=10^12;
	        	nbr++;
	        	RSAKeys rsakey = new RSAKeys();
	            rsakey.generatePaire(2048);
	            this.BankKeyPair=rsakey;
	            rsakey.save("Bankc"+nbr,"./Keys");
	            rsakey.saveBase64("Bankc"+nbr, "./Keys");
	            System.out.println("Generation of KeyPaire Bank and saving them in byte and string base64 is Done");
	        }
	    }
	    
	    

	    class card {
	    	int nCard;
	    	int nbr=0;
	    	String nameHolder;
	    	Date dateExp;
	    	String PIN;
	    	String info;

	    	byte[] VA;
	    	String VAString;
	    	Key Epub;
	    	byte[] Ecert;
	    	
	    	
	    	SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
	    	
	    	card(String name, String PIN, byte[] VA, Key Epub, byte[] Ecert){
	    		nbr++;
	    		this.nCard=nbr;										//
	    		this.nameHolder=name;								//     VA
	    		this.info=nameHolder+PIN+nbr;			//
	    		this.PIN=PIN;
	    	
		    	this.Epub=Epub;
		    	this.Ecert=Ecert;
	    	}
	    	public String getinfobyte() {
	    		return this.info;
	    	}
	    }

	    class terminalOperator {
	    	int nTO;
	    	String name;
	    	Key CApub;
	    	//ArrayList<Key> listCApub;
	    terminalOperator(int nbr, String name, Key CApub) {
	    	this.name= name;
	    	this.nTO=nbr;
	    	this.CApub=CApub;
		}
	    }
	    class certifcatAuthentification{
	    	int nCA;
	    	String name;
	    	RSAKeys CAKeyPair;
			int nbr=0;
	    	//ArrayList<KeyPair> listCAKeyPair;
	    	
	    	certifcatAuthentification() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
	    		RSAKeys rsakey = new RSAKeys();
	            rsakey.generatePaire(2048);
	            this.CAKeyPair=rsakey;
	            nbr++;
	            rsakey.save("CAc"+nbr,"./Keys");
	            rsakey.saveBase64("CAc"+nbr, "./Keys");
	            System.out.println("Generation of KeyPaire CA and saving them in byte and string base64 is Done");
	    	}
	    	
	    	public RSAKeys getCAKeyPair() {
				return this.CAKeyPair;
			}
			public void setCAKeyPar(RSAKeys CAKeyPair) {
				this.CAKeyPair = CAKeyPair;
			}
	    }


}
