import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 원숭이가 말이 되기를 원했다. 
 * 말을 움직임을 유심히 살펴보고 그대로 따라가히고 했다. 
 * 말은 체스의 나이트와 같은 이동 방향을 갖는다. 또한 장애물을 뛰어 넘을 수 있다. 
 * 
 * 하지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고 
 * 그외에는 그냥 인접한 칸으로만 움직을 수 있다. 대각선은 갈 수 없다. 
 * 
 * 격자판 맨 위에서 시작해서 맨 오른쪽 아래까지 가야한다. 
 * 인접한 네 방향으로 한 번 움직이는 것 말의 움직임으로 한 번 움직이는 것
 * 모두 한번의 동작으로 친다. 
 * 격자판이 주어졌을 때 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 
 * 방법을 알아내는 프로그램을 작성하시오
 * 
 * 입력
 * 첫째줄에 정수 K가 주어진다. 
 * 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 
 * H줄에 걸쳐 W개의 숫자가 주어지는데 
 * 0은 아무것도 없는 평지, 1은 장애물을 뜻한다. 장애물이 있는 곳으로는 이동할 수 없다. 
 * 시작점과 도착점은 항상 평지이다. 
 * W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다. 
 * 
 * 출력
 * 원숭이의 동작수의 최솟값 출력
 * 시작점에서 도착점까지 갈 수 없을 경우 -1
 * 
 * 풀이
 * queue 넣어서 풀기
 * queue 말처럼 행동한 값을 넣기
 */
public class Main {
	
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {0, 0, -1, 1, -1, 1, 2, 2, 1, -1, -2, -2}, 
				dy = {1, -1, 0, 0, -2, -2, -1, 1, 2, 2, 1, -1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[K + 1][H][W];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 0, 0));
		
		if(H - 1 == 0 && W - 1 == 0) {
			System.out.println(0);
			return;
		}
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			
			for(int i = 0; i < dy.length; i++) {
				int nr = n.row + dy[i];
				int nc = n.col + dx[i];
				
				if(nr < 0 || nr >= H ) continue;
				if(nc < 0 || nc >= W) continue;
				
				if(n.jump == K && i >= 4) break;
				if(map[nr][nc] == 1) continue;
				
				if(nr == H - 1 && nc == W - 1) {
					System.out.println(n.level + 1);
					return;
				}

				if(i < 4) {
					if(visited[n.jump][nr][nc]) continue;
					visited[n.jump][nr][nc] = true;
					queue.add(new Node(nr, nc, n.jump, n.level + 1));
				}
				else {
					if(visited[n.jump + 1][nr][nc]) continue;
					visited[n.jump + 1][nr][nc] = true;
					queue.add(new Node(nr, nc, n.jump + 1, n.level + 1));
				}
				
			}
		}
		System.out.println(-1);

	}
	
	public static class Node{
		int row;
		int col;
		int level;
		int jump;
		
		public Node(int row, int col, int jump, int level) {
			this.row = row;
			this.col = col;
			this.jump = jump;
			this.level = level;
		}
	}

}