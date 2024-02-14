import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * N * N개의 보드에서 N개의 퀸을 서로 다른 두 퀸이 공격하지 못하게 놓은 경우의 수는 몇가지?
 * 
 * 입력
 * 테스트케이스 T
 * 하나의 자연수 N
 * 
 * 출력
 * 
 * 풀이
 * 백트래킹
 */
public class Solution {
	
	static int N, answer;
	static int[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 answer = 0;
			 
			 for(int j = 0; j < N; j++) {
				 visited = new int[N][N];
				 setVisited(0, j, 1);
				 f(1);
				 setVisited(0, j, -1);
			 }
			 
			 System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static void setVisited(int row, int col, int flag) {
		for(int i = 0; i < N; i++) {
			visited[row][i] += flag;
			visited[i][col] += flag;
			
			if(row + i < N) {
				if(col + i < N) visited[row + i][col + i] += flag;
				if(col - i >= 0) visited[row + i][col - i] += flag;
			}
			if(row - i >= 0) {
				if(col + i < N) visited[row - i][col + i] += flag;
				if(col - i >= 0) visited[row - i][col - i] += flag;
			}
		}
	}
	
	public static void f(int cnt) {
		if(cnt == N) {
			answer ++;
			return;
		}
		
		for(int j = 0; j < N; j++) {
			if(visited[cnt][j] != 0) continue;
			setVisited(cnt, j, 1);
			f(cnt + 1);
			setVisited(cnt, j, -1);
		}
		
	}
}