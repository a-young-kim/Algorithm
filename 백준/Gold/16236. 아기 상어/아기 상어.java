import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.*;
/*
 * 문제
 * 1. N * N 크기의 공간에 물고리 M마리와 아기 상어 1마리 존재
 * 2. 한 칸에는 물고기가 최대 1마리 존재
 * 3. 아기 상어는 자신의 크기보다 큰 물고리가 있는 킨을 지나갈 수 없고, 나머지는 지나갈 수 있다. 
 * 4. 아기 상어는 자기보다 작은 물고기만 먹을 수 있다. 
 * 5. 크기가 같은 물고기는 먹을 수 없지만 지나갈 수 있다. 
 * 6. 아기 상어 이동 결정 조건
 * 	- 먹을 수 있는 물고기가 없다면 엄마 상어에 도움
 * 	- 먹을 수 있는 물고기가 1마리라면 물고기를 먹으러 간다.
 * 	- 1마리보다 많다면 가까운 물고기를 먹으로 간다. 
 * 	- 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때 지나야하는 칸의 개수의 최솟값
 * 	- 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다. 
 * 7. 상어의 이동은 1초, 물고기를 먹는데 걸리는 시간은 없다. 
 * 8. 물고기를 먹으면 그 칸을 빈칸
 * 9. 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가
 * 10. 아기 상어의 처음 크기는 2이고 1초에 상하좌우 이동 가능
 *
 * 공간의 주어졌을 때 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아 먹을 수 있는지 구하는 프로그램
 * 
 * 입력 
 * 1. 공간의 크기 N 20 이하
 * 2. N개의 줄에 공간의 상태 
 * 	0: 빈 칸
	1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
	9: 아기 상어의 위치 --> 한마리
	
	출력
	시간 
	
	아이디어
	bfs ->
	크기에 따른 개수를 저장한다. 
	물고기를 만나면 queue를 비우고 다음 단계를 진행한다. 
	
	풀이
	1. N 입력
	2. Node class 제작 -> int row, int col, int level, int count, int size(2)
	3. int[][] map 입력 -> 아기 상어 위치 노드호 만들어 넣기
		- int[] dx
		- int[] dy
	4. queue에서 첫 위치 삽입
	5. while 문 실행
	6. 해당 level의 값을 모두 queue에 삽입 --> level 별 탐색
	7. 물고기를 만나는 것을 상 -> 좌 -> 우 -> 하
	
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[] fishes = new int[7];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) continue;
				else if(map[i][j] == 9) {
					Node node = new Node(i, j, 0, 2, 0);
					queue.add(node);
					map[i][j] = 0;
					visited[i][j] = true;
				}
				else fishes[map[i][j]]++;
				
			}
		}
		
		int[] dx = {0, -1, 1, 0};
		int[] dy = {-1, 0, 0, 1};

		int answer = 0, level = 1, size = queue.size();
		
		while(!queue.isEmpty()) {
			
			if(size == 0) {
				
				PriorityQueue<Node> queue2  = new PriorityQueue<>();
				while(!queue.isEmpty()) {
					Node node = queue.poll();
					int row = node.row;
					int col = node.col;
					
					if(map[row][col] == node.size || map[row][col] == 0) {
						queue2.add(node);
						continue;
					}
					
					fishes[map[row][col]]--;
					node.count++;
					if(node.count == node.size) {
						node.count = 0;
						node.size++;
					}
					
					visited = new boolean[N][N];
					visited[row][col] = true;
					queue = new PriorityQueue<>();
					queue.add(new Node(row, col, node.level, node.size, node.count));
					answer = node.level;
					map[row][col] = 0;
					break;
				}
				
				if(queue.isEmpty()) queue = queue2;
				size = queue.size();
			}
			
			Node node = queue.poll();
			
			// 잡아먹을 물고기가 있는지 확인
			int cnt = 0;
			for(int i = 1; i < node.size; i++) {
				if(i > 6) break;
				cnt += fishes[i];
			}
			if(cnt == 0) break;

			for(int i = 0; i < 4; i++) {
				int nr = node.row + dy[i];
				int nc = node.col + dx[i];
				int nlevel = node.level + 1;
				
				if(nr < 0 || nr >= N) continue;
				if(nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] > node.size) continue;
				
				
				queue.add(new Node(nr, nc, nlevel, node.size, node.count));
				visited[nr][nc] = true;
				
			}
			size --;
		}
		

		System.out.println(answer);
	}
	
	public static class Node implements Comparable<Node>{
		int row;
		int col;
		int level;
		int size;
		int count;
		
		
		public Node(int row, int col, int level, int size, int count) {
			super();
			this.row = row;
			this.col = col;
			this.level = level;
			this.size = size;
			this.count = count;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", level=" + level + ", size=" + size + ", count=" + count
					+ "]";
		}


		@Override
		public int compareTo(Node o) {
			if(this.level == o.level) {
				if(this.row == o.row) return this.col - o.col;
				return this.row - o.row;
			}
			return this.level - o.level;
		}

	}

}