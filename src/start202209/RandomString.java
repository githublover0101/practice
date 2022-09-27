package start202209;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomString {

	/**
	 * 解题思路：
	 * 1.将句子按照空格拆分
	 * 2.遍历每个单词，构建map，key为该单词，value为该单词之后的下一个单词（保存为Set，因为每个单词会出现多次）；
	 * 如果是最后一个单词，下一个单词则为第一个单词
	 * 3.随机选取一个单词作为头单词
	 * 4.寻找头单词的后续节点，也是随机获取（基于前面构建的map）
	 * 5.获取到的后续节点，添加到句子中，直到长度为n
	 * @param words
	 * @param n
	 * @return
	 */
	public String generateRandomString(String words, int n) {
		if(words == null || words.length() == 0) {
			return "";
		}
		
		StringBuilder res = new StringBuilder();
		String[] tmpWords = words.split(" ");
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		
		int len = tmpWords.length;
		for(int i = 0; i < len; i++) {
			String key = tmpWords[i];
			String nextKey = "";
			if(i == len-1) {
				nextKey = tmpWords[0];
			} else {
				nextKey = tmpWords[i+1];
			}
			if(map.containsKey(key)) {
				Set<String> tmpValueSet = map.get(key);
				tmpValueSet.add(nextKey);
			} else {
				Set<String> tmpValueSet = new HashSet<String>();
				tmpValueSet.add(nextKey);
				map.put(key, tmpValueSet);
			}
		}
		
		int randomIndex = new Random().nextInt(len);
		String randomWord = tmpWords[randomIndex]; //随机选取第一个单词
		
		while(n > 0) {
			res.append(randomWord).append(" ");
			
			//找出这个单词之后的单词set
			Set<String> nextWordSet = map.get(randomWord);
						
			String[] nextWords = covertStringSetToStringArray(nextWordSet);
			int nextRandomIndex = new Random().nextInt(nextWords.length);
			
			//从后面的单词中每次随机取一个
			randomWord = nextWords[nextRandomIndex];
			n--;
		}
		
		return res.toString();
	}
	
	private String[] covertStringSetToStringArray(Set<String> stringSet) {
		if(stringSet == null) return null;
		int len = stringSet.size();
		String[] stringArray = new String[len];
		int i = 0;
		for(String e: stringSet) {
			stringArray[i++] = e;
		}
		return stringArray;
	}
	
	public static void main(String[] args) {
		String words = "this is a sentence it is a good one and it is also bad";
		int n = 5;
		RandomString instance = new RandomString();
		for(int i = 0; i < 50; i++) {
			String ans = instance.generateRandomString(words,n);
			System.out.println(ans);
		}
		
	}
}
