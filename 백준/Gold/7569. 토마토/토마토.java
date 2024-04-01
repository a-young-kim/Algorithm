import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 토마토를 H * N * M 창고에 보관
 * 2. 보관 후 하루가 지나면 익은 토아토들의 인접한 곳에 있는 익지 않는 토마토들이 익는다 -> 상하좌우 앞뒤
 * 3. 창고에 보관된 토마토들이 며칠이 지나면 다 익는지 최소 일수 알고 싶음
 * 4.  격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 
 * 	며칠이 지나면 모두 익는지 최소 일수, 칸에 토마토가 없을 수도 있음
 * 
 * 입력
 *  1. 상자의 가로 칸 수 M, 상자의 세로 칸수 N, 쌓아올린 상자의 수 H 각각 100이하
 *  2. 가장 아래 상자부터 가장 위의 상자까지 저장된 토마토 정보
 *  3. 1은 익은 토마토, 0은 익지 않은 토마토 -1은 토마토가 들어 있지 않은 칸
 *  
 *  아이디어 
 *  bfs
 *  
 *  풀이
 *  1. M, N, H 입력
 *  2. int[H][N][M] 배열 생성
 *  3. dx, dy, dx 생성
 *  4. 배열에 입력 -> 입력 시 익지 않은 토마토 개수 cnt에 저장
 *  5. class Node 생성 row, col, level
 *  6. 익은 토마토를 Node 생성하여 queue에 넣기
 *  7. bfs 탐색
 *  	1. queue.poll() --> level 저장
 *  	2. for i가 6 전까지
 *  	3. 주변이 익지 않은 토마토이면 익은 토마토로 표시하고 queue에 넣기
 *  	4. answer --
 *  
 *  8. level 출력
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] dx = {0, 0, -1, 1, 0, 0};
		int[] dy = {1, -1, 0, 0, 0, 0};
		int[] dz = {0, 0, 0, 0, -1, 1};
		
		int[][][] map = new int[H][N][M];
		int level = 0, cnt = 0;
		Queue<Node> queue = new ArrayDeque<>();
		for(int z = 0; z < H; z++) {
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < M; x++) {
					map[z][y][x] = Integer.parseInt(st.nextToken());
					if(map[z][y][x] == 1) queue.add(new Node(x, y, z, 0));
					else if(map[z][y][x] == 0) cnt++;
				}
			}
		}
		
		first: while(!queue.isEmpty()) {
			Node node = queue.poll();
			level = node.level;
			
			for(int i = 0; i < 6; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				int nz = node.z + dz[i];
				
				if(nx < 0 || nx >= M) continue;
				if(ny < 0 || ny >= N) continue;
				if(nz < 0 || nz >= H) continue;
				if(map[nz][ny][nx] != 0) continue;
				
				map[nz][ny][nx] = 1;
				queue.add(new Node(nx ,ny, nz, node.level + 1));
				cnt--;
				if(cnt == 0) {
					level = node.level + 1;
					break first;
				}
			}
		}
		if(cnt == 0) System.out.println(level);
		else System.out.println(-1);

	}
	
	public static class Node{
		int x;
		int y;
		int z;
		int level;
		
		public Node(int x, int y, int z, int level) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.level = level;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", z=" + z + ", level=" + level + "]";
		}
		
	}

}