import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr, dp;
	static int[] dr= {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	
	static class Node{
		int r, c, s;
		public Node(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        String pre = "Problem ";
        int t = 1;
        int INF = 1000000;
        
        while(true) {
        	N = Integer.parseInt(br.readLine());
        	if(N==0) break;
        	arr = new int[N][N];
        	dp = new int[N][N];
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			 arr[i][j] = Integer.parseInt(st.nextToken());
        			 dp[i][j] = INF;
        		}
        	}
        	
        	bfs();

        	sb.append(pre).append(t++).append(": ").append(dp[N-1][N-1]).append('\n');
        }
        
        System.out.println(sb);
	}

	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, arr[0][0]));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			int s = n.s;
			for(int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(!range(nr, nc) || dp[nr][nc] <= s + arr[nr][nc]) continue;
				dp[nr][nc] = s+arr[nr][nc];
				q.add(new Node(nr, nc, dp[nr][nc]));
			}
		}
	}

	private static boolean range(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}