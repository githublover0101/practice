package start202209.token.card.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

public class Player {
	
	public boolean canBuy(Map<String, Integer> tokens, Map<String, Integer> cardRules) {
		Set<Entry<String, Integer>> set = cardRules.entrySet();
		
		for(Entry<String, Integer> item: set) {
			String color = item.getKey();
			Integer value = item.getValue();
			
			if(!tokens.containsKey(color) || tokens.get(color) < value) {
				return false;
			}
		}
		return true;
		
	}
	
	
	public void buyCards(Map<String, Integer> tokens, Map<String, Integer> cardRules) {
		int count = 0;
		while(canBuy(tokens, cardRules)) {
			removeTokens(tokens, cardRules);
			count++;
		}
		System.out.println("can buy cards count: " + count);
		System.out.println("remain tokens: " + tokens);
	}
	
	private void removeTokens(Map<String, Integer> tokens, Map<String, Integer> cardRules) {
		Set<Entry<String, Integer>> set = cardRules.entrySet();
		
		for(Entry<String, Integer> item: set) {
			String color = item.getKey();
			Integer value = item.getValue();
			
			tokens.put(color, tokens.get(color) - value);
		}
	}
	
	
	public static void main(String[] args) {
		Map<String, Integer> tokens = new HashMap<String, Integer>();

		tokens.put("Red", 3);
		tokens.put("Blue", 4);
		tokens.put("Green", 3);
		tokens.put("White", 3);
		tokens.put("Black", 3);
		
		Map<String, Integer> cardMap = new HashMap<String, Integer>();
		cardMap.put("Red", 1);
		cardMap.put("Blue", 1);
		
		Player obj = new Player();
		boolean res = obj.canBuy(tokens, cardMap);
		System.out.println(res);
		
		obj.buyCards(tokens, cardMap);
		
		Map<Token, Integer> tokensMap = new HashMap<Token, Integer>();
		Token[] tokenColors = Token.values();
		Random random = new Random();
		for(Token t: tokenColors) {
			int count = random.nextInt(10);
			tokensMap.put(t, count);
		}
		
		System.out.println("original tokens are ====== " + tokensMap);
		
		Card card = new CardA();
		res = card.canBuy(tokensMap);
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
