import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 고슴도치는 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다. 
 * 숲은 R행 C열로 이루어져 있다. 
 * 비어있는 곳은 . 물ㅇ르 차있는 지역은 * 돌은 X로 표시되어 있다. 비버의 굴은 D로
 * 고슴도치의 위치는 S로 나타낼 수 있다. 
 * 
 * 고슴도치는 매 분마다 상하좌우 중 하나로 이동 가능
 * 물도 매분 비어있는 칸으로 확장한다.
 * 물이 있는 칸과 인접한 칸은 물이 차제 괴다. 
 * 고슴도치는 물로 이동 불가, 물도 비버의 소굴로 불가
 * 
 * 최소 시간을 구하시오
 * 
 * 입력
 * 50보다 작은 R C
 * D와 S는 하나씨만
 * 
 * 출력
 * 빠른 시간 혹은 KAKTUS 출력
 * 
 * 풀이
 * 물 먼저 차게 만든다. 
 * 그리고 비버 이동 
 * bfs
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];

		int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
		
		Queue<Node> water = new ArrayDeque<>();
		Queue<Node> gosem = new ArrayDeque<>();
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			for(int j = 0;j < C; j++) {
				map[i][j] = s.charAt(j);

				if(map[i][j] == 'S') {
					gosem.add(new Node(i, j, 0));
				}
				else if(map[i][j] == '*') {
					water.add(new Node(i, j, 0));
				}
			}
		}
		
		while(!gosem.isEmpty()) {
			int size = water.size();
			for(int i = 0; i < size; i++) {
				Node node = water.poll();
				for(int j = 0; j < 4; j++) {
					int nr = node.row + dy[j];
					int nc = node.col + dx[j];
					
					if(nr < 0 || nr >= R) continue;
					if(nc < 0 || nc >= C) continue;
					if(map[nr][nc] != '.') continue;
					map[nr][nc] = '*';
					water.add(new Node(nr, nc, node.level + 1));
				}
			}
			
			size = gosem.size();
			for(int i = 0; i < size; i++) {
				Node node = gosem.poll();
				for(int j = 0; j < 4; j++) {
					int nr = node.row + dy[j];
					int nc = node.col + dx[j];
					
					if(nr < 0 || nr >= R) continue;
					if(nc < 0 || nc >= C) continue;
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					if(map[nr][nc] == 'D') {
						System.out.println(node.level + 1);
						return;
					}
					if(map[nr][nc] != '.') continue;
					gosem.add(new Node(nr, nc, node.level + 1));
				}
			}
		}
		System.out.println("KAKTUS");
	}
	
	public static class Node{
		int row;
		int col;
		int level;
		
		public Node(int row, int col, int level) {
			this.row = row;
			this.col = col;
			this.level = level;
		}
	}

}