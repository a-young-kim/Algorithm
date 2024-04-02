import java.util.*;
/*
문제
1. 1부터 N까지 번호가 붙어있는 N명의 사람
2. 영어 끝말잇기의 규칙
    2.1 번호 순 
    2.2 마지막 사람 다음은 1번
    2.3 끝말잇기 규칙 동일
3. 사람의 수 N과 순서대로 말한 단어 words가 매개변수로 주어질 때 가장 먼자 탈락하는 사람의 번호와 몇 먼째 차례에서 탈락하는지

제한 사항
1. N 10이하
2. words 단어 배열 N이상 100이하
3. 단어의 길이는 2이상 50이하
4. 정답은 [번호, 차례] -> 탈락자가 없다면 0, 0

아이디어
이전 값의 마지막 글자 저장

*/
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        char letter = words[0].charAt(words[0].length() - 1);
        
        int node = 2, level = 1;
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i = 1 ;i <  words.length; i++){
            if(node > n) {
                node = 1;
                level++;
            }
            
            if(words[i].length() == 1 || words[i].charAt(0) != letter || set.contains(words[i])){
                answer[0] = node;
                answer[1] = level;
                break;
            }
            letter = words[i].charAt(words[i].length() - 1);
            set.add(words[i]);
            node++;
        }

        return answer;
    }
}