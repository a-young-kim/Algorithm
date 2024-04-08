import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 파란 구슬과 빨간 구슬 존재
 * 2. 보드의 크기 N * M
 * 3. 가장 바깥 행과 열은 모두 막혀있음, 보드에는 구멍 존재
 * 4. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것 -> 파란 구슬은 구멍으로 들어가면 안됨
 * 5. 구슬은 중력을 이용하여 굴릴 수 있다. 상하좌우 가능
 * 6. 각각 동작에서 공은 동시에 움직안다. 
 * 7. 두 구슬이 동시에 움직여도 실패
 * 8. 두 구슬이 동시에 같은 칸에 있을 수 있다.
 * 9.기울이는 동작을 그만하는 것은 구슬이 움직이지 않은 때
 * 10. 최소 몇번만에 빨간 구슬을 구멍에서 빼낼 수 있는지
 * 
 * 입력
 * 1. N, M 존재 10이하
 * 2. N개의 줄에 M개의 문자열
 * 	- '.': 빈칸
 * 	-'#': 벽
 *  -'o' : 구멍
 * 	-'R':빨간 구슬 위치
 * 	-'B':파란 구슬 위치
 * 3. 가장자리는 모두 #, 구멍의 개수는 한개이며 구슬은 각각 1개
 * 
 * 출력
 * 최소 몇번만에 빨간 구슬을 빼낼 수 있는지
 * 10번 이하로 움직여서 빼낼 수 없으면 -1 출력
 * 
 * 아이디어
 * 조합
 * 
 * 시간복잡도
 * 2의 10승
 * 
 * 풀이
 * 
 */
public class Main {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[] save;
	static int[] answerArr = {2, 1, 3, 1, 2};
	
	static char[][][] map;
	static Node[] reds, blues;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[11][N][M];
		reds = new Node[11];
		blues = new Node[11];
		
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0 ; j < M; j++) {
				map[0][i][j] = s.charAt(j);
				if(map[0][i][j] == 'R') {
					reds[0] = new Node(i, j, 'R');
				}
				else if(map[0][i][j] == 'B') {
					blues[0] = new Node(i, j, 'B');

				}
			}
		}
		save = new int[10];
		Arrays.fill(save,  -1);
		
		f(0);

		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);

	}
	
	public static void f(int cnt) {

		if(cnt >= answer) return;
		if(cnt == 10) return;
		
		// 0123 상하좌우
		for(int i = 0; i < 4; i++) {
			save[cnt] = i;

			int next = move(i, cnt);
			if(next == 1) {
				answer = Math.min(answer,  cnt + 1);
				return;
			}
			else if(next == -1) continue;
			else f(cnt + 1);

			save[cnt] = -1;
		}
		return;
	}
	public static void print(int cnt) {
		for(int i = 0; i < N; i++) System.out.println(Arrays.toString(map[cnt][i]));
		System.out.println();
	}
	
	public static int move(int direct, int cnt) {
	
		for(int i = 0; i < N; i++) System.arraycopy(map[cnt][i], 0, map[cnt + 1][i],  0,  M);

		// 상하좌우
		switch(direct) {
		case 0:{
		
			if(blues[cnt].row < reds[cnt].row) {

				/*if(moveTop(blues, cnt)) return -1;
				if(moveTop(reds, cnt)) return 1;*/
				
				boolean resultBlue = moveTop(blues, cnt);
				boolean resultRed = moveTop(reds, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}
			
			else {

				/*if(moveTop(reds, cnt)) return 1;
				if(moveTop(blues, cnt)) return -1;*/
				
				boolean resultRed = moveTop(reds, cnt);
				boolean resultBlue = moveTop(blues, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}

		}
		case 1:{
			if(blues[cnt].row > reds[cnt].row) {

				/*if(moveBottom(blues, cnt)) return -1;
				if(moveBottom(reds, cnt)) return 1;*/
				
				boolean resultBlue = moveBottom(blues, cnt);
				boolean resultRed = moveBottom(reds, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
				
				
			}
			else {
				/*if(moveBottom(reds, cnt)) return 1;
				if(moveBottom(blues, cnt)) return -1;*/
				
				boolean resultRed = moveBottom(reds, cnt);
				boolean resultBlue = moveBottom(blues, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}
		}
		case 2:
		{	
			if(blues[cnt].col < reds[cnt].col) {
				
				/*if(moveLeft(blues, cnt)) return -1;
				if(moveLeft(reds, cnt)) return 1;*/
				
				boolean resultBlue = moveLeft(blues, cnt);
				boolean resultRed = moveLeft(reds, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}
			else {
				/*if(moveLeft(reds, cnt)) return 1;
				if(moveLeft(blues, cnt)) return -1;*/
				
				boolean resultRed = moveLeft(reds, cnt);
				boolean resultBlue = moveLeft(blues, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}
		}
		case 3:
		{
			if(blues[cnt].col > reds[cnt].col) {
				/*if(moveRight(blues, cnt)) return -1;
				if(moveRight(reds, cnt)) return 1;*/
				
				boolean resultBlue = moveRight(blues, cnt);
				boolean resultRed = moveRight(reds, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
				
			}
			else {
				/*if(moveRight(reds, cnt)) return 1;
				if(moveRight(blues, cnt)) return -1;*/
				
				boolean resultRed = moveRight(reds, cnt);
				boolean resultBlue = moveRight(blues, cnt);
				
				if(!resultBlue && resultRed) return 1;
				else if(resultBlue) return -1;
				else return 0;
			}
		}
		default:break;
		}
		return 0;
	}
	
	public static boolean check(int row, int col, int cnt) {
		
		if(map[cnt][row][col] == 'O') return true;
		return false;
	}
	
	public static boolean moveRight(Node[] nodes, int cnt) {
		Node node = nodes[cnt];
		
		cnt++;
		map[cnt][node.row][node.col] = '.';

		for(int col = node.col + 1; col < M; col++) {
			if(map[cnt][node.row][col] == '.') continue;
			
			nodes[cnt] = new Node(node.row, col - 1, node.type);
			break;
		}
		
		node = nodes[cnt];
		
		boolean check = check(node.row, node.col + 1, cnt);
		if(!check) map[cnt][node.row][node.col] = node.type;

		return check;
	}
	
	public static boolean moveLeft(Node[] nodes, int cnt) {
		Node node = nodes[cnt];
		
		cnt++;
		map[cnt][node.row][node.col] = '.';
		for(int col = node.col - 1; col >= 0; col--) {
			if(map[cnt][node.row][col] == '.') continue;
		
			nodes[cnt] = new Node(node.row, col + 1, node.type);
			break;
		}
		
		node = nodes[cnt];
		boolean check = check(node.row, node.col - 1, cnt);
		if(!check) map[cnt][node.row][node.col] = node.type;
		
		return check;
	}
	
	public static boolean moveBottom(Node[] nodes, int cnt) {
		Node node = nodes[cnt];
		
		cnt++;
		map[cnt][node.row][node.col] = '.';
		for(int row = node.row + 1; row < N; row++) {
			if(map[cnt][row][node.col] == '.') continue;
			
			nodes[cnt] = new Node(row - 1, node.col, node.type);
			break;
		}
		
		node = nodes[cnt];
		
		boolean check = check(node.row + 1, node.col, cnt);
		if(!check) map[cnt][node.row][node.col] = node.type;
		
		
		return check;
	}
	
	public static boolean moveTop(Node[] nodes, int cnt) {
		Node node = nodes[cnt];
		
		cnt++;
		
		map[cnt][node.row][node.col] = '.';
		for(int row = node.row - 1; row >= 0; row--) {
			if(map[cnt][row][node.col] == '.') continue;
			
			nodes[cnt] = new Node(row + 1, node.col, node.type);
			break;
		}
		
		node = nodes[cnt];
		
		boolean check = check(node.row - 1, node.col, cnt);
		if(!check) map[cnt][node.row][node.col] = node.type;
		
		return check;
	}
	
	public static class Node{
		int row;
		int col;
		char type;
		
		public Node(int row, int col, char type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", type=" + type + "]";
		}

		

	}
}