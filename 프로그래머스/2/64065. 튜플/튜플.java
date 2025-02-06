/*
문제
1. 어떤 순서를 따르는 모음 => 튜플
2. 튜플의 특징
2.1 중복 원소 가능
2.2 원소에는 순서 있음
2.3 원소 개수는 유한함
3. 원소의 개수가 n개이고 중복되는 원소가 없는 튜플이 주어짐
4. 특정 튜플을 포현하는 집합이 담긴 문자열 s가 매개 변수로 주어질 때 s가 표현하는 튜플을 배열에 담아 return
4.1 집합은 순서 상관없음

제한 사항
1. s의 길이는 백만 이하
2. 숫자 0으로 시작하는 경우는 없음
3. s의 원소는 십만이하

아이디어
크기가 작은 집합 순으로 정렬 해서 하나씩 뽑기

*/

import java.util.*;
class Solution {
    public int[] solution(String s) {
        
        List<int[]> list = new ArrayList<>();
        
        String[] inputsArr = s.split("[{}]");
       
        for(String inputs : inputsArr){
            if(inputs.length() == 0) continue;
            if(inputs.charAt(0) == ',') continue;
            String[] inputArr = inputs.split(",");
            
            int[] arr = new int[inputArr.length];
            for(int i = 0; i < inputArr.length; i++){
                arr[i] = Integer.parseInt(inputArr[i]);                
            }
            
            list.add(arr);
        }
        
        
        HashSet<Integer> set = new HashSet<>();
        list.sort((o1, o2) -> (o1.length - o2.length));
        int[] answer = new int[list.size()];
        int idx = 0;
        for(int[] arr : list){
            for(int num : arr){
                if(set.contains(num)) continue;
            
                answer[idx] = num;
                idx ++;
                set.add(num);
            }
        }
        return answer;
    }
}