package oop;

public class Card {
	private String num;
	private String suit;
	
	public Card(String suit, String num) {
		this.suit = suit;
		this.num = num;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getNum() {
		return this.num;
	}
	
	public String toString() {
		return this.suit + this.num;
	}
}