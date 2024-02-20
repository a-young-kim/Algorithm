import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 세로  R 가로 C로된 표 모양의 보드
 * 보드의 각 칸에는 대문자 알파벳 하나씩
 * 1행 1열 , 좌측 상단에는 말이 놓여 있다. 
 * 말은 상하좌우 중 한 칸으로 이동할 수 있음
 * 같은 알파벳이 적힌 칸은 두 번 지날 수 없다.
 * 
 * 좌측 상단에서 시작해서 말이 최대한 몇 칸 지날 수 있는지 구하는 프로그램
 * 말이 지나는 칸은 시작 지점 포함
 * 
 * 입력
 * R, C 20 이하
 * R개의 줄에 걸쳐서 보드에 적혀있는 C개의 대문자 알파벳들이 빈 칸 없이 주어짐
 * 
 * 출력
 * 최대 칸 수 
 * 
 * 풀이 
 * dfs 재귀
 * visited를 이용해서 사용된 알파벳 확인
 */
public class Main {
	
	static int R, C, answer = Integer.MIN_VALUE;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static boolean[] visited = new boolean[26];
	static int[][] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		array = new int[R][C];
		for(int i = 0;i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			for(int j = 0;j < C; j++) {
				array[i][j] = word.charAt(j) - 'A';
			}
		}
		visited[array[0][0]] = true;
		dfs(1, 0, 0);
		System.out.println(answer);
	}
	
	public static void dfs(int cnt, int row, int col) {
		
		answer = Math.max(answer,  cnt);

		for(int i = 0; i< 4; i++) {
			if(row + dy[i] < 0 || row + dy[i] >= R) continue;
			if(col + dx[i] < 0 || col + dx[i] >= C) continue;
			if(visited[array[row + dy[i]][col + dx[i]]]) continue;
			visited[array[row + dy[i]][col + dx[i]]] = true;
			dfs(cnt + 1, row + dy[i], col + dx[i]);
			visited[array[row + dy[i]][col + dx[i]]] = false;
		}
	}
}