import java.util.*;
import java.io.*;

/*
문제
1. 어떤 순서를 따르는 요소들의 모음 -> 튜플
2. 튜플의 성질
    2.1 중복 원소 가능
    2.2 원소 순서가 다르면 다른 튜플
    2.3 원소의 개수 유한
3. 원소의 개수가 n이고 중복되는 원소가 없는 튜플이 주어질 떄 가능한 집합이 주어진다. 
4. 특정 튜플을 표현하는 집합이 담긴 문자열이 주어질 경우 s가 표현하는 튜플을 배열에 담아
    return하시오

제한사항
1. s는 백만 이하
2. s는 숫자와 {,}로 이루어져 잆음
3. 숫자가 0으로 시작하는 경우는 없음
4. s가 표현하는 튜플의 원소는 십만 이하 자연수
5. return하는 배열의 길이가 1이상 500이하인 경우만 입력

풀이
1. List<List<Integer>> 생성
2. 집합들을 LIst<List> 형식으로 변경
3. List를 List의 길이 순으로 변경
4. List<Integer> answer를 생성하여 입력
5. list를 int로 변경하여 return
*/
class Solution {
    public int[] solution(String s) {
        List<List<Integer>> data = new ArrayList<>();
        String[] arr = s.split("\\{|}");
        
        
        for(int i = 0; i < arr.length; i++){
          if(arr[i].length() == 0) continue;
            List<Integer> list = new ArrayList<>();
            String[] arr2 = arr[i].split(",");
            
            if(arr2.length == 0) continue;
            for(int j = 0;j < arr2.length; j++) {
                if(arr2[j].length() == 0) continue;
                list.add(Integer.parseInt(arr2[j]));
            }
            data.add(list);
        }
        
        Collections.sort(data, new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> o1, List<Integer> o2){
                return o1.size() - o2.size();
            }
        });
        
        //for(List<Integer> list: data) System.out.println(list);
        List<Integer> listAnswer = new ArrayList<>();
        for(List<Integer> list : data){
            for(Integer num: list){
                if(!listAnswer.contains(num)) listAnswer.add(num);
            }
        }
        //System.out.println(listAnswer);
        int[] answer = new int[listAnswer.size()];
        
        for(int i = 0; i < listAnswer.size(); i++) answer[i] = listAnswer.get(i);
        return answer;
    }
}