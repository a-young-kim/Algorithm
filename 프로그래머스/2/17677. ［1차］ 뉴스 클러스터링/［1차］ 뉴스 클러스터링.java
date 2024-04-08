/*
문제
1. 문자열 간의 유사도를 자카드 유사도를 사용하여 계산하려고 한다. 
2. 자카드 유사도
    - 두 집합에서 교집합 / 합집합
    - 둘 다 공집합인 경우 1
3. 문자열을 두 글자로 끊어서 다중 집합을 만든다. 

입력 형식
1. 두 문자열 1000이하
2. 두 글자씩 끊어서 다중 집합의 원소로 만든다. 
3. 영문자로 된 글자 쌍만 유효하고 그 외의 문자가 있으면 버린다. 
4. 대소문자 구분을 하지 않는다. 

출력
두 문자열의 자카드 유사도를 출력한다. 
65536을 곱하고 정수만 출력 한다.

풀이
1. int[2]['Z' - 'A'][] 배열 생성 0 부터 25
2. 가능한 경우 그 부분 1로 바꾸기
3. 두 집단이 가능한 경우 개수 세기

다중집합 A = {1, 1, 2, 2, 3}, 다중집합 B = {1, 2, 2, 4, 5}라고 하면, 
교집합 A ∩ B = {1, 2, 2}, 합집합 A ∪ B = {1, 1, 2, 2, 3, 4, 5}가 되므로, 
자카드 유사도 J(A, B) = 3/7, 약 0.42가 된다.
*/
import java.io.*;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        int[][][] boards = new int[2][26][26];
        
        str1 = str1.toUpperCase();
        for(int i = 1; i < str1.length(); i++){
            int c1 = str1.charAt(i - 1) - 'A';
            int c2 = str1.charAt(i) - 'A';
            if(c1 < 0 || c1 >= 26) continue;
            if(c2 < 0 || c2 >= 26) continue;
            
            boards[0][c1][c2] += 1;
        }
    
        
        str2 = str2.toUpperCase();
        for(int i = 1; i < str2.length(); i++){
            int c1 = str2.charAt(i - 1) - 'A';
            int c2 = str2.charAt(i) - 'A';
            if(c1 < 0 || c1 >= 26) continue;
            if(c2 < 0 || c2 >= 26) continue;
            
            boards[1][c1][c2] += 1;
        }
        
        /*for(int i = 0; i < 2; i++){
            for(int j = 0; j < 16; j++){
                System.out.println(Arrays.toString(boards[i][j]));
            }
            System.out.println();
        }*/
        
        double min = 0;
        double max = 0;
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++){
                 max += Math.max(boards[0][i][j] , boards[1][i][j]);
                 min += Math.min(boards[0][i][j] , boards[1][i][j]);
            }
        }
        //System.out.println(min + " " + max);
        if(max == 0) answer = 65536;
        else answer = (int)(min/max*65536);
        return answer;
    }
}