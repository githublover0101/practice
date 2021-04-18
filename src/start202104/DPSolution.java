package start202104;

public class DPSolution {

	/**
	 * https://leetcode.com/problems/maximum-subarray/
	 * 
	 * 53. 最大子序和
	 * 
	 * 例子：[-2,1,-3,4,-1,2,1,-5,4]，结果为[4,-1,2,1]，最大和为6.
	 * 
	 * dp[i] 表示以nums[i]结尾的元素，最大的子序和
	 * dp[i+1] = dp[i] + nums[i], dp[i] > 0
	 * dp[i+1] = nums[i], dp[i] <= 0
	 * 
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < len; i++) {
        	dp[i] = nums[i];
        	if(dp[i-1] > 0 ) {
        		dp[i] += dp[i-1];
        	}
        	max = dp[i] > max ? dp[i] : max;
        }
        return max;
    }
	
	public int maxSubArray2(int[] nums) {
        if(nums.length == 0) return 0;
        int len = nums.length;
        int pre = nums[0];
        int cur = 0;
        int max = nums[0];
        for(int i = 1; i < len; i++) {
        	cur = nums[i];
        	if(pre > 0 ) {
        		cur += pre;
        	}
        	max = cur > max ? cur : max;
        	pre = cur;
        }
        return max;
    }
	
	
	/**
	 * 
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
	 * 121. 买卖股票的最佳时机
	 * 
	 * 每天只能执行买入或者卖出一次操作
	 * 解题思路
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		if(prices.length == 0) return 0;
		
		//记录最低价格
		int min = prices[0];
		
		//记录最大的差价值，如果当前prices[i] - min > max，则max=prices[i]-min
		int max = 0;
		for(int i = 1; i < prices.length; i++) {
			if(prices[i] - min > max) {
				max = prices[i] - min;
			}
			if(prices[i] < min) {
				min = prices[i];
			}
		}
		return max;
    }
	
	/**
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
	 * 可以买卖多次，每次买之前需要进行卖的操作
	 * 
	 * dp[i]表示第i天赚钱总额，只要满足prices[i] - prices[i-1] > 0，就进行买入卖出操作
	 * 
	 * dp[i] = dp[i-1]
	 * dp[i] = dp[i-1] + (prices[i] - prices[i-1])
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitII(int[] prices) {
        if(prices.length == 0) return 0;
        int max = 0;
        int[] dp = new int[prices.length];
        for(int i = 1; i < prices.length; i++) {
        	dp[i] = dp[i-1];
        	if(prices[i-1] < prices[i]) {
        		dp[i] += prices[i] - prices[i-1];
        	}
        }
        return dp[prices.length-1];
    }
	
	
	/**
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
	 * 最多可以完成两笔交易，获取最大的利润
	 * 
	 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
	 * 
	 * //对于数组d[i]，表示第i天能获取到的最大收益
        //1.按照第一题的思路，从左到右，得到数组left[], nums[i]-min
        //2.再从右到左，max-nums[i], 得到数组right[]
        //3.找到最大的left+right
	 * @param prices
	 * @return
	 */
	public int maxProfitIII(int[] prices) {
		if(prices.length == 0) return 0;
		int len = prices.length;
		
		//从左往右，记录最低价min，以及最大利润maxProfile
		//每次检测更新maxProfile = max(maxProfile, prices[i]-min)
		//dp[i] = maxProfile
		int[] left = new int[len];
		int min = prices[0];
        int maxProfile = 0;
		for(int i = 1; i < len; i++) {
            min = Math.min(min, prices[i]);
			maxProfile = Math.max(maxProfile, prices[i] - min);
            left[i] = maxProfile;
		}
		
		//从右往左，记录最高价max，以及最大利润maxProfile
		//maxProfile = max(maxProfile, max - prices[i])
		//dp[i] = maxProfile
		int[] right = new int[len];
		int max = prices[len-1];
        maxProfile = 0;
		for(int i = len-2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
			maxProfile = Math.max(maxProfile, max - prices[i]);
            right[i] = maxProfile;
		}
		
		int maxMax = 0;
		for(int i = 0; i < len; i++) {
			maxMax = Math.max(maxMax, left[i]+right[i]);
		}
		return maxMax;
    }

}
