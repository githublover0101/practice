package abc;

import java.util.ArrayList;

public class LetterCombinations {

	public ArrayList<String> letterCombinations(String digits) {
        String current = "";
        ArrayList<String> list = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return list;
        
        String[] listStr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        letterCombinations(digits, 0, current, list, listStr);
        return list;
    }
    
    public void letterCombinations(String digits, int start, String current, ArrayList<String> list, String[] listStr) {
        if(start == digits.length()) {
            list.add(current);
            return;
        }
        char numberChar = digits.charAt(start);
        String charStr = listStr[Integer.parseInt(numberChar+"")];
        for(int i = 0; i < charStr.length(); i ++) {
            letterCombinations(digits, start+1, current + charStr.charAt(i), list, listStr);
        }   
    }
    
    public static void main(String[] args) {
    	LetterCombinations test = new LetterCombinations();
    	String digits = "2";
    	ArrayList<String> list = test.letterCombinations(digits);
    	for(String result: list) {
    		System.out.println(result);
    	}
    }
}
