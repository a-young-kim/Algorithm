import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 정사각형 칸들로 이루어진 사각형 모양의 판
 * 그 위에 얇은 치즈가 놓여져 있다.
 * 편 가장자리에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 
 * 치즈는 공기 중에 놓으면 녹게 되는데 공기에 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 * 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게된다.
 * 
 * 입력으로 사각형 모양의 판의 크기와 한 족각의 치즈가 판 위에 주어졌을 때, 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과
 * 모두 녹기 한 시간 전에 남아 있는 치즈 조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오
 * 
 * 입력
 * 첫째 줄에는 사각형의 모양 판의 세로 길이가 양의 정수로 주어진다.
 * 세로와 가로의 길이는 최대 100
 * 판의 각 가로줄의 모양이 윗줄부터 차례로 둘쨰줄부터 마지막 줄까지 주어진다.
 * 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어진다. 
 * 
 * 출력
 * 첫쨰 줄에는 치즈가 모두 녹아서 없어지는데 걸리는 시간
 * 모두 녹기 한시간 전에 남아 있는 치즈 조각이 높여저 있는 칸의 수를 출력
 * 
 * 풀이
 * 공기가 있는 부분을 2로 표시
 * 2 주변에 0이 있으면 0으로 표시
 * 
 * dfs -> 1. 공기 있는 부분을 2로 표시
 * bfs -> 한시간마다 맡다아 있는 것 확인
 * dfs 로 공기 있는 부분 없애기
 * 공기 없는 부분 링크드리스트에 저장하여서 한시간 지날때마다 확인
 * answer 로 저장
 * 치즈가 공기가 될따 row를 받아서 주변 확인 후 2로 바꾸기
 */
public class Main {
	
	static int N, M;
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	static int[][] map;
	static Queue<Node> cheese;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cheese = new ArrayDeque<>();
		for(int i = 0; i < N ;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					Node node = new Node(i, j);
					cheese.add(node);
				}
			}
		}
		
		// 공기 2로 바꾸기
		bfs(0, 0, map);
		int size = 0;
		int count = 0;
		int time = 0;
		int[][] map2 = map;
		while(!cheese.isEmpty()) {
			if(size == 0) {
				map = map2;
				map2 = new int[N][M];
				for(int i = 0;i < N; i++) {
					map2[i] = Arrays.copyOf(map[i], M);
				}
				time ++;
				count = cheese.size();
				size = cheese.size();
			}
			
			Node n = cheese.poll();
			for(int i = 0; i < 4; i++) {
				if(n.row + dy[i] < 0 || n.row + dy[i] >= N) continue;
				if(n.col + dx[i] < 0 || n.col + dx[i] >= M) continue;
				if(map[n.row + dy[i]][n.col + dx[i]] == 2) {
					map2[n.row][n.col] = 2;
					bfs(n.row, n.col, map2);
					break;
				}
			}
			
			if(map2[n.row][n.col] != 2) {
				cheese.add(n);
			}
			size --;
		}
		System.out.println(time + " " + count);
		
	}
	
	public static void bfs(int row, int col, int[][] map) {
		boolean[][] visited = new boolean[N][M];
		
		Queue<Node> queue = new ArrayDeque<>();
		Node n = new Node(row, col);
		visited[row][col] = true;
		map[row][col] = 2;
		queue.add(n);
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				if(n.row + dy[i] < 0 || n.row + dy[i] >= N) continue;
				if(n.col + dx[i] < 0 || n.col + dx[i] >= M) continue;
				if(map[n.row + dy[i]][n.col + dx[i]] != 0) continue;
				if(visited[n.row + dy[i]][n.col + dx[i]]) continue;
				visited[n.row + dy[i]][n.col + dx[i]] = true;
				map[n.row + dy[i]][n.col + dx[i]] = 2;
				Node node = new Node(n.row + dy[i], n.col + dx[i]);
				queue.add(node);
			}
		}
	}
	
	public static class Node{
		int row;
		int col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}