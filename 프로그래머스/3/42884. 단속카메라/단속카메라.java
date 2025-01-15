import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int min = -30000;
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        for(int[] route : routes){
            if(min >= route[0] && min <= route[1]) continue;
            min = route[1];
            answer++;
        }
        
        return answer;
    }
}