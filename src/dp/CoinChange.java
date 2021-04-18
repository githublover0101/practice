package dp;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {

	// dfs ===> combination sum problem
	// time complexity is O()
	public List<List<Integer>> changeMoney(int[] coins, int target) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		dfs(coins, subList, allList, target, 0);
		return allList;
	}

	public void dfs(int[] coins, List<Integer> subList, List<List<Integer>> allList, int target, int start) {
		if(target == 0) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		for(int i = start; i < coins.length; i ++) {
			if(coins[i] <= target) {
				subList.add(coins[i]);
				dfs(coins, subList, allList, target - coins[i], i);
				subList.remove(subList.size()-1); //remove
			}
		}
	}

	// coinUsed[i] 表示给钱数为 i 找零需要的最少硬币数
	// coinUsed[i] = min no of coins to make change for amount i
	// coinUsed[i] = min{coinUsed[i - value[j]] + 1}
	public int makeChange(int[] value, int target) {
		int[] coinUsed = new int[target+1];
		for(int cents = 1; cents <= target; cents ++) {
			int minCents = Integer.MAX_VALUE;
			
			for(int i = 0; i < value.length; i ++) {
				if(value[i] <= cents) {
					int tmp = coinUsed[cents-value[i]] + 1;
					if(tmp < minCents) {
						minCents = tmp;
					}
				}
			}
			coinUsed[cents] = minCents;
		}
		return coinUsed[target];
	}

	public static void main(String[] args) {
		int target = 4;
		int[] coins = {1,2,3};
		CoinChange test = new CoinChange();
		List<List<Integer>> list = test.changeMoney(coins, target);
		for(List<Integer> subList: list) {
			System.out.println(subList);
		}

		int minCount = test.makeChange(coins, target);
		System.out.println("min coins count: " + minCount);
	}
}