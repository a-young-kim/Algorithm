import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 마을은 행이 R개 열이 C개인 직사각형 모양
 * 빙하는 순차적으로 녹는대
 * 매일 일반 땅과 접촉한 빙하 공간을 ㄴㄱ인다
 * 접촉은 가로 세로
 * 두사람이 만나기 위해 며칠이 지나야 하는지 구하여라
 * 
 * 입력
 * 첫째줄 R과 C 1500이하
 * R개의 줄에 C의 문자열
 * .은 일반 땅
 * X는 빙하
 * L은 사람이 있는 공간
 * 
 * 출력
 * 첫째 줄에 두 사람이 만나기까지 며칠이 걸리는지 출력
 * 
 * 풀이
 * bfs -> 빙하 녹임
 * 사람 만나는 것 bfs
 */
public class Main {
	
	static int R, C;
	static int[] person = new int[2];
	static char[][] map;
	static boolean[][] visitedMelt ,visited;
	static Queue<Node> queue = new ArrayDeque<>();
	static Queue<Node> find = new ArrayDeque<>();
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s =  st.nextToken();
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'L' && find.size() == 0) {
					find.add(new Node(i, j));
				}
				
				if(map[i][j] != 'X') {
					queue.add(new Node(i, j));
				}
			}
		}

		int level = 0;
		int size = queue.size();
		visitedMelt = new boolean[R][C];
		visited = new boolean[R][C];
		
		while(!queue.isEmpty()) {
			if(size == 0) {
				level++;
				size = queue.size();
				if(isMeeting()) break;
			}
			
			Node n = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = n.row + dy[i];
				int nc = n.col + dx[i];
				
				if(nr < 0 || nr >= R) continue;
				if(nc < 0 || nc >= C) continue;
				if(visitedMelt[nr][nc]) continue;
				visitedMelt[nr][nc] = true;
				if(map[nr][nc] != 'X') continue;
				map[nr][nc] = '.';
				queue.add(new Node(nr, nc));
			}
			size --;
		}
		System.out.println(level);

	}
	
	public static boolean isMeeting() {

		visited[find.peek().row][find.peek().col] = true;
		Queue<Node> nextQueue = new ArrayDeque<>();

		while(!find.isEmpty()) {

			Node n = find.poll();
			
			int cnt = 0;
			
			for(int i = 0; i < 4; i++) {
				int nr = n.row + dy[i];
				int nc = n.col + dx[i];
				
				if(nr < 0 || nr >= R) continue;
				if(nc < 0 || nc >= C) continue;
				if(map[nr][nc] == 'X') continue;
				cnt++;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 'L') return true;
				visited[nr][nc] = true;
				find.add(new Node(nr, nc));
			}
			if(cnt != 4) nextQueue.add(n);
		}
		find = nextQueue;
		return false;
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