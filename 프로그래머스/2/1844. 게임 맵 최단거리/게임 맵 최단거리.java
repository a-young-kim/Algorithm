import java.util.Queue;
import java.util.ArrayDeque;
class Solution {
    int[][] array;
	int N, M;
	int [][] dp, delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};;
	int answer = Integer.MAX_VALUE;
    public int solution(int[][] maps) {
        array = maps;
        N = array.length;
        M = array[0].length;
        dp = new int[N][M];
	    
        Queue<Integer> q = new ArrayDeque<>();
		q.add(0);
		q.add(0);
		dp[0][0] = 1;
		
		while(!q.isEmpty()) {
			int row = q.poll();
			int col = q.poll();
			int cnt = dp[row][col];
			
			if(row == N - 1 && col == M - 1) {
				answer = cnt;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				if(row + delta[i][0] < 0 || row + delta[i][0] >= N) continue;
				if(col + delta[i][1] < 0 || col + delta[i][1] >= M) continue;
				if(array[row + delta[i][0]][col + delta[i][1]] == 0) continue;
				if(dp[row + delta[i][0]][col + delta[i][1]] != 0) continue;
				dp[row + delta[i][0]][col + delta[i][1]] = cnt + 1;
				q.add(row + delta[i][0]);
				q.add(col + delta[i][1]);
			}
        }
       
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
    
}