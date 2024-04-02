import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리: 215560
 * 시간: 1452
 */
/*
 * 문제
 * 1. 검정색 루피를 획득하면 도둑루피의 크기만큼 소지한 루피가 감소
 * 2. N * N 크기의 동굴 --> 제일 왼쪽 위 (0, 0)
 * 3. N - 1, N - 1까지 이동
 * 4. 링크는 잃는 금액을 최소로 하여동굴 건너편까지 이동, 상하좌우 인접한 곳으로 1칸씩 이동
 * 5. 링크가 잃을 수밖에 없는 최소 금액
 * 
 * 입력
 * 1. 여러개의 테스트 케이스
 * 2. 동굴의 크기를 나타내는 정수 N (125이하) -> N이 0이면 종료
 * 3. 각 도둑루피의 크기가 공백으로 주어짐 모든 정수는 0 이상 9이하 한자리 수
 * 
 * 출력
 * 각 케이스마다 한줄씩 걸쳐 정답을 형식에 맞처서 출력
 * 
 * 아이디어
 * bfs,dp
 * 
 * 풀이
 * 1. while문 실생
 * 2. N 입력 -> 0이면 break
 * 3. int[N][N] map 생성 후 입력 받음
 * 4. int[N][N] dp 생성
 * 5. class Node 생성
 * 6. queue 생성 후 넣기
 * 7. while -> bfs
 * 8. queue poll()
 * 9. for i 4이하 까지
 * 10. 경계값 처리
 * 11. 저장되어 있는 값이 추가로 들어 오는 값보다 작으면 continue
 */
public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static  String pre = "Problem ";
    static int INF = 1000000, N;
    static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st ;
	     StringBuilder sb = new StringBuilder();
	     
	     int cnt = 1;
	     while(true) {
	    	 st = new StringTokenizer(br.readLine());
	    	 N = Integer.parseInt(st.nextToken());
	    	 if(N == 0) break;
	    	 
	    	 map = new int[N][N];
	    	 dp = new int[N][N];
	    	 
	    	 for(int i = 0; i < N; i++) {
	    		 st = new StringTokenizer(br.readLine());
	    		 for(int j = 0; j < N; j++) {
	    			 map[i][j] = Integer.parseInt(st.nextToken());
	    			 dp[i][j] = INF;
	    		 }
	    	 }
	    	 

	    	bfs();
	    	 
	    
	    	 sb.append(pre).append(cnt).append(": ").append(dp[N-1][N-1]).append('\n');
	    	 cnt++;
	     }
	     System.out.println(sb);
	}
	
	public static void bfs() {
		 dp[0][0] = map[0][0];
    	 Queue<Node> queue = new ArrayDeque<>();
    	 queue.add(new Node(0, 0, dp[0][0]));
    	 
    	 while(!queue.isEmpty()) {
    		 Node node = queue.poll();
    		 
    		 for(int i = 0;i < 4; i++) {
    			 int nr = node.row + dy[i];
    			 int nc = node.col + dx[i];
    			 
    			 if(nr < 0 || nr >= N) continue;
    			 if(nc < 0 || nc >= N) continue;
    			 
    			 int np = node.price + map[nr][nc];
    			 if(dp[nr][nc] <= np) continue;
    			 dp[nr][nc] = np;
    			 queue.add(new Node(nr, nc, np));
    		 }
    	 }
	}
	
	public static class Node{
		int row;
		int col;
		int price;
		
		
		public Node(int row, int col, int price) {
			super();
			this.row = row;
			this.col = col;
			this.price = price;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", price=" + price + "]";
		}
	}

}