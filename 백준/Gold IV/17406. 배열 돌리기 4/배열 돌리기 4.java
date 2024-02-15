import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * N * M 크기 배열 A가 있을 때, 배열 A의 값은 각 행에 있는 모든 수의 합의 최솟값
 * 회전 연산 (r, c, s)인 정사각형을 시계방향으로 한칸씩 돌린다는 으미
 * 인덱스는 1부터 시작
 * 가장 왼쪽 윗칸이 (r-s, c-s) 가장 오른쪽 아랫칸이(r+s, c+s)인 정사각형을 시계방향으로 돌린다.
 * 
 * 회전 연산이 두개 이상이면 연산을 수행하는 순서에 따라 최종 배열이 다르다
 * 
 * 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값
 * 
 * 입력
 * 배열 A의 크기 N, M 회전 연산의 개수 K
 * 배열 A에 들어있는 수 
 * 회전 연산의 정보
 */
public class Main {
	static int N, M, K, idxCount = 0, answer = Integer.MAX_VALUE;
	static int[][] array, changeArray, saveArray, order, orderIndex;
	static boolean[] visited;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1}, fArray;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		array = new int[N][M];
		changeArray = new int[N][M];
		saveArray = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[K][3];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken()) - 1;
			order[i][1] = Integer.parseInt(st.nextToken()) - 1;
			order[i][2] = Integer.parseInt(st.nextToken());
			
		}
		
		// 순서의 순열 구하기
		int cnt = 1;
		for(int i = 1; i < K + 1; i++) {
			cnt *= i;
		}
		orderIndex = new int[cnt][K];
		visited = new boolean[K];
		fArray = new int[K];
		f(0);

		for(int i = 0; i < cnt; i++) {
			for(int j = 0; j < N; j++) {
				changeArray[j] = Arrays.copyOf(array[j], M);
			}
			
			for(int k = 0; k < K; k++) {
				for(int j = 0; j < N; j++) {
					saveArray[j] = Arrays.copyOf(changeArray[j], M);
				}
				int r = order[orderIndex[i][k]][0];
				int c = order[orderIndex[i][k]][1];
				int s = order[orderIndex[i][k]][2];
				
				for(int j = 0; j < s; j++) {
					int rowMin = r - s + j;
					int rowMax = r + s - j;
					int colMin = c - s + j;
					int colMax = c + s - j;
					change(rowMin, rowMax, colMin, colMax, rowMin, colMin, 0);
				}
				
				changeArray = saveArray;
				saveArray = new int[N][M];
			}
			
			
			for(int j = 0; j < N; j++) {
				int sum = 0;
				for(int k = 0; k < M; k++) {
					sum += changeArray[j][k];
				}
				answer = Math.min(answer, sum);
			}
		}
		System.out.println(answer);
	}
	
	public static void change(int rowMin, int rowMax, int colMin, 
			int colMax, int row, int col, int flag) {
		if(flag != 0 && row == rowMin && col == colMin) {
			return;
		}
		if(row + dy[flag] < rowMin || row + dy[flag] > rowMax) flag++;
		else if(col + dx[flag] < colMin || col + dx[flag] > colMax)  flag++;
		
		saveArray[row + dy[flag]][col + dx[flag]] = changeArray[row][col];
		change(rowMin, rowMax, colMin, colMax, row + dy[flag], col + dx[flag], flag);
	}
	
	public static void f(int cnt) {
		if(cnt == K) {
			orderIndex[idxCount] = Arrays.copyOf(fArray, K);
			idxCount++;
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			fArray[cnt] = i;
			f(cnt + 1);
			visited[i] = false;
		}
	}
}