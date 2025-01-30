/*
문제
1. 단품 메뉴를 조합해서 코스 메뉴
2. 최소 2가지 이상의 메뉴를 단품메뉴로 구성
3. 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
4.단품메뉴들의 갯수가 담긴 course 주어짐
5. 새로 추가하게될 코스요리 메뉴의 구성을 문자열 형태로 return

제한사항
주문은 10이하의 문자열 -> 알파벳 대문자로만 이루어짐
코스 배열은 10 이하 -> 오름차순 정렬
사전순으로 오름차순 정렬해서 return

아이디어
조합 -> 2의 10승/ 10! 의 * 20 => 될 듯....아마

풀이과정
1. List<List<HashMap<String, Integer>>> list 생성
2. for i in range(0, course[-1]) list 안에 hashmap 생성
3. dfs() 조합
4. return
*/
import java.util.*;
class Solution {
    List<HashMap<String, Integer>> list;
    int maxNum;
   
    public String[] solution(String[] orders, int[] course) {
       
        
        list = new ArrayList<>();
        
        for(int i = 0; i < 11; i++){
            HashMap<String, Integer> map = new HashMap<>();
            list.add(map);
        }
        
        maxNum = course[course.length - 1];
        
        for(String order : orders){
            char[] array = order.toCharArray();
            Arrays.sort(array);
            dfs(0, 0, array, "");
        }
        
        List<String> result = new ArrayList<>();
        
        for(int num : course){
            HashMap<String, Integer> map = list.get(num);
            int cnt = 2;
            List<String> cntList = new ArrayList<>();
           
            for(String key : map.keySet()){
                Integer value = map.get(key);
                
                if(value == 1) continue;
                if(value > cnt){
                    cntList.clear();
                    cnt = value;
                    cntList.add(key);
                }
                else if(value == cnt) {
                    cntList.add(key);
                }
            }
            result.addAll(cntList);
        }
        
        String[] answer = new String[result.size()];
        Collections.sort(result);
        
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    public void dfs(int idx, int cnt, char[] order, String myOrder){
        
        HashMap<String, Integer> map = list.get(cnt);
        if(map.containsKey(myOrder)) {
            map.put(myOrder, map.get(myOrder) + 1);
        }
        else {
            map.put(myOrder, 1);
        }
        
        if(cnt == maxNum) return;
        
        for(; idx < order.length; idx ++){
            dfs(idx + 1, cnt + 1, order, myOrder + order[idx]);
        }
    }
}