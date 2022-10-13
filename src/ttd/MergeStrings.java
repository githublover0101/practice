package ttd;

public class MergeStrings {

	public String mergeStrings(String[] strings) {
		int[] letters = new int[26];
		int len = strings.length;
		for(int i = 0; i < len; i++) {
			countLetters(letters, strings[i]);
		}
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < 26; i++) {
			int count = letters[i];
			char c = (char)(i+97);
			int index = 0;
			while(index < count) {
				result.append(c);
				index++;
			}
		}
		return result.toString();
	}

	private void countLetters(int[] letters, String str) {
		int len = str.length();
		for(int i = 0; i < len; i++) {
			char c = str.charAt(i);
			letters[(int)c - 97]++;
		}
	}
	
	public static void main(String[] args) {
		MergeStrings instance = new MergeStrings();
		String[] strings = new String[4];
		strings[0] = "abc";
		strings[1] = "abctest";
		strings[2] = "abctestghy";
		strings[3] = "abc";
		System.out.println(instance.mergeStrings(strings));
	}
	
}
