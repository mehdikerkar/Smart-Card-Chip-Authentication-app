/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rsa;

import java.math.BigInteger;

public class Rsa {
	
	private Cles cle;
	private	 Caracteres car;
	private String code="",res="";
	
	public Rsa(int bitLength) {
		
		cle = new Cles(bitLength);
		car = new Caracteres(cle.getN(), cle.getE());
		car.generer();
	}
	
	public String Crypter(String msg) {
		System.out.println("debut cryptage RSA");
		code=Crypter.CrypterTexte(msg,cle.getE(),cle.getN(),car.getCaractere(),car.getValeur());
		System.out.println("le msg crypte : "+code);
		return code;
	}
	public String Decrypter(String code) {
		System.out.println("debut decryptage RSA");
		res=Decrypter.Decrypter(code, cle.getD(), cle.getN(), car.getCaractere(), car.getValeur());
		System.out.println("le resulat  decrypte : "+res);
		return res;
	}
	public String Crypter(String msg,BigInteger e) {
		System.out.println("debut cryptage RSA");
		code=Crypter.CrypterTexte(msg,e,cle.getN(),car.getCaractere(),car.getValeur());
		System.out.println("le msg crypte : "+code);
		return code;
	}
	public String Decrypter(String code, BigInteger d) {
		System.out.println("debut decryptage RSA");
		res=Decrypter.Decrypter(code, d, cle.getN(), car.getCaractere(), car.getValeur());
		System.out.println("le resulat  decrypte : "+res);
		return res;
	}
	public BigInteger getPrivateKey() {
		return cle.getE();
	}public BigInteger getPublicKey() {
		return cle.getD();
	}
}
