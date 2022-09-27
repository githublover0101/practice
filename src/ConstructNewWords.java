import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import start202209.RandomString;

public class ConstructNewWords {
    public String getNewWords(String words, int n){
        if(words==null){
            return "";
        }
        StringBuilder ans = new StringBuilder();
        
        //对原有的句子根据空格进行拆分，拆分成各个单词
        String[] wordsString = words.split(" ");
        Map<String, Set<String>> wordsMap = new HashMap<String, Set<String>>();
        int length = wordsString.length;
        // 构建map
        for(int i=0;i<length-1;i++){
        	String tmpKey = wordsString[i];
            String tmpValue = wordsString[i+1];
            if(!wordsMap.containsKey(tmpKey)){
                Set<String> wordSet = new HashSet<String>();
                wordSet.add(tmpValue);
                wordsMap.put(tmpKey, wordSet);
            }else{
                wordsMap.get(tmpKey).add(tmpValue);
            }
        }

        if(!wordsMap.containsKey(wordsString[length-1])){
            Set<String> wordSet = new HashSet<String>();
            wordSet.add(wordsString[0]);
            wordsMap.put(wordsString[length-1], wordSet);
        }else{
        	wordsMap.get(wordsString[length-1]).add(wordsString[0]);
        }

        
        //从单词中随机取一个单词，作为句子头单词
        int randomI = new Random().nextInt(length-1);
        String randomWord = wordsString[randomI];
        
        while(n>0){
            ans.append(randomWord).append(" ");
            Set<String> wordTmpSet = wordsMap.get(randomWord);
            
            int tmpLen = wordTmpSet.size();
            
            randomI = new Random().nextInt(tmpLen);
            String[] tm = new String[tmpLen];
            int indx = 0;
            for (String v:wordTmpSet){
                tm[indx] = v;
                indx++;
            }
            randomWord = tm[randomI];
            n--;
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String words = "this is a sentence it is a good one and it is also bad";
        int n = 5;
        
        ConstructNewWords instance = new ConstructNewWords();
		for(int i = 0; i < 50; i++) {
			String ans = instance.getNewWords(words,n);
			System.out.println(ans);
		}
    }
}
