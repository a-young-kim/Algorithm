import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * N * M 행렬 맵
 * 0은 이동 가능
 * 1은 이동할 수 없는 벽
 * (1, 1) 에서 (N, M)으로 이동하려고 하는데 최단 경로
 * 시작하는 칸과 끝나는 카도 포함
 * 
 * 만약 이동하는 도중에 한개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개까지 부수고 이동가능
 * 한 칸에서 이동할 수 있는 캉은 상하좌우로 인접한 칸
 * 최단 경로 구하는 프로그램
 * 
 * 입력
 * N 1000이하 M 1000이하 
 * N개의 줄에 M개의 숫자로 맵이 주어진다.
 * 시작 지점과 끝 지점은 한상 0
 * 
 * 출력
 * 최단 거리 출력
 * 불가능할 경우 -1 출력
 * 
 * 풀이
 * bfs
 * Node에 row와 col 그리고 부셨는지 확인개수 세기
 * delta
 */
public class Main {
	
	static int N, M;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static boolean[][] visited, visited2;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		visited2 = new boolean[N][M];
		
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0;j < M; j++) {
				map[i][j] = s.charAt(j) -'0';
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, true));
		visited[0][0] = true;
		
		int size = 0, answer = 0;
		boolean flag = false;
		while(!queue.isEmpty()) {
			if(size == 0) {
				size = queue.size();
				answer ++;
			}
			Node n = queue.poll();
			if(n.row == N - 1 && n.col == M - 1) {
				flag = true;
				break;
			}
			for(int i = 0; i < 4; i++) {
				if(n.row + dy[i] < 0 || n.row + dy[i] >= N) continue;
				if(n.col + dx[i] < 0 || n.col + dx[i] >= M) continue;
				// 벽을 뿌신적이 없는 
				if(n.check) {
					if(visited[n.row + dy[i]][n.col + dx[i]]) continue;
					if(map[n.row + dy[i]][n.col + dx[i]] == 1) {
						visited2[n.row + dy[i]][n.col + dx[i]] = true;
						queue.add(new Node(n.row + dy[i], n.col + dx[i], false));
					}
					else {
						visited[n.row + dy[i]][n.col + dx[i]] = true;
						queue.add(new Node(n.row + dy[i], n.col + dx[i], true));
					}
				}
				// 뿌신적 있는
				else {
					if(visited2[n.row + dy[i]][n.col + dx[i]]) continue;
					if(map[n.row + dy[i]][n.col + dx[i]] == 1) continue;
					visited2[n.row + dy[i]][n.col + dx[i]] = true;
					queue.add(new Node(n.row + dy[i], n.col + dx[i], n.check));
					
				}
			}
			size --;
		}
		if(flag) System.out.println(answer);
		else System.out.println(-1);
	}
	
	public static class Node{
		int row;
		int col;
		boolean check;
		public Node(int row, int col, boolean check) {
			this.row = row;
			this.col = col;
			this.check = check;
		}
	}

}