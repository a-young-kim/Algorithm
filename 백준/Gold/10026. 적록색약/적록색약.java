import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 
 * 크기가 N * N인 그리드인 각 칸에 RGB 중 하나를 색칠한 그림이 있다. 
 * 그림은 볓 개의 구역으로 나누어져 있는데 구역은 같은 색으로 이루어져 있다. 
 * 또한 같은 색상이 상하좌우로 인접해 있는 경우 두 글자는 같은 구역이다. 
 * 색상의 차이를 거의 느끼지 못하는 경우에도 같은 색상이라고 한다. 
 * 
 * 입력
 * 첫쨰 줄 N 100이하
 * 둘째 줄 부터 N개의 줄에 그림
 * 
 * 출력
 * 적록색약이 아닌 사람이 봤을 떄 구역 수 " " 적록색약인 사람이 봤을 때 구역 수
 * 
 * 풀이
 * 적록색약인 사람을 위해 입력을 받을 때 
 * G가 들어오면 R로 변경
 * 
 * visited 사용
 * 인덱스 값을 넣어서 시작
 * 같은 색일 때 이동
 * 
 * dfs -> 재귀
 * bfs -> while 문 queue
 */
public class Main {
	
	static int N;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static char[][] noColor, color;
	static boolean[][] noColorVisited, colorVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		noColor = new char[N][N];
		color = new char[N][N];
		
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String colors = st.nextToken();
			
			for(int j = 0;j < N; j++) {
				char c = colors.charAt(j);
				if(c == 'G') noColor[i][j] = 'R';
				else noColor[i][j] = c;
				color[i][j] = c;
			}
		}
		
		int colorCnt = 0;
		colorVisited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0;j < N; j++) {
				if(colorVisited[i][j]) continue;
				colorVisited[i][j] = true;
				colorCnt++;
				dfs(i, j, color[i][j], true);
			}
		}
		
		int noColorCnt = 0;
		noColorVisited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0;j < N; j++) {
				if(noColorVisited[i][j]) continue;
				noColorVisited[i][j] = true;
				noColorCnt++;
				dfs(i, j, noColor[i][j], false);
			}
		}
		System.out.println(colorCnt + " " + noColorCnt);
	}
	
	public static void dfs(int row, int col, char c, boolean flag) {
		
		for(int i = 0;i < 4; i++) {
			if(row + dy[i] < 0 || row + dy[i] >= N) continue;
			if(col + dx[i] < 0 || col + dx[i] >= N) continue;
			if(flag) {
				if(colorVisited[row + dy[i]][col + dx[i]]) continue;
				if(c == color[row + dy[i]][col + dx[i]]) {
					colorVisited[row + dy[i]][col + dx[i]] = true;
					dfs(row + dy[i], col + dx[i], c, flag);
				}
			}
			else{
				if(noColorVisited[row + dy[i]][col + dx[i]]) continue;
				if(c == noColor[row + dy[i]][col + dx[i]]) {
					noColorVisited[row + dy[i]][col + dx[i]] = true;
					dfs(row + dy[i], col + dx[i], c, flag);
				}
			}
		}
	}
}