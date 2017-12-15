package process;

import process.BandElectronicCardSim.Card;

public class Map {
	private Card c;
	private RSAKeys r;
	
	public Map(Card card,RSAKeys rsa) {
		c = card;
		r = rsa;
	}

	public Card getC() {
		return c;
	}

	public void setC(Card c) {
		this.c = c;
	}

	public RSAKeys getR() {
		return r;
	}

	public void setR(RSAKeys r) {
		this.r = r;
	}
	
	public boolean equals(Map m) {
		return c.equals(m.getC());
	}
	
}
