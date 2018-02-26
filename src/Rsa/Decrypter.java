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
public class Decrypter {
    public static String Decrypter(String texte, BigInteger d,BigInteger n,char c[],BigInteger[]va){
        BigInteger[]chiffré=valeur(texte,c,va);
        BigInteger[]déchiffré=new BigInteger[texte.length()];
        
        for(int i=0;i<texte.length();i++){
            déchiffré[i]=chiffré[i].modPow(d,n);  
        }
        String texDéchiffré="";
         
        for(int i=0;i<déchiffré.length;i++){
            texDéchiffré=texDéchiffré+ (char)(déchiffré[i].intValue());
                                           }
        return texDéchiffré;
                                                                                      }
    
    public static BigInteger[]valeur(String text,char c[],BigInteger[]va){
        BigInteger[]temp=new BigInteger[text.length()];
        for(int i=0;i<text.length();i++){
            for(int j=0;j<c.length;j++){
                if(c[j]==text.charAt(i)){
                    temp[i]=va[j];
                                        }
                                       }
                                         }
        return temp;
    }
    
}
