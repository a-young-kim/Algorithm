import java.util.*;

/*
풀이
1. int[] alpa = int[27]을 만들어서 호출 개수 세기
2. 호출 개수의 최대 값 확인 int maxCount 
3. orders과 비교하여 maxCount보다 작지만 orders중의 최대값 저장 int maxCnt
4. List<HashMap<String, Integer>> 생성
5. for i를 maxCnt 까지 돌려 list.add()
6. f("", 0) 실행
7. for문 list를 돌리기 -> 해당 index가 course에 있는지 확인
8. int count = 0, List<String> list = new Array
9. for(String key: hashMap.KeySet())
9.1. int num = hashMap.get(key)
9.2. num == count list.add
9.3 num > count list = new, list.add
10. answer.add(list);
*/

import java.util.*;
import java.io.*;

class Solution {
    List<HashMap<String, Integer>> list = new ArrayList();
    List<String> answerList = new ArrayList<>();
    
    boolean[] needs;
    int[] alphabet;
    int maxCnt;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        alphabet = new int[26];

        for(int j = 0; j < orders.length; j++){
            String order = orders[j];
            for(int i = 0; i < order.length(); i++){
                int index = order.charAt(i) - 'A';
                alphabet[index] ++;
            }
            char[] array = order.toCharArray();
            Arrays.sort(array);
            orders[j] = new String(array);
        }
        
        System.out.println(Arrays.toString(alphabet));
        System.out.println(Arrays.toString(orders));
        
        needs = new boolean[11];
        for(int c :course) {
            needs[c] = true;
        }
        System.out.println(Arrays.toString(needs));
        maxCnt = course[course.length - 1];
        
        for(int i = 0; i < maxCnt + 1; i++) list.add(new HashMap<>());

        for(String order: orders){
            f(0, "", order);
        }

        
        for(HashMap<String, Integer> hm: list) {
            System.out.println(hm);
            getAnswer(hm);
        }
        
        answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) answer[i] = answerList.get(i);
        Arrays.sort(answer);
        return answer;
    }
    
    public void f(int idx, String course, String order){
        int len = course.length();
        
        if(needs[len]){
            HashMap<String, Integer> hm = list.get(len);
            if(hm.containsKey(course)) hm.put(course, hm.get(course) + 1);
            else hm.put(course, 1);
        }
        
        if(len == maxCnt + 1) return;

        for(int i = idx; i < order.length(); i++){
            char c = order.charAt(i);
            if(alphabet[c - 'A'] <= 1) continue;
           f(i + 1, course + c, order);
        }
    }
    
    public void getAnswer(HashMap<String, Integer> hashMap){
        int max = 0;
        for(String key: hashMap.keySet()){
            int value = hashMap.get(key);
            max = Math.max(max, value);
        }
        
        if(max <= 1) return;
        
        for(String key: hashMap.keySet()){
            int value = hashMap.get(key);
            if(max == value) answerList.add(key);
        }
    }
    
}