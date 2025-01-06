import java.util.*;


class Solution {
    int answer = 0;
    HashSet<String> set = new HashSet<>();
    final String[] alphabets= {"A", "E", "I", "O", "U"};
    String endWord;
    
    public int solution(String endWord) {
        
        this.endWord = endWord;
        setDic("");
        return answer;
    }
    
    public void setDic(String word){
        if(answer != 0) return;
        
        if(word.length() > 5) return;
        if(word.equals(endWord)) {
            answer = set.size();
            return;
        }
        set.add(word);
        for(String c : alphabets){
            setDic(word + c);
        }
    }
}