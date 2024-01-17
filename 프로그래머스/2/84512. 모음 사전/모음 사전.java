import java.util.HashMap;

class Solution {
    final String[] alphabets = {"A", "E", "I", "O", "U"};
	HashMap<String, Integer> wordDic = new HashMap<>();
    
    public void setDic(String str){
		int length = str.length();
		wordDic.put(str, wordDic.size());
		
		if(length > 4) return;
		for(int i = 0; i < alphabets.length; i++) {
			setDic(str + alphabets[i]);
		}
	}
    public int solution(String word) {
		setDic("");
		
		int answer = wordDic.get(word);
        return answer;
    }
}