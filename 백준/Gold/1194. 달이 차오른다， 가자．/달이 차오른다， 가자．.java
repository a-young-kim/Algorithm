import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 미로 탈출을 하려고 한다. 미로는 다음과 같이 구성된다. 
 * 	- '.': 빈칸, 이동 가능
 * 	- '#': 이동 불가
 * 	- ('a', 'b', 'c', 'd', 'e', 'f'): 열쇠, 언제나 이동 가능
 * 	- ('A', 'B', 'C', 'D', 'E', 'F'): 대응하는 열쇠가 있어야 한다. 
 * 	- '0': 현재 위치
 * 	- '1': 출구
 * 
 * 2. 움직임은 수평 혹은 수직으로 이동
 * 3. 이동 횟수의 최솟값
 * 
 * 입력
 * 1. 미로의 세로 N 가로 M 둘다 50 이하
 * 2. 미로의 모양
 * 3. 열쇠는 여러번 사용 가능
 * 
 * 아이디어
 * 1. 열쇠의 유무는 hashMap에서 확인
 * 2. bfs 탐색
 * 3. N * M * visited 
 * 
 * 풀이
 * 1. N, M 입력
 * 2. class Node 생성, row, col, level, key -> 기본은 0
 * 3. HashMap에 visited배열 저장
 * 	-> key 문자열의 아스키코드 값 등
 * 4. bfs 탐색을 돌면서 확인 후 문에 도착하면 break
 * 	bfs 탐색
 * 	- node poll()
 * 	- 가지고 있는 key 확인해서 visited 고려
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][][] visitedMap = new boolean[64][N][M];
		
		char[][] map = new char[N][M];
		
		Node node = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s= st.nextToken();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '0') {
					node = new Node(i, j, 0, 000000);
					visitedMap[node.key][i][j] = true;
				
				}
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		int answer = -1;
		first: while(!queue.isEmpty()) {
			node = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = node.row + dy[i];
				int nc = node.col + dx[i];
				int nextLevel = node.level + 1;
				
				if(nr < 0 || nr >= N) continue;
				if(nc < 0 || nc >= M) continue;
				
				if(visitedMap[node.key][nr][nc]) continue;
				
				if(map[nr][nc] == '#') continue;
				else if(map[nr][nc] == '1') {
					answer = nextLevel;
					break first;
				}
				else if(map[nr][nc] == '.' || map[nr][nc] == '0') {
					queue.add(new Node(nr, nc, nextLevel, node.key));
					visitedMap[node.key][nr][nc] = true;
					
				}
				else if(map[nr][nc] < 91) {
					// 문
					int key = node.key;
					if(key == 0) continue;
					int keyNum = map[nr][nc] - 65;
					if((key & (1 << keyNum)) != 0) {
						queue.add(new Node(nr, nc, nextLevel, node.key));
					}
					visitedMap[node.key][nr][nc] = true;
					
				}
				else {
					// 키
					int keyNum = map[nr][nc] - 97;
					int newKey = (node.key | (1 << keyNum));
					queue.add(new Node(nr, nc, nextLevel, newKey));
					visitedMap[node.key][nr][nc] = true;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static class Node {
		int row;
		int col;
		int level;
		int key;
		
		public Node() {}
		
		public Node(int row, int col, int level, int key) {
			super();
			this.row = row;
			this.col = col;
			this.level = level;
			this.key = key;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", level=" + level + ", key=" + key + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		
		
	}

}