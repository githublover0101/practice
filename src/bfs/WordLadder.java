package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WordLadder {

	public int ladderLength(String start, String end, Set<String> dict) {
        LinkedList<String> queue = new LinkedList<String>();
        LinkedList<Integer> distance = new LinkedList<Integer>();
        queue.add(start);
        distance.add(1);
        
        // ArrayList<String> array = new ArrayList<String>();
        // array.addAll(dict);
        while(!queue.isEmpty()) {
            String curr = queue.pop();
            int dist = distance.pop();
            if(curr.equals(end)) {
                return dist;
            }
            
            if(isOneDistance(curr, end)) {
                return dist + 1;
            }
            
            for(int i = 0; i < curr.length(); i ++) {
                char[] chars = curr.toCharArray();
                for(char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if(dict.contains(newWord)) {
                        if(isOneDistance(newWord, curr)) {
                            queue.add(newWord);
                            distance.add(dist + 1);
                            dict.remove(i);
                        }   
                    }
                }
            }
        }
        return 0;
    }
    
    public boolean isOneDistance(String s1, String s2) {
        int len = 0;
        for(int i = 0; i < s1.length(); i ++) {
            if(s1.charAt(i) != s2.charAt(i)) len++;
        }
        return len == 1;
    }

    public boolean canTransform(String start, String end) {
        int diff = 0;
        for(int i = 0; i < s1.length(); i ++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(diff == 0) {
                    diff++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public List<List<String>> findLadders(String start, String end, HashSet<String> dict) {
        List<List<String>> res = new ArrayList<ArrayList<String>>();

        if(canTransform(start, end)) {
            List<String> path = new ArrayList<String>();
            path.add(start);
            path.add(end);
            res.add(path);
            return res;
        }

        
    }
    
    public void dfs(String start, String end, ArrayList<String> subList, ArrayList<ArrayList<String>> allList, ArrayList<String> dict, boolean[] visited) {
        if(start.equals(end) || isOnlyOneChange(start, end)) {
        	if(isOnlyOneChange(start, end)) subList.add(end);
            allList.add(new ArrayList<String>(subList));
            return;
        }
       
        for(int i = 0; i < dict.size(); i ++) {
            String element = dict.get(i);
            if(!visited[i] && isOnlyOneChange(element, start)) {
                visited[i] = true;
                subList.add(element);
                dfs(element, end, subList, allList, dict, visited);
                visited[i] = false;
                subList.remove(subList.size()-1);
            }
        }
    }
    
    public boolean isOnlyOneChange(String word1, String word2) {
        int num = 0;
        for(int i = 0; i < word1.length(); i ++) {
            if(word1.charAt(i) != word2.charAt(i)) {
                num++;
            }
        }
        return num == 1;
    }

    public static void main(String[] args) {
    	WordLadder test = new WordLadder();
    	String start = "qa";
        String end = "sq";
        String[] dicts = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        
        HashSet<String> dict = new HashSet<String>();
    	dict.addAll(Arrays.asList(dicts));
    	ArrayList<ArrayList<String>> list = test.findLadders(start, end, dict);
    	System.out.println(list);


    }
}