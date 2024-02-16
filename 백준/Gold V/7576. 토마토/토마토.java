import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 토마토는 N * M의 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관
 * 
 * 보관 후 하루만 지나면 익은 토마토들의 인접한 곳에 있는 익은 토마토의 영향을 받아 익게된다.
 * 인접한 곳은 왼오위아래를 의미하면 대각선 방향에 있는 토마토는 영향을 주지 못한다.
 * 또한 토마토가 혼자 저절로 익는 경우가 없다.
 * 창고에 보관된 토마토들이 며칠이 지나면 다 익게되는지 최소 일수를 구하여라
 * 
 * 칸 일부에는 토마토가 없을 수도 있다. 
 * 
 * 입력
 * 상자의 크기 N, M 세로 가로 2이상 1000이하
 * 하나의 상자에 저정된 토마토의 정보
 * 상자에 담긴 토마토의 정보
 * 1은 익은 토마토 0은 익지 않은 토마토 정수 -1은 토마토가 들어 있지 않은 칸을 나타낸다
 * 
 * 토마토가 하나 이상인 경우에만 입력으로 주어진다.
 * 
 * 출력
 * 토마토가 모두 익을 때까지의 최소 날짜 출력
 * 저장될 때부터 모든 토마토가 익어 있는 상태이면 0출력
 * 토마토가 익지 못하는 상황이면 -1 출력
 * 
 * 풀이 과정
 * 저장시 익은 위치의 토마토를 배열에 받고 해당 queue를 돌면서 주변 안 익은 것 익게 만들기
 * 안 익은 것 num 처음 부터 세서 감소
 * 주변 모든 토마토가 익었으면 queue에서 아웃
 */
public class Main {
	static int N, M, notOne;
	static int[][] array;
	static boolean[][] visited;
	static int[] dx = {1, 0, 0, -1}, dy = {0, -1, 1, 0};
	static Queue<Integer[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j < M ; j++) {
				int n = Integer.parseInt(st.nextToken());
				array[i][j] = n;
				if(n == 0) 	notOne ++;
				else if(n == 1) {
					queue.add(new Integer[] {i, j});
					visited[i][j] = true;
				}
			}
		}

		int answer = 0;
		int size = 0;
		
		while(!queue.isEmpty()) {
			if(size == 0) {
				answer++;
				size = queue.size();
			}
			
			Integer[] index = queue.poll();
			int row = index[0];
			int col = index[1];
			
			for(int k = 0; k < 4; k++) {
				if(row + dy[k] >= N || row + dy[k] < 0) continue;
				if(col + dx[k] >= M || col + dx[k] < 0) continue;
				
				if(visited[row + dy[k]][col + dx[k]]) continue;
				visited[row + dy[k]][col + dx[k]] = true;
				
				if(array[row + dy[k]][col + dx[k]] == 0) {
					notOne --;
					array[row + dy[k]][col + dx[k]] = 1;
					queue.add(new Integer[] {row + dy[k], col + dx[k]});
				}
			}
			size --;
		}
		if(notOne != 0) System.out.println(-1);
		else System.out.println(answer - 1);
	}
}