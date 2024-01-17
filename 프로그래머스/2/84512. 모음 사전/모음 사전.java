import java.util.HashMap;
class Solution {
    public void setDic(String str, String[] alphabets,HashMap<String, Integer> wordDic){
		int length = str.length();
		wordDic.put(str, wordDic.size());
		
		if(length > 4) return;
		for(int i = 0; i < alphabets.length; i++) {
			setDic(str + alphabets[i], alphabets, wordDic);
		}
	}
    public int solution(String word) {
       HashMap<String, Integer> wordDic = new HashMap<>();
		String[] alphabets = {"A", "E", "I", "O", "U"};
		setDic("", alphabets, wordDic);
		
		int answer = wordDic.get(word);
        return answer;
    }
}