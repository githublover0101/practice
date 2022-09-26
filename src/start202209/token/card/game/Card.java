package start202209.token.card.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class Card {

	public abstract Map<Token, Integer> getCardRules();
	
	public boolean canBuy(Map<Token, Integer> tokens) {
		Map<Token, Integer> cardRules = getCardRules();
		Set<Entry<Token, Integer>> cardRulesSet = cardRules.entrySet();
		
		for(Entry<Token, Integer> rule: cardRulesSet) {
			Token color = rule.getKey();
			int colorCount = rule.getValue();
			if(!tokens.containsKey(color) || tokens.get(color) < colorCount) {
				return false;
			}
		}
		return true;
	}
	
	public int buy(Map<Token, Integer> tokens) {
		int count = 0;
		while(canBuy(tokens)) {
			removeTokenAfterBuyOneCard(tokens);
			count++;
		}
		
		System.out.println("buy count === " + count);
		return count;
	}
	
	private void removeTokenAfterBuyOneCard(Map<Token, Integer> tokens) {
		Map<Token, Integer> cardRules = getCardRules();
		Set<Entry<Token, Integer>> cardRulesSet = cardRules.entrySet();
		
		for(Entry<Token, Integer> rule: cardRulesSet) {
			Token color = rule.getKey();
			int colorCount = rule.getValue();
			tokens.put(color, tokens.get(color) - colorCount);
		}
	}
	
}


class CardA extends Card {
	
	public Map<Token, Integer> getCardRules() {
		Map<Token, Integer> cardRuleMap = new HashMap<Token, Integer>();
		cardRuleMap.put(Token.BLACK, 1);
		cardRuleMap.put(Token.RED, 2);
		return cardRuleMap;
	}
	
}

class CardB extends Card {
	
	public Map<Token, Integer> getCardRules() {
		
		Map<Token, Integer> cardRuleMap = new HashMap<Token, Integer>();
		cardRuleMap.put(Token.GREEN, 1);
		cardRuleMap.put(Token.RED, 1);
		return cardRuleMap;
	}
}

class CardC extends Card {
	
	public Map<Token, Integer> getCardRules() {
		Map<Token, Integer> cardRuleMap = new HashMap<Token, Integer>();
		cardRuleMap.put(Token.BLACK, 1);
		cardRuleMap.put(Token.BLUE, 1);
		return cardRuleMap;
	}
}

class CardD extends Card {
	
	public Map<Token, Integer> getCardRules() {
		Map<Token, Integer> cardRuleMap = new HashMap<Token, Integer>();
		cardRuleMap.put(Token.WHITE, 3);
		cardRuleMap.put(Token.RED, 2);
		return cardRuleMap;
	}
}