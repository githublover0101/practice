package start202209.token.card.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CardPlayer {

	
	public static void main(String[] args) {
		Map<Token, Integer> tokensMap = new HashMap<Token, Integer>();
		Token[] tokenColors = Token.values();
		Random random = new Random();
		for(Token t: tokenColors) {
			int count = random.nextInt(10);
			tokensMap.put(t, count);
		}
		
		System.out.println("original tokens are ====== " + tokensMap);
		
		Card card = new CardA();
		boolean res = card.canBuy(tokensMap);
		System.out.println("can buy cardA: " + res);
		int count = card.buy(tokensMap);
		System.out.println("can buy cardA count: " + count);

		
		card = new CardB();
		res = card.canBuy(tokensMap);
		System.out.println("can buy cardB: " + res);
		count = card.buy(tokensMap);
		System.out.println("can buy cardB count: " + count);
		
		
		card = new CardC();
		res = card.canBuy(tokensMap);
		System.out.println("can buy cardC: " + res);
		count = card.buy(tokensMap);
		System.out.println("can buy cardC count: " + count);
		
		card = new CardD();
		res = card.canBuy(tokensMap);
		System.out.println("can buy cardD: " + res);
		count = card.buy(tokensMap);
		System.out.println("can buy cardD count: " + count);
	}
	
}
