/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rsa;
import java.math.BigInteger;
/**
 *
 * @author mariya
 */
public class Crypter {
    
    public static String CrypterTexte(String texte,BigInteger e,BigInteger n,char c[],BigInteger[] va){
        BigInteger []chiffré=new BigInteger[texte.length()];
        for(int i=0;i<texte.length();i++){
            BigInteger grandNB=new BigInteger(String.valueOf(texte.charAt(i)+0));
            grandNB=grandNB.modPow(e, n);
            chiffré[i]=grandNB;
                                         }
        return caractere_passe(chiffré,c,va);
    }
    public static String caractere_passe(BigInteger chiffré[],char c[],BigInteger[] va){
        String chaine="";
        for(int i=0;i<chiffré.length;i++){
            for(int j=0;j<va.length;j++){
                if(chiffré[i].toString().compareToIgnoreCase(va[j].toString())==0){
                  chaine=chaine+c[j];  
                                                                                  }
                                        }
                                        }
        return chaine;
    }
}
