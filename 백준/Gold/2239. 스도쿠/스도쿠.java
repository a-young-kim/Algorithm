import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 9 * 9 보드
 * 2. 중복 없이 채우기
 * 
 * 풀이
 * 1. 9*9 배열에 들어 갈 수 있는 값을 채운다. 
 * 2. 백트레킹
 */
public class Main {
	
	static int N = 9;
	static int[][] map;
	static boolean[][][] visitedMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0;j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		f(0, 0);
		
	}
	
	
	public static boolean f(int row, int col) {
		
			
		if(col >= N) {
			row ++;
			col = 0;
		}
		
		if(row >= N ) {
			for(int i = 0 ; i < N; i++) {
				for(int j = 0;j < N;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			return true;
		}
		
		if(map[row][col] != 0) return f(row, col + 1);
		else {
			boolean[] visited = new boolean[N + 1];
			// 상하 판단
			for(int k = 0; k < N; k++) {
				int nr1 = row - k;
				int nr2 = row + k;
				
				if(nr1 >= 0 && nr1 < N) visited[map[nr1][col]] = true;
				if(nr2 >= 0 && nr2 < N) visited[map[nr2][col]] = true;
				
			}
			
			// 좌우 판단
			for(int k = 0; k < N; k++) {
				int nc1 = col - k;
				int nc2 = col + k;
				
				if(nc1 >= 0 && nc1 < N) visited[map[row][nc1]] = true;
				if(nc2 >= 0 && nc2 < N) visited[map[row][nc2]] = true;
			}
			
			// 3 * 3 판단
			for(int k = 0; k < 3; k++) {
				for(int l = 0; l < 3; l++) {
					int nr = row / 3 * 3 + k;
					int nc = col / 3 * 3+ l;
					
					visited[map[nr][nc]] = true;
				}
			}
			
			for(int k = 1; k < N + 1; k++) {
				if(visited[k]) continue;
				map[row][col] = k;
				
				if(f(row, col + 1)) return true;
				map[row][col] = 0;
			}
		}
		return false;
	}
}