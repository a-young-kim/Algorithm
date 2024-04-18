import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. N * M 행렬
 * 2. 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽
 * 3. 두 칸이 벽을 공유할 경우, 인접한다고 표현
 * 4. 현재 벽을 부수고 이동할 수 있는 곳으로 변경 
 * 5. 그 위치에서 이동하 룻 있는 칸의 수 세기
 * 
 * 시간 복잡도
 * 1000 * 1000 * 1000 (중간에 벽이 있어서 이것보다는 적게 나올 듯)
 * 
 * 풀이
 * 1. N, M 입력
 * 2. int[N][M] map, answer 생성 후 map 입력
 * 3. for row 0 ~ N, for col 0 ~ M
 * 4. map[row][col] == 0이고 방문한 적이 없을 경우
 * 5. bfs 탐색 시작
 * 6. answer 만들기
 * 6.1 for row 0 ~ N, col 0 ~M까지 , 벽일 경우
 * 6.2 사방 탐색하여 해당 위치 가 가지고 있는 countMap 확인
 * 6.3 countMap 확인 시 root가 같으면(같은 그룹이면) 한번만 더함
 * 7. answer 출력
 * 
 * bfs 탐색
 * 1. class Node 생성, row, col
 * 2. queue(new Node(row, col))
 * 3. for i 가 4이하
 * 4. nr, nc 입력
 * 5. 해당 값이 0이고 root[nr][nc] == null 이면 queue넣기, root[nr][nc] = new Node(row, col)
 * 6. result ++;
 * 7. while문이 끝나면 answer[row][col] = result
 */
public class Main {
	
	static int N, M;
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	static int[][] map, answer, countMap;
	static Node[][] root;
	static Queue<Node> queue;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		countMap = new int[N][M];
		root = new Node[N][M];
		answer = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) -'0';
			}
		}
		
		// 루트 지정해주기
		for(int row = 0; row < N; row ++) {
			for(int col = 0; col < M; col ++) {
				if(map[row][col] == 1) continue;
				if(root[row][col] != null) continue;
				
				queue = new ArrayDeque<>();
				queue.add(new Node(row, col));
				
				int count = 1;
				root[row][col] = new Node(row, col);
				while(!queue.isEmpty()) {
					Node node = queue.poll();
					
					for(int i = 0; i < 4; i++) {
						int nr = node.row + dy[i];
						int nc = node.col + dx[i];
						
						if(nr < 0 || nr >= N) continue;
						if(nc < 0 || nc >= M) continue;
						
						if(map[nr][nc] == 1) continue;
						if(root[nr][nc] != null) continue;
						
						queue.add(new Node(nr, nc));
						root[nr][nc] = new Node(row, col);
						count++;
					}
				}
				countMap[row][col] = count;
			}
		}
		
		
		//answer 만들기
		boolean[][] visited = new boolean[N][M];
		for(int row = 0; row < N; row ++) {
			for(int col = 0; col < M; col ++) {
				if(map[row][col] != 1) continue;
				
				
				int result = 0;
				for(int i = 0; i < 4; i++) {
					int nr = row + dy[i];
					int nc = col + dx[i];
					
					if(nr < 0 || nr >= N) continue;
					if(nc < 0 || nc >= M) continue;
					if(map[nr][nc] == 1) continue;
					
					Node rootNode = root[nr][nc];
					if(visited[rootNode.row][rootNode.col]) continue;
					
					visited[rootNode.row][rootNode.col] = true;
					result += countMap[rootNode.row][rootNode.col];
				}
				answer[row][col] = result + 1;
				
				for(int i = 0; i < 4; i++) {
					int nr = row + dy[i];
					int nc = col + dx[i];
					
					if(nr < 0 || nr >= N) continue;
					if(nc < 0 || nc >= M) continue;
					if(map[nr][nc] == 1) continue;
					
					Node rootNode = root[nr][nc];
					
					visited[rootNode.row][rootNode.col] = false;
				}
			}
		}
		
		for(int row = 0; row < N; row ++) {
			for(int col = 0; col < M; col++) {
				sb.append(answer[row][col] % 10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
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