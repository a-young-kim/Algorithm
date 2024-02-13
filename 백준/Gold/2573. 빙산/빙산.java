import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 빙산을 2차원 배열에 표시
 * 빙산의 높이 정보는 배열의 각 칸에 양의 정수로 저장되며 바다는 0으로 저장
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어든다.
 * 칸의 높이는 일년마다 네 방향에 붙어있는 0의 저장된 칸의 개수만큼 줄어든다. 
 * 0보다 줄어들지는 않는다. 
 * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성
 * 
 * 입력
 * 첫 줄, 이차원 배열의 행의 개수와 열의 개수 N M 3이상 300이하
 * N개의 줄에 각 줄마다 배열의 각 행을 나타내는 M개의 정수, 각 칸에 들어가는 값은 0이상 10이하
 * 배열에서 빙산이 차지하는 칸의 개수는 10000개 이하
 * 배열의 첫번째 행과 열, 마지막 행과 열을 항상 0으로 채워진다. 
 * 
 * 출력
 * 최초의 시간을 출력 
 * 빙산이 다 녹을 때까지 분리되지 않으면 0 출력
 * 
 * 풀이 방식
 * 
 * while문을 이용해서 계속 년을 증가시킨다. 
 * dp를 이용해여 주변의 0의 개수를 저장한다.
 * 빙산의 개수를 세고 두 덩어리 이상으로 분리될 경우 한 덩어리만 세고 빙산의 개수보다 작으면 답
 * 재귀로 빙상의 덩어리를 센다. 
 */
public class Main {
	static int N, M, answer;
	static int[][] array, zeroCountArray;
	static boolean[][] visited;
	static int[] dx = {1, 0, 0, -1}, dy = {0, 1, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		// 빙산의 row, col를 담고 있음
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j ++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if(array[i][j] != 0) {
					queue.add(i);
					queue.add(j);
				}
			}
		}
		zeroCountArray = new int[N][M];
		// 주변의 0의 개수를 센다. 
		int size = queue.size();
		for(int i = 0; i < size / 2; i++) {
			int row = queue.poll();
			int col = queue.poll();
			int cnt = 0;
			for(int k = 0; k < 4; k++) {
				if(array[row][col] != 0 && array[row + dy[k]][col + dx[k]] == 0) cnt++;
			}
			zeroCountArray[row][col] = cnt;
			queue.add(row);
			queue.add(col);
		}
		
		
		while(!queue.isEmpty()) {
			// 빙산의 높이의 감소
			int[][] nextArray = new int[N][M];
			for(int i = 0; i < N; i++) {
				nextArray[i] = Arrays.copyOf(zeroCountArray[i], M);
			}
			size = queue.size();
			for(int i = 0; i < size / 2 ; i++) {
				int row = queue.poll();
				int col = queue.poll();
				array[row][col] = array[row][col] - zeroCountArray[row][col] > 0 ? 
						array[row][col] - zeroCountArray[row][col] : 0;
				if(array[row][col] == 0) {
					for(int k = 0; k < 4; k++) {
						nextArray[row + dy[k]][col + dx[k]] ++;
					}
				}
				else {
					queue.add(row);
					queue.add(col);
				}
			}
			answer++;
			zeroCountArray = nextArray;
			// 덩어리 수 비교
			if(queue.isEmpty()) break;
			int row = queue.poll();
			int col = queue.poll();
			visited = new boolean[N][M];
			visited[row][col] = true;
			int visitedCount = f(row, col, 1);
			queue.add(row);
			queue.add(col);
			
			if(visitedCount != queue.size()/2) break;
		}
		
		if(queue.isEmpty()) answer = 0;
		System.out.println(answer);
	}
	public static int f(int row, int col, int visitedCount) {

		for(int i = 0 ; i < 4; i++) {
			if(array[row + dy[i]][col + dx[i]] == 0) continue;
			if(visited[row + dy[i]][col + dx[i]]) continue;
			visited[row + dy[i]][col + dx[i]] = true;
			visitedCount++;
			visitedCount = f(row + dy[i], col + dx[i], visitedCount);
		}
		
		return visitedCount;
	}

}