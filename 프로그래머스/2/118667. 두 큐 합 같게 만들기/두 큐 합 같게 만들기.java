/*
문제
1. 길이가 같은 두개의 큐
2. 하나의 원소를 골라 추출하고 추출한 원소를 다른 큐에 집어넣어 큐의 원소의 합이 같도록
3. 이 떄 필요한 작업의 최소 횟수

아이디어
큐 두개를 만들어서 더 큰 쪽을 pop해서넣ㄱ 
*/
import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int N = queue1.length;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0;
        long sum2 = 0;
        for(int i = 0; i < queue1.length; i++){
            q1.add(queue1[i]);
            sum1 += queue1[i];
            
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        while(sum1 != sum2){
            if(answer > N * 3) return -1;
            if(sum1 > sum2){
                if(q1.isEmpty()) return -1;
                
                int num = q1.poll();
                sum1 -= num;
                sum2 += num;
                q2.add(num);
            }
            
            else{
                if(q2.isEmpty()) return -1;
                
                int num = q2.poll();
                sum1 += num;
                sum2 -= num;
                q1.add(num);
            }
            answer ++;
        }
        
        return answer;
    }
}