import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1}, 
				dy = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[][] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		
		f(0);
		System.out.println(answer);
	}
	
	public static void f(int cnt) {
		
		if(cnt == N) {
			answer++;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(array[cnt][i] != 0)  continue;
			f2(1, cnt, i);
			f(cnt + 1);
			f2(-1, cnt, i);
		}
	}
	
	public static void f2(int flag, int row, int col) {
		array[row][col] += flag;
		for(int i = 0; i < 8; i++) {
			for(int j = 1; j < N; j++) {
				int nr = row + dy[i] * j;
				int nc = col + dx[i] * j;
				
				if(nr < 0 || nr >= N) break;
				if(nc < 0 || nc >= N) break;
				
				array[nr][nc] += flag;
			}
		}
			
	}

}