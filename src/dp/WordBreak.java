package dp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WordBreak {

    // first should check whether or not the word can be breaked
    // if yes, use backtracking to get the list
	public List<String> wordBreak(String s, Set<String> dict) {
        List<List<String>> allList = new ArrayList<List<String>>();

        List<String> subList = new ArrayList<String>();


    }

    public void backtracking(String s, Set<String> dict, List<List<String>> allList, List<String> subList) {
        if(s.length == 0) {

        }
        for(String word: dict) {
            String currWord = s.substring(0, dict.length());
            
        }
    }


}
