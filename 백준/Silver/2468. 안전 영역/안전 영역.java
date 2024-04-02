import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 비가 내렸을 때 물에 잠기기 않는 안전한 영역이 최대 몇개
 * 2. N * N 배열, 각 원소들을 해당 지점의 위치
 * 3. 물에 잠기지 않은 안전한 영역은 인접한 영역은 하나의 영역으로 두고 계산
 * 4. 높이 정보가 주어졌을 때 장마철에 물에 잡기지 않는 안전한 영역의 최대 개수를 계산 --> 잠기는 높이를 지정하지 않음
 * 
 *입력
 *1. N입력 100 dlgk
 *2. N * N 배열의 정보 입력
 *3. 높이는 100이하
 *
 *아이디어 
 * bfs
 * 
 * 시간 복잡도
 * N * N * 100 = 백만
 * 
 * 풀이
 * 1. N 입력
 * 2. int[][] map 생성
 * 3. 데이터 입력 -> 최대 높이 저장
 * 4. for문으로 최대 높이 - 1까지 돌리기 
 * 		4.1 int cnt = 0;
 * 		4.2 for row 0부터 N까지 
 * 		4.3 for col는 0부터 N까지 -> 높이보다 값이 클경우 bfs 탐색 시작
 * 5. bfs 탐색
 * 6. 최대 개수 answer에 저장 
 * 7. 출력
 * 
 * bfs 탐색
 * 1. boolean[][] visited  생성
 * 2. queue에 현재 위치 넣기
 * 3. while 시작
 * 4. poll()
 * 5. 주변 값이 높이보다 클경우에만 queue에 넣기
 */
public class Main {
	
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int H = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > H) H = map[i][j];
			}
		}
		
		int answer = 0;
		for(int i = 0; i < H; i++) {
			int cnt = 0;
			boolean[][] visited = new boolean[N][N];
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(map[row][col] <= i) continue;
					if(visited[row][col]) continue;
					visited[row][col] = true;
					
					bfs(i, new Node(row, col), visited);
					cnt++;
				}
			}
			answer = Math.max(answer,  cnt);
		}
		System.out.println(answer);
	}
	
	public static void bfs(int height, Node sNode, boolean[][] visited) {
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(sNode);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = node.row + dy[i];
				int nc = node.col + dx[i];
				
				if(nr < 0 || nr >= N) continue;
				if(nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] <= height) continue;
				
				visited[nr][nc] = true;
				queue.add(new Node(nr, nc));
			}
		}
	}
	
	
	public static class Node{
		int row;
		int col;
		
		
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}
	}

}