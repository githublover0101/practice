package start202209;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomString2 {
	

	public String generateRandomString(String paragraph, int n) {
		if(paragraph == null || paragraph.length() == 0) return null;
		
		StringBuilder res = new StringBuilder();
		
		String[] words = paragraph.split(" ");
		int len = words.length;
		
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		for(int i = 0; i < len; i++) {
			String curWord = words[i];
			String nextWord = "";
			if(i == len-1) { //means the last one word, so the next word is the first one
				nextWord = words[0];
			} else {
				nextWord = words[i+1];
			}
			
			if(map.containsKey(curWord)) {
				map.get(curWord).add(nextWord);
			} else {
				Set<String> nextWordSet = new HashSet<String>();
				nextWordSet.add(nextWord);
				map.put(curWord, nextWordSet);
			}
		}
		
		Random randomObj = new Random();
		int randomIndex = randomObj.nextInt(len);
		
		System.out.println(" len = " + len +  "===== randomIndex == " + randomIndex);
		
		String randomWord = words[randomIndex];
		
		while(n > 0) {
			res.append(randomWord).append(" ");
			
			Set<String> nextWordSet = map.get(randomWord);
			String[] nextWords = convertSetToStringArray(nextWordSet);
			
			randomIndex = randomObj.nextInt(nextWords.length);
			randomWord = nextWords[randomIndex];
			
			n--;
		}
		return res.toString();
	}
	
	private String[] convertSetToStringArray(Set<String> set) {
		if(set == null || set.size() == 0) return null;
		
		int len = set.size();
		String[] stringArray = new String[len];
		int i = 0;
		for(String e: set) {
			stringArray[i++] = e;
		}
		return stringArray;
	}
	
	
	public String generateRandomString2(String paragraph, int n, int m) {
		if(paragraph == null || paragraph.length() == 0) return null;
		
		String[] words = paragraph.split(" ");
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		int len = words.length;
		for(int i = 0; i < len-m+1; i++) {
			String cur = words[i];
			for(int j = 1; j < m; j++) {
				cur = cur + " " + words[i+j];
			}
			String next = "";
			if(i == len-m) {
				next = words[0];
			} else {
				next = words[i+m];
			}
			
			if(map.containsKey(cur)) {
				map.get(cur).add(next);
			} else {
				Set<String> nextSet = new HashSet<String>();
				nextSet.add(next);
				map.put(cur, nextSet);
			}
		}
		for(int i = 0; i < len; i++) {
			String cur = words[i];
			String next = "";
			if(i == len-1) {
				next = words[0];
			} else {
				next = words[i+1];
			}
			
			if(map.containsKey(cur)) {
				map.get(cur).add(next);
			} else {
				Set<String> nextSet = new HashSet<String>();
				nextSet.add(next);
				map.put(cur, nextSet);
			}
		}
		
		Random randomObj = new Random();
		
		int randomIndex = randomObj.nextInt(len-m+1);
		
		String randomWord = words[randomIndex];
		
		for(int i = 1; i < m; i++) {
			randomWord = randomWord + " " + words[randomIndex+i];
		}
		
		System.out.println(" ===== the first two words are : " + randomWord);
		int count = m;
		StringBuilder result = new StringBuilder();
		while(n > 0) {
			result.append(randomWord).append(" ");
			Set<String> nextSet = map.get(randomWord);
			
			String[] nextWords = convertSetToStringArray(nextSet);
			randomIndex = randomObj.nextInt(nextWords.length);
			
			randomWord = nextWords[randomIndex];
			n = n-count;
			
			count = 1;
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		String words = "this is a sentence it is a good one and it is also bad";
		int n = 5;
		RandomString2 instance = new RandomString2();
		for(int i = 0; i < 50; i++) {
			String ans = instance.generateRandomString2(words, n, 3);
			System.out.println(ans);
		}
		
	}
	
}
