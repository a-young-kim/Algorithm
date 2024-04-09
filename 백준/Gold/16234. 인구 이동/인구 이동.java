/*
 * 문제
 * 1. N * N 크기의 map
 * 2. 각각의 딴에는 나라가 하나씩 존재, (r, c)있는 나라에는 A[r][c]명 살고 있음
 * 3. 국경선 존재 -> 정사각형
 * 4. 인구 이동은 다음과 같이 발생하며 더 이상 인구 이동이 없을 때까지 지속
 * 4.1 두 나라의 인구 차이가 L명 이상 R명 이하라면 국경선을 하루 동안 연다
 * 4.2 위의 조건에 따라 국경선이 모두 열렸다면 인구 이동 시작
 * 4.3 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으며, 그 나라는 오늘 하루 동안 연합이라고 한다. 
 * 4.4 연합을 이루고 있는 각 칸의 인구수는 (연합인구수)/연합을 이루고 있는 칸의 개수 -> 소수점을 버린다.
 * 4.5 연합을 해체하고 모든 국경선을 닫는다. 
 * 5. 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램 작성
 * 
 * 입력
 * 1. N(50 이하), L, R(100이하)
 * 2. 각 나라의 인구수 
 * 3. 인구 이동이 발생하는 일수가 2000번 보다 작거나 같다. 
 * 
 * 출력
 * 인구 이동이 며칠 동안 발생하는지 출력
 * 
 * 아이디어
 * 그룹을 정해서 visited 배열에 저장
 * 각 값이 같은 부분끼리 구한다. 
 * 
 * 풀이
 * 1. N, L, R 입력
 * 2. int[][] map 생성 후 입력 int[][] visited 생성
 * 3. bfs 탐색을 통해 그룹 분리
 * 4. 그룹 분리후 각 값을 더해서 map 수정
 */
import java.util.*;
import java.io.*;

public class Main {

	static int N, L, R;
	static int[][] map, group;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
	static Queue<Node> queue;
	static List<Node> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j = 0;j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		do {
			f();
			
			check();
			answer++;
			
		}
		while(list.size() != 1);

		System.out.println(answer - 1);
		
	}
	
	public static void check() {

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(group[i][j] == 0) continue;
				
				Node node = list.get(group[i][j]);
				map[i][j] = node.sum / (node.count);
			}
		}
	}
	
	public static void print(int[][] arr) {
		for(int i = 0; i < N; i++)System.out.println(Arrays.toString(arr[i]));
		System.out.println();
	}
	
	public static void f() {
		queue = new ArrayDeque<>();
		list = new ArrayList<>();
		list.add(null);
		group = new int[N][N];
		
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0;j < N; j++) {
				if(group[i][j] != 0) continue;
				
				group[i][j] = cnt;
				queue.add(new Node(i, j, -1, -1));
				
				int count = map[i][j], num = 1;
				while(!queue.isEmpty()) {
					Node node = queue.poll();

					int w1 = map[node.row][node.col];
					
					for(int k = 0; k < 4; k++) {
						int nr = node.row + dy[k];
						int nc = node.col + dx[k];
						
						if(nr < 0 || nr >= N) continue;
						if(nc < 0 || nc >= N) continue;
						if(group[nr][nc] != 0) continue;
						
						int w2 = map[nr][nc];
						
						if(Math.abs(w1 - w2) > R) continue;
						if(Math.abs(w1 - w2) < L) continue;
						
						group[nr][nc] = cnt;
						count += w2;
						num++;
						
						queue.add(new Node(nr, nc, -1, -1));
					}
				}
				
				if(num == 1) {
					group[i][j] = 0;
				}
				
				else{
					cnt++;
					list.add(new Node(-1, -1, count, num));
				}
				
			}
		}
	}
	
	public static class Node{
		int row;
		int col;
		int sum;
		int count;
		
		
		public Node(int row, int col, int sum, int count) {
			super();
			this.row = row;
			this.col = col;
			this.sum = sum;
			this.count = count;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", sum=" + sum + ", count=" + count + "]";
		}
		
	}

}