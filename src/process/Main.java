package process;

import interfaces.ConsoleProgressBar;
import interfaces.TextAreaOutputStream;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cryptoAlgorithm.RSA;
import process.BandElectronicCardSim.bank;
import process.BandElectronicCardSim.card;
import process.BandElectronicCardSim.certifcatAuthentification;
import process.BandElectronicCardSim.terminalOperator;

public class Main {

		static private RSAKeys RSAKeyB, RSAKeyCA;	
	
		private static bank Bank;
		private static card Card;
		private static certifcatAuthentification CA;
		private static terminalOperator ATM;
		
		static byte[] VA, _Ecert;
		static byte[] Ecert,Ecert1,Ecert2;
		static String info, _info;
		
		static Key Epub,_Epub, Epriv, CApub, CApriv;
		static ConsoleProgressBar probar;
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeySpecException{
		/*		// Jframe and consol 
		JFrame frame = new JFrame();
        frame.add( new JLabel(" Outout" ), BorderLayout.NORTH );

        JTextArea ta = new JTextArea();
        TextAreaOutputStream taos = new TextAreaOutputStream( ta, 60 );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );
        frame.add( new JScrollPane( ta )  );
        frame.pack();
        frame.setVisible( true );
        frame.setSize(800,600);
      */  
        
		//BandElectronicCardSim.Bank name = new Bank();
		BandElectronicCardSim becs = new BandElectronicCardSim();
		
		String nameCard = "Paul Smith";
		String PIN = "PIN4367",_PIN;
		Scanner scanIn = new Scanner(System.in);
		info = nameCard+PIN;
		
		Bank = becs.new bank();
		RSAKeyB = new RSAKeys(2048);
		CA = becs.new certifcatAuthentification();
		RSAKeyCA= new RSAKeys(2048);
		
		
		// ilfaudrait affichage cration clés OK
		
		//------------------1 affectation KeyPair Bank+CA
		Epub = Bank.getBankKeyPair().getPublicKey();
		Epriv = Bank.getBankKeyPair().getPrivateKey();
		CApub = CA.getCAKeyPair().getPublicKey();
		CApriv = CA.getCAKeyPair().getPrivateKey();

		
		verificationEKeys(RSAKeyB);//-------------- KeyPairBank Verification
		verificationCAKeys(RSAKeyCA);//-------------- KeyPairCA Verification
		//---------------------------------------------------------------------------------------------------------------------------
		Base64.Encoder encoder = Base64.getEncoder();
		Base64.Decoder decoder = Base64.getDecoder();
		System.out.println("debut encryption");
		//--------------------------------Encryption
		VA= RSA.encrypt(info, Epriv);// encryption of info with Epriv
		System.out.println("fin encryption info");
		System.out.println(Epub.getEncoded().length);
		System.out.println(encoder.encodeToString(Epub.getEncoded()));
		
		//String Epub1s = substring(0, (((CharSequence) Epub).length()/2), encoder.encodeToString(Epub.getEncoded()));
		//String Epub2s = substring((((CharSequence) Epub).length()/2),((CharSequence) Epub).length(), encoder.encodeToString(Epub.getEncoded()));
		//System.out.println(Epub1s);
		//System.out.println(Epub2s);
		//Ecert1 = RSA.encrypt(Epub1s, CApriv);//encryption of Epub with CApriv
		//Ecert2 = RSA.encrypt(Epub2s, CApriv);
		Ecert=RSA.encrypt(encoder.encodeToString(Epub.getEncoded()), CApriv);
		
		System.out.println("");
		System.out.println("fin encryption Epub");
		System.out.println("fin encryption");
		//------------------2 creation and attribution information Card + ATM
		ATM = becs.new terminalOperator(1, "ATM Sanfransisco", CApub);
		Card = becs.new card(nameCard, PIN, VA, Epub, Ecert);
		
		//---------------------------------- insertion (Card,ATM) begin test
		//------------------3 decryption  Epub
		_Epub = RSAKeyCA.publicKeyFromKeySpec(decoder.decode(RSA.decrypt(Ecert, CApub)));
		//------------------5 decryption  VA
		_info= RSA.decrypt(VA, Epub);
		
		//probar= new ConsoleProgressBar("authentification","Epub","Done",100);
		//if the result _Epub is equal at Epub past are test info
		if (authentificationEpub(Epub,_Epub)) {	
			 
			//probar= new ConsoleProgressBar("authentification","info","Done",100);
			if(authentificationInfo(info, _info)){	
				System.out.println("Tape your password");
				int i=0;
			    while (info!=_info || i<3) {
			    	
			    	  _info = scanIn.nextLine();
			                  if (info==_info) {
			                	  //probar= new ConsoleProgressBar("authentification","info","Done",100);
			                	  System.out.println("Enter : Your password is right");
			                  }else {System.out.println("Error : Your password is wrong  Repaet again");}
			    }
			    scanIn.close(); 
			}
			else{
			}
		}
		
		
	}
	private static String substring(int i, int j, String encodeToString) {
		// TODO Auto-generated method stub
		return encodeToString;
	}
	public static boolean verificationEKeys(RSAKeys RSAKeyB) {
		System.out.println("Verification of generated Key Pair Bank (private,Public)");
		
		if (RSAKeyB.isPrivateReady() && RSAKeyB.isPublicReady()) {
			System.out.println("The generation is done");	
			return true;
		}else {
			System.out.println("Error : the generation is wrong");	
			return false;
		}
		
	}
	public static boolean verificationCAKeys(RSAKeys RSAKeyCA) {
		System.out.println("Verification of generated Key Pair CA (private,Public)");
		
		if (RSAKeyCA.isPrivateReady() && RSAKeyCA.isPublicReady()) {
			System.out.println("The generation is done");	
			return true;
		}else {
			System.out.println("Error : the generation is wrong");	
			return false;
		}
		
	}
	public static boolean authentificationEpub(Key Epub, Key _Epub) {
		if (Epub.equals(_Epub)) {
			System.out.println("Enter : The Ecert is Sure");
			return true;
		}else {
			System.out.println("Error : The Ecert is been hacked");			
			return false;
		}
	/*	if (Epub == _Epub) {
			System.out.println("Enter : The Ecert is Sure");
			return true;
		}
		throw new Exception ("Error : The Ecert is been hacked");	
		*/
	}
	public static boolean authentificationInfo(String info, String _info) {
		if (info.equals(_info)) {
			System.out.println("Enter : The VA is Sure");
			return true;
		}else
			System.out.println("Error : The VA is been hacked");
			return false;
	}

}
