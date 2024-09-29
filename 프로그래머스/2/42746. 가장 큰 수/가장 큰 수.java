/*
문제
1. 정수를 이어 붙여서 만들 수 있는 가장 큰 수

풀이과정
1. numbers를 String만들고 정렬
2. 
*/
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        
      
        List<String> list = new ArrayList<>();
        
        boolean check = false;
        for(int number : numbers){
            if(number != 0) check = true;
            
            list.add(String.valueOf(number));
        }
        
        if(!check) return "0";
        
        Collections.sort(list, new Comparator<String>(){
            @Override
            public int compare(String a, String b){
               
                String num1 = a + b;
                String num2 = b + a;
                return num2.compareTo(num1);
            }
        });
        
       StringBuilder answer = new StringBuilder();
        for (String s : list) {
            answer.append(s);
        }
        
    
        return answer.toString();

    }
}