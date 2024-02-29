import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 1번부터 N번까지 번호가 매겨진 도시들 도시 사이의 길
 * 어느 한 도시에서 출발하여 N개의 도시의 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로
 * 한번 갔던 도시로는 다시 갈 수 없다. 
 * 가장 적은 비용을 들이는 여행 계획
 * 
 * 각 도시를 이동하는데 드는 비용 i j i에서 j로 가기 위한 비용
 * 비용은 대칭적이지 않음
 * 양의 정수 
 * 갈 수 없는 경우는 0
 * 
 * 입력
 * 첫째 줄 도시의 수 N 10이하
 * 다음 N개의 줄에 비용 행렬 
 * 성분은 백만 -->int
 * 갈 수 없는 경우 0
 * 항상 순회할 수 있는 경우만 입력으로 주어진다. 
 * 
 * 출력
 * 
 * 풀이
 * 순열 
 * visited 사용
 */
public class Main {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			visited[i] = true;
			f(1, 0, i, i);
			visited[i] = false;
		}
		System.out.println(answer);
	}
	
	public static void f(int cnt, int weight, int beforeNode, int startNode) {
		
		if(cnt == N) {
			int w = map[beforeNode][startNode];
			if(w == 0) return;
			answer = Math.min(answer,  w + weight);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			if(map[beforeNode][i] == 0) continue;
			visited[i] = true;
			f(cnt + 1, weight + map[beforeNode][i], i, startNode);
			visited[i] = false;
		}
	}
	
	
	public static class Node{
		int node;
		int weight;
		
		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

}