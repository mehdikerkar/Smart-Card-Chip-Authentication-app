package process;

import Rsa.Rsa;
import process.BandElectronicCardSim.Card;

public class Map {
	private Card c;
	private Rsa r;
	
	public Map(Card card,Rsa rsa) {
		c = card;
		r = rsa;
	}

	public Card getC() {
		return c;
	}

	public void setC(Card c) {
		this.c = c;
	}

	public Rsa getR() {
		return r;
	}

	public void setR(Rsa r) {
		this.r = r;
	}
	
	public boolean equals(Map m) {
		return c.equals(m.getC());
	}
	
}
