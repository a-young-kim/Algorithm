import java.util.*;
/*
문제
1. 모든 음식의 지수를 K 이상으로 만든다. 코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식으로 만든다.
2. 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
3. 모든 음식의 지수고 K 이상이 될 때까지 반복
4. 음식의 스코빌 지수 배열 scoville, 스코빌 지수 K에서 섞어야 하는 최소 횟수 return

제한 사항
1. 음식은 2이상 백만 이하
2. K는 10억 이하
3. 원소는 0이상 백만 이하 -> long 사용
4. 만들 수 없는 경우 -1 return

풀이 방식
1. pq를 만든다. 
2. 가장 작은 두개의 값들을 poll하여 값을 수정하고 다시 add
3. 길이가 0이면 -1, K 이상이면 섞은 횟수 출력
*/
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length;i++){
            pq.add(scoville[i]);
        }
        
        while(pq.size() > 1){
            if(pq.peek() >= K) break;
            
            int n1 = pq.poll();
            int n2 = pq.poll();
            pq.add(n1 + n2 * 2);
            answer++;
        }
        
        if(pq.peek() >= K) return answer;
        else return -1;
    }
}