package dp;

public class BestTimeToBuyAndSellStock {

	// find the min, and prices[i] - min is the profit
	public int maxProfit(int[] prices) {
    	int min = prices[0];
    	int max = prices[0];
    	int buyIndex = 0;
    	int sellIndex = 0;
    	for(int i = 1; i < prices.length; i ++) {
    		if(max < prices[i] - min) {
    			max = prices[i] - min;
    			sellIndex = i;
    		}
    		if(prices[i] < min) {
    			buyIndex = i;
    			min = prices[i];
    		}
    	}
    	// buyIndex & sellIndex
    	return max;
    }
} 