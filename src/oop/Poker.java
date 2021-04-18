package oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poker {
	private List<Card> cards = null;

//	数字
	public static String[] nums = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

//	花色
    public static String[] suits = { "&", "@", "#", "$"};
    
    private static final List<Card> protoCards = new ArrayList<Card>();
    
    static {
		for(String suit: suits) {
			for(String num: nums) {
				protoCards.add(new Card(suit, num));
			}
		}
    }
    
	public Poker() {
		cards = new ArrayList<Card>(protoCards);
	}

	public void reset() {
		cards.clear();
		cards.addAll(protoCards);
	}
	
// 	洗牌
	public void shuffle() {
		Collections.shuffle(cards);
		System.out.println(cards + " : " + cards.size());
	}

	public void deal(int numHands, int cardsPerHand) {
		for(int i = 0; i < numHands; i ++) {
			deal(cardsPerHand);
		}
	}

	
	public void deal(int n) {	
		int len = cards.size();
		//发牌应该是从最后往前发，操作list的最后
		List<Card> cardsDeal = cards.subList(len-n, len);
		System.out.println(cardsDeal);
		for(int i = len-1; i >= len-n; i --) {
			cards.remove(i);
		}
	}

	public static void main(String[] args) {
		Poker onePoker = new Poker();
		onePoker.shuffle();
		onePoker.deal(4,5);
		onePoker.shuffle();
		onePoker.deal(2,3);
		onePoker.shuffle();
		onePoker.reset();
		onePoker.shuffle();
	}
}