/*
문제
1. n 명이 입국 심사
2. 모든 심사대는 비어 있음
3. 한 심사대에서 동시어 심사 가능
4. 모든 심사를 받는데 걸리는 시간 최소

입력
1. 입국 심사를 기다리는 사람 수 n, 10억 이하
2. 각 심사관이 한 명을 심사는데 걸리는 시간

풀이
1. times 정렬
2. int start = times[0], int end = times[0] * n 
3. while(start < end)
4. mid = (start + end) / 2
5. mid를 가지고 times에서 다 나누어서 합이 n보다 크면 end = mid로 
6. n보다 작으면 start = mid
*/
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long start = (long)times[0];
        long end = (long)times[times.length - 1] * n;
        long mid;
        
        while(start <= end){
            mid = (start + end) / 2;
            
            long count = countPeople(mid, times);

            if(count >= n){
                answer = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        
        return answer;
    }
    
    public long countPeople(long mid, int[] times){
        long count = 0;
        
        for(int i = 0; i < times.length; i++){
            count += mid / times[i];
        }
        
        return count;
    }
}