import java.io.*;
import java.util.*;

/*
 * 문제
 * 1. N개의 가방 -> 1이상 100이하
 * 2. 최대한 가치 있게 물건을 선택
 * 3. 물건을 무게 W(십만이하), 가치 V(천이하)
 * 4. 최대 K 만큼의 무게만을 넣을 수 있는 가방
 * 5. 가방에 넣을 수 있는 물거읜 최대값 
 * 
 * 입력
 * 물품의 수 N, 무게 K
 * 각 물건의 무게 W, 가치 V
 * 
 * 출력
 * 가치의 최대값
 * 
 * 풀이
 * DP
 * 1. HashMap 각 인덱스(무게)에 맞는 V 저장 -> V가 최대값으로 정렬
 * 2. 각 index의 id를 지정하여 visited 이차원 배열을 만든다. 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		
		List<Node> list = new ArrayList<>();
		list.add(new Node(-1, -1));
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list.add(new Node(w, v));
		}
		
		int answer = 0;
		for(int i = 1; i < N + 1; i++) {
			Node node = list.get(i);

			for(int j = 1; j < K + 1; j++) {
				
				if(j - node.w < 0) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - node.w] + node.v);
			}
			answer = Math.max(answer,  dp[i][K]);
		}
		System.out.println(answer);
	}
	
	public static class Node{
		int w;
		int v;
		
		public Node(int w, int v) {
			this.w = w;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Node [w=" + w + ", v=" + v + "]";
		}
		
		
	}
	

}