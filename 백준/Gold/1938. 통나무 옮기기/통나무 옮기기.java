import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 기차 놀이 공간은 0과 1로 표현
 * 2. 1으 평지에 존재하는 나무, 0은 빈 공간
 * 3. BBB로 표현한다고 할떄, BBB를 이동시키거나 회전시켜 EEE위치로 이동
 * 4. 기차의 길이는 항상 3이며 B의 개수와 E의 개수가 같다고 할 때 다음과 같은 규칙 존재
 * 	4.1 U 기차를 위로 한칸 옮긴다. 
 * 	4.2 D 기차를 아래로 한칸 옮긴다. 
 * 	4.3 L 기차를 왼쪽으로 한 칸 옮긴다. 
 * 	4.4 R 기차를 오른쪽으로 한 칸 옮긴다. 
 * 	4.5 T 기차의 중심점을 중심으로 90도 회전시킨다
 * 5. 기차가 이동할 때 그 위치에 나무가 가로막고 있으면 이동할 수 없다. 
 * 6. 또한 기차는 한번에 한칸만 움직으며 대각선으로 놓일 수 없다
 * 7. 90도 회전을 할 경우 중심점을 기준으로 3*3구역에 한개의 나무도 존재해서는 안된다.
 * 8. 기차를 5가지의 기본 동작만을 사용하여 처음위치에서 최종 위치로 
 * 	최소 횟수 동작으로 움직이는 프로그램 작성
 * 
 * 입력
 * 1. 한변의 길이 N 50 이하
 * 2. 공간의 정보 0 1 B E, 한줄에 입력되는 문자열은 N이며 문자 사이에는 빈칸이 없다
 * 
 * 출력
 * 최소 동작 횟수 출력
 * 이동이 불가능하면 0 출력
 * 
 * 아이디어
 * bfs
 * 
 * 풀이
 * 1. int N 입력
 * 2. int[][] map 입력
 * 3. class Node 입력
 * 	3.1 row, col, level, state(0 가로 위치, 1 세로 위치 판단)
 * 4. Queue 생성
 * 5. B의 중심점을 기준으로 Node 생성, E의 중심점을 기준으로 node 생성
 * 6. boolean[2][N][N] visited 생성 (0: 가로로 있을 때, 1:세로로 있을 때)
 * 7. bfs 실행
 * 
 * bfs
 * 1. while queue에 node가 있을 때
 * 2. Node node = queue.poll()
 * 3. for i 가 5까지
 * 3.1. i = 0일때 위로, Node nextNode = goUp()
 * 3.2 i = 1일 때 아래,  Node nextNode = goDown()
 * 3.3 i = 2일 때 왼쪽,  Node nextNode = goLeft()
 * 3.4 i = 3일 때 오른쪽,  Node nextNode = goRight()
 * 3.5 i = 4일 때 90도 회전,  Node nextNode = goTurn()
 * 4. if(nextNode.row = eNode.row && nextNode.col = eNode.col
 * 			 && nextNode.state = eNode.state) answer = nextNode.level; break;
 * 
 * 5. 출력
 * 
 * def Node goUP(Node node) --> 나머지도 비슷
 * 1. int nr = node.row - 1;, int nc = node.col  // 중심
 * 2. if(nr - 1 < 0) return
 * 3. if(map[nr][nc] == 1) return; 
 * 4. if(visited[node.state][nr][nc]) return;
 * 5. visited[node.state][nr][nc] = true;
 * 6. return new Node(nr, nc, node.state, node.level + 1)
 * 
 * def goTurn(Node node)
 * 1. int nr = node.row; int nc = node.col
 * 2. int nstate = node.state == 0 ? 1: 0;
 * 3. if(visited[nstate][nr][nc]) return;
 * 4. for i 가 0부터 3까지
 * 4.1 for j가 0부터 3까지
 * 4.2 map[i][j] == 1 return
 * 5.  visited[state][nr][nc] = true;
 * 6. return new Node(nr, nc, nstate, node.level + 1
 */
public class Main {

	static int N;
	static int[] dx = {-1, 0, 1}, dy= {-1, 0, 1};

	static char[][] map;
	static boolean[][][] visited;
	static Node bNode, eNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		visited = new boolean[2][N][N];

		int bCount = 0, eCount = 0;
		int bRow = 1, eRow = 1;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			bRow = 1;
			eRow = 1;

			for(int j = 0; j < N; j++) {
				char c = s.charAt(j);

				if(c == 'B') {
					bCount ++;
					if(bCount == 2) bNode = new Node(i, j, 0, bRow);
					bRow = 0;
				}
				else if(c == 'E') {
					eCount ++;
					if(eCount == 2) eNode = new Node(i, j, 0, eRow);
					eRow = 0;
				}

				if(c == '1') map[i][j] = c;
				else map[i][j] = '0';
			}
		}

		Queue<Node> queue = new ArrayDeque<>();
		queue.add(bNode);
		visited[bNode.state][bNode.row][bNode.col] = true;
		int answer = 0;

		first: while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i = 0; i < 5; i++) {
				Node nextNode = null;

				switch (i) {
				case 0:
					nextNode = goUp(node);
					break;

				case 1:
					nextNode = goDown(node);
					break;

				case 2:
					nextNode = goLeft(node);
					break;

				case 3:
					nextNode = goRight(node);
					break;

				case 4:
					nextNode = goTurn(node);
					break;
				}

				if(nextNode == null) continue;
				if(nextNode.row == eNode.row && nextNode.col == eNode.col && nextNode.state == eNode.state) {
					answer = nextNode.level;

					break first;
				}
				queue.add(nextNode);
			}
		}
		
		System.out.println(answer);
	}
	
	public static void print() {
		System.out.println("new");
		for(int i =0 ;i < N; i++) System.out.println(Arrays.toString(visited[0][i]));
		System.out.println();
		
		for(int i =0 ;i < N; i++) System.out.println(Arrays.toString(visited[1][i]));
		System.out.println();
	}
	public static Node goUp(Node node) {
		int nr = node.row - 1, nc = node.col;
		
		if(nr < 0) return null;
		if(visited[node.state][nr][nc]) return null;
		
		if(node.state == 1) {
			if(nr - 1 < 0) return null;
			if(map[nr - 1][nc] == '1') return null;
		}
		else if(node.state == 0) {
			
			for(int i = 0; i < 3; i++) {
				int nnc = nc + dx[i];
				if(map[nr][nnc] == '1') return null;
			}
		}
		

		visited[node.state][nr][nc] = true;
		return new Node(nr, nc, node.level + 1, node.state);
	}

	public static Node goDown(Node node) {
		int nr = node.row + 1, nc = node.col;

		if(nr >= N) return null;
		if(visited[node.state][nr][nc]) return null;
		
		if(node.state == 1) {
			if(nr + 1 >= N) return null;
			if(map[nr + 1][nc] == '1') return null;
		}
		else if(node.state == 0) {
			for(int i = 0; i < 3; i++) {
				int nnc = nc + dx[i];
				if(map[nr][nnc] == '1') return null;
			}
		}
		

		visited[node.state][nr][nc] = true;
		return new Node(nr, nc, node.level + 1, node.state);
	}

	public static Node goLeft(Node node) {
		int nr = node.row, nc = node.col - 1;
		
		if(nc < 0) return null;
		if(visited[node.state][nr][nc]) return null;
		
		if(node.state == 0) {
			if(nc - 1 < 0) return null;
			if(map[nr][nc - 1] == '1') return null;
		}
		else if(node.state == 1) {
			
			for(int i = 0; i < 3; i++) {
				int nnr = nr + dx[i];
				if(map[nnr][nc] == '1') return null;
			}
		}

		visited[node.state][nr][nc] = true;
		return new Node(nr, nc, node.level + 1, node.state);
	}

	public static Node goRight(Node node) {
		int nr = node.row, nc = node.col + 1;

		if(nc >= N) return null;
		if(visited[node.state][nr][nc]) return null;
		
		if(node.state == 0) {
			if(nc + 1 >= N) return null;
			if(map[nr][nc + 1] == '1') return null;
		}
		else if(node.state == 1) {
			
			for(int i = 0; i < 3; i++) {
				int nnr = nr + dx[i];
				if(map[nnr][nc] == '1') return null;
			}
		}

		visited[node.state][nr][nc] = true;
		return new Node(nr, nc, node.level + 1, node.state);
	}

	public static Node goTurn(Node node) {
		int nr = node.row, nc = node.col;
		int nstate = node.state == 0 ? 1: 0;

		if(visited[nstate][nr][nc]) return null;

		for(int i = 0; i < 3; i++) {
			int nnr = nr + dy[i];

			if(nnr < 0 || nnr >= N) return null;
			for(int j = 0; j < 3; j++) {
				int nnc = nc + dx[j];

				if(nnc < 0 || nnc >= N) return null;
				if(map[nnr][nnc] == '1') return null;
			}
		}

		visited[nstate][nr][nc] = true;
		return new Node(nr, nc, node.level + 1, nstate);
	}


	public static class Node{
		int row;
		int col;
		int level;
		int state;

		public Node(int row, int col, int level, int state) {
			super();
			this.row = row;
			this.col = col;
			this.level = level;
			this.state = state;
		}
		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", level=" + level + ", state=" + state + "]";
		}
	}
}