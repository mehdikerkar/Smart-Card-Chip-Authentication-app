/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rsa;
import java.math.BigInteger;

public class Caracteres {
    private char caractere[]=new char[294];
    private BigInteger valeur[]=new BigInteger[294];
    private BigInteger e;
    private BigInteger n;
    
    
    public Caracteres(BigInteger n,BigInteger e){
        this.e=e;
        this.n=n;
    
                                                }
    public void generer(){
        int a=32;
        int aa=(int) (32+1+Math.random()*5);
        char b;
        for(int i=0;i<294;i++){//génère le code cos pour chaque caractère
            if(a==127){
                b=(char)a;
                caractere[i]='■';
                valeur[i]=valeurCaractere((char)254);
                
                      }
            else{
                b=(char)a;
                caractere[i]=b;
                valeur[i]=valeurCaractere(((char)aa));
                }
            a++;
            aa++;
            
                               }
        valeur[292]=valeurCaractere(((char)10));
        valeur[40]=valeurCaractere((' '));
                            }
    public char[]getCaractere(){
        return caractere;
                               }
    public void setCaractere(char[]Caractere){
        this.caractere=caractere;
        
                                             }
    
    public BigInteger[]getValeur(){
        return valeur;
                                  }
    public void setValeur(BigInteger[]valeur){
        this.valeur=valeur;
                                             }
    
    public BigInteger getE(){
     return e;
                                        }
    public void setE(BigInteger e){
        this.e=e;
                                  }
    public BigInteger getN() {
        return n;
                             }   
    public void setN(BigInteger n){
        this.n=n;
                                  }
    
    public BigInteger valeurCaractere(char c/*BigInteger e,BigInteger n*/){
        BigInteger grandNB=new BigInteger(String.valueOf(c+0));
        grandNB=grandNB.modPow(e,n);
        return grandNB;
                                                                          } 
}