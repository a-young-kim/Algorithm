import java.util.*;
import java.io.*;

/*
 * 문제
 * 뿌요 게임
 * 1. 필드에 여러 가지 색깔의 뿌요를 놓는다. 
 * 2. 뿌요는 중력의 영향을 받아 바닥이나 다른 뿌요 위로 떨어진다. 
 * 3. 뿌요를 놓고 난 후, 같은 색의 뿌요가 4개 이상 상하좌우로 연결되어 있으면 같은 색의 뿌요들이 한번에 없어진다. 
 * 	-> 1연쇄
 * 4. 뿌요들이 없어지고나서 위에 다른 뿌요들이 있다면 이들 역시 중력의 영향을 받아 아래로 떨어진다. 
 * 5. 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터진다.
 * 6. 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때 1연쇄 늘어난다. 
 * 7. 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야하고 여러 그룹이 터지더라도 한번의 연쇄가 추라된다. 
 * 
 * 상대방이 필드가 주어졌을 때 연쇄가 몇번 연속으로 일어날지 계산하자
 * 
 * 입력
 * 1. 12 * 6 크기의 map 입력
 * 2. .은 빈공간 나머지는 각각 색깔의 뿌요
 * 3. R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑
 * 4. 입력으로 주어지는 field는 뿌요들이 전부 아래로 떨어진 뒤
 * 
 * 출력
 * 몇 연쇄가 발생하는지 하나도 없으면 0
 * 
 * 풀이
 * 1. int[12][6] 입력
 * 2. while문 실행
 * 3. bfs 탐색
 * 4. bfs에서 뿌요가 탐색되지 않는다면 break
 * 5. answer 출력
 * 
 * bfs 탐색
 * 1. for i 12미만
 * 2. for j 6이하
 * 3. 뿌요를 만날 때까지 탐색
 * 4. 같은 색이 4개 이상 연달아 있으면 터트리기
 * 5. poll 할때 List에 저장해 놓기
 * 
 */
public class Main {
	
	static int[] dx= {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		char[][] map = new char[12][6];
		for(int i = 0; i < 12; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			for(int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}

		}
		
		int answer = 0;
		while(true) {
			if(!bfs(map)) break;
			down(map);
			answer++;
		}
		System.out.println(answer);
	}
	
	public static void down(char[][] map) {
		
		Deque<Character> deque = new ArrayDeque<Character>();
		for(int col = 0; col < 6; col++) {
			for(int row = 11; row >= 0; row--) {
				if(map[row][col] == '.') continue;
				deque.add(map[row][col]);
				map[row][col] = '.';
			}
			
			int row = 11;
			while(!deque.isEmpty()) {
				map[row][col] = deque.pollFirst();
				row--;
			}
		}
	}
	
	public static boolean bfs(char[][] map) {
		
		boolean[][] visited = new boolean[12][6];
		boolean check = false;
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] == '.') continue;
				if(visited[i][j]) continue;
				
				visited[i][j] = true;
				
				Node node = new Node(i,j);
				
				List<Node> save = new ArrayList<>();
				Queue<Node> queue = new ArrayDeque<>();
				queue.add(node);
				
				while(!queue.isEmpty()) {
					node = queue.poll();
					save.add(node);
					
					for(int k = 0; k < 4; k++) {
						int nr = node.row + dy[k];
						int nc = node.col + dx[k];
						
						if(nr < 0 || nr >= 12) continue;
						if(nc < 0 || nc >= 6) continue;
						if(map[nr][nc] != map[i][j]) continue;
						if(visited[nr][nc]) continue;
						
						visited[nr][nc] = true;
						queue.add(new Node(nr, nc));
					}
				}
				
				if(save.size() >= 4) {
					check = true;
					for(Node dNode: save) {
						map[dNode.row][dNode.col] = '.';
					}
				}
			}
		}
		
		return check;
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