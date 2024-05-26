import java.util.*;


/*
문제
1. 한번에 K칸 점프 or 현재까지 온 거리 * 2 만큼 순간이동
2. 순간이동을 하면 건전지 사용량이 줄지 않지만 앞으로 K칸을 점프하면 K만큰 건전지 사용량
3. 거리가 N만큰 떨어져 있는 장소로 이동
4. 점프로 이동하는 것을 최소화 하려고 할때 사용햐아하는 건전지 사용량의 최솟값 return

제한 사항
N 10억 이하 
K 1이상 자연수

풀이
1. 1이동
2. 분할 정복으로 n의 1/2에 도착할 때 구하기 

*/
public class Solution {
    int ans = 0;
    public int solution(int n) {
        
        dfs(n);
        return ans;
    }
    
    public void dfs(int n){
        if(n == 0) return;
        if(n % 2 == 1) ans ++;
        dfs(n / 2);
    }
}