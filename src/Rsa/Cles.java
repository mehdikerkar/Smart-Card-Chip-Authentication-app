/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rsa;
import java.math.BigInteger;
import java.util.Random;
/**
 *
 * @author mariya
 */
public class Cles {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger phi;
    
    
    public Cles(int bitLength){
        genererPremierPQ(bitLength);
        genererNED(bitLength);
                 }
    public void genererPremierPQ(int bitLength){
    	p = BigInteger.probablePrime(bitLength, new Random());
		q = BigInteger.probablePrime(bitLength, new Random());
       /* p=new BigInteger(10,100,new Random());
        do{
            q=new BigInteger(10,100,new Random());
          }while(q.compareTo(p)==0);
                         */         
    	}
    
    public void genererNED(int bitLength){
        n=p.multiply(q);
        phi=p.subtract(BigInteger.valueOf(1));
        phi=phi.multiply(q.subtract(BigInteger.valueOf(1)));
       
        do{
            e=new BigInteger(bitLength,new Random());
          }while((e.compareTo(phi)!=-1)||(e.gcd(phi).compareTo(BigInteger.valueOf(1))!=0));
        
        d=e.modInverse(phi);
                            }
    public BigInteger getP(){
        return p;
                            }
    
    public BigInteger getQ(){
        return q;
                            }
    
    public BigInteger getN(){
        return n;
                            }
    
    public BigInteger getE(){
        return e;
                            }
    
    public BigInteger getD(){
        return d;
                            }
    
    public BigInteger getPHIN(){
        return phi;
                               }
    
    public void setP(BigInteger p){
        this.p=p;  
                                  }
    
    public void setQ(BigInteger q){
        this.q=q;  
                                  }
     
    public void setN(BigInteger n){
        this.n=n;  
                                  }
      
    public void setE(BigInteger e){
        this.e=e;  
                                  }
    
    public void setD(BigInteger d){
        this.d=d;  
                                  }
    
     public void setPHIN(BigInteger phi){
        this.phi=phi;  
                                        }
        
        }
