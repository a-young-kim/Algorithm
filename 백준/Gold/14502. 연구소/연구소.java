import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * N * M 직사각형
 * 빈칸 혹은 벽으로 이루어져 있음
 * 상하좌우 빈 칸 모두 퍼져나갈 수 있다. 
 * 새울 수 있는 벽의 개수는 3개
 * 
 * 0은 빈칸 1은 벽 2는 바이러스
 * 
 * 벽을 3개 세운 뒤 바이러스가 퍼질 수 덦는 곳을 안전 영역
 * 
 * 안전 영역의 크기의 최대값
 * 
 * 입력
 * 세로 N, 가로 M
 * 둘째줄 N개의 줄에 지도의 모양
 * 2의 개수는 10 이하
 * 빈칸의 개수는 3개 이상
 * 
 * 출력
 * 안전 영역의 최대 크기 출력
 * 
 * 풀이
 * dfs
 */
public class Main {
	
	static int N, M, answer = Integer.MIN_VALUE, cnt, safe;
	static int[][] array, save = new int[3][2], calArray;
	static List<Integer[][]> list  = new ArrayList<>(); 
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N][M];
		for(int i = 0 ;i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if(array[i][j] == 0) safe++;
			}
		}
		
		// 조합 개수 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M ; j++) {
				if(array[i][j] == 0) {
					save[0][0] = i;
					save[0][1] = j;
					f(i, j + 1, 1);
				}
			}
		}

		
		// 최대 개수 세기
		for(int i = 0; i < list.size(); i++) {
			cnt = 0;
			calArray = new int[N][M];
			for(int j = 0; j < N; j++) {
				System.arraycopy(array[j], 0, calArray[j], 0, M);
			}

			for(int j = 0; j < 3; j ++) {
				Integer[] num = list.get(i)[j];
				calArray[num[0]][num[1]] = 1;
			}

			for(int j = 0; j < N; j ++) {
				for(int k = 0;k < M; k++) {
					if(calArray[j][k] != 2) continue;
					dfs(j, k);
				}
			}

			answer = Math.max(answer, safe - cnt - 3);

		}
		System.out.println(answer);
	}
	public static void f(int row, int col, int cnt) {
		if(cnt == 3) {
			Integer[][] s = new Integer[3][2];
			for(int i = 0; i < 3; i++) {
				s[i][0] = save[i][0];
				s[i][1] = save[i][1];
			}
			list.add(s);
			return;
		}
		
		for(int i = row; i < N; i++) {
			int j;
			if(i != row) j = 0;
			else j = col;
			for(; j < M; j++) {
				if(array[i][j] == 0) {
					save[cnt][0] = i;
					save[cnt][1] = j;
					f(i, j + 1, cnt + 1);
				}
			}
		}
	}
	public static void dfs(int row, int col) {

		for(int i = 0;i < 4; i++) {
			if(row + dy[i] < 0 || row + dy[i] >= N) continue;
			if(col + dx[i] < 0 || col + dx[i] >= M) continue;
			if(calArray[row + dy[i]][col + dx[i]] != 0) continue;
			cnt++;
			calArray[row + dy[i]][col + dx[i]] = 2;

			dfs(row + dy[i], col + dx[i]);
		}
	}
}