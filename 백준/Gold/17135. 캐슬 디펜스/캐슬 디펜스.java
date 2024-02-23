import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 문제
 * 성을 향해 몰려오는 적을 잡는 턴 방식 게임
 * N * M인 격자 판
 * 격자판은 1 * 1크기의 칸으로 나누어져 있고 각 칸에 포함된 적의 수는 최대 하나
 * 격자판 N 번행의 바로 아래(N + 1 행)의 모든 칸에는 성이 있다
 * 
 * 성을 적에서 지키기 위해 궁수 3명을 배치하려고 한다.
 * 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 
 * 각각의 턴마다 궁수는 적 하나를 공격할 수 있고 모든 궁수는 동시에 공격한다.
 * 궁수가 공격하는 적의 거리가 D이하인 적 중에서 가장 가까운 적
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
 * 같은 적이 여러 궁수에게 공격당할 수 있다.  공격받은 적은 게임에서 제외
 * 궁수의 공격이 끝나면 적이 이동
 * 적은 아래로 한칸 이동하며 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
 * 모든 적이 격자판에서 제외되면 게임이 끝난다. 
 * 
 * 격자판의 상태가 주어졌을 때 궁수의 공격으로 제거할 수 이쓴ㄴ 적의 최대 수 
 * 
 * 입력
 * 격자판 행의 수 N, 열의 수 M 궁수의 공격 거리 제한 D
 * 격자판의 상태
 * 
 * 출력
 * 제거할 수 있는 적의 최대 수
 * 
 * 풀이
 *  M개 중에 3개를 선택하는 경우의 수
 *  bfs 돌릴 마다 제거할 적 set에 넣고 level이 끝나면 삭제
 *  
 */
public class Main {
	
	static int N, M, D, enermy, answer = Integer.MIN_VALUE;
	static int[] dx = {-1, 0, 1}, dy = {0, -1, 0};
	static int[] array = new int[3];
	
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enermy++;
			}
		}
		
		// 성 위치 시키기 -> 조합
		f(0, 0);
		
		System.out.println(answer);
	}
	
	public static void bfs() {
		int[][] map2 = new int[N][M];
		for(int i = 0;i < N; i++) {
			map2[i] = Arrays.copyOf(map[i], M);
		}
		
		List<Node> list = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			list.add(new Node(N, array[i]));
		}

		int e = enermy;
		int removeEnermy = 0;
		while(e != 0) {
			Set<Node> set = new HashSet<>();
			for(int i = 0; i < 3; i++) {
				Node castle = list.get(i);
				Queue<Node> queue = new ArrayDeque<>();
				queue.add(castle);
				boolean[][] visited = new boolean[N][M];
				
				int size = 0;
				int level = 0;
				first: while(!queue.isEmpty()) {
					if(size == 0) {
						size = queue.size();
						level ++;
					}
					
					if(level > D) break;
					Node node = queue.poll();
					
					for(int j = 0;j < 3; j++) {
						int nr = node.row + dy[j];
						int nc = node.col + dx[j];
			
						if(nr < 0 || nr >= N) continue;
						if(nc < 0 || nc >= M) continue;
						if(visited[nr][nc]) continue;
						if(map2[nr][nc] == 1) {
							set.add(new Node(nr, nc));
							break first;
						}
						visited[nr][nc] = true;
						queue.add(new Node(nr, nc));	
					}
					size--;
				}
			}
			removeEnermy += set.size();
			for(Node node: set) {
				map2[node.row][node.col] = 0;
				e--;
			}
		
			for(int i = N - 1; i >= 0; i--) {
				for(int j = 0; j < M; j++) {
					if(map2[i][j] == 1) {
						map2[i][j] = 0;
						if( i != N - 1) {
							map2[i + 1][j] = 1;
						}
						else e--;
					}
				}
			}
		}
		
		answer = Math.max(answer, removeEnermy);
	}
	
	public static void f(int cnt, int idx) {
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = idx ;i < M; i++) {
			array[cnt] = i;
			f(cnt + 1, i + 1);
		}
	}
	
	public static class Node{
		int row, col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Node) {
				Node n = (Node)obj;
				if(this.row == n.row && this.col == n.col) return true;
			}
			return false;
		}
		
		 @Override
		    public int hashCode() {
	        return 31 * row + col * 17;
	    }
	}
}