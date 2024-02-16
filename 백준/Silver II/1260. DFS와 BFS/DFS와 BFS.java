import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 그래프
 * DFS 결과와 BFS 결과로 탐색한 결과를 출력하는 프로그램 작성
 * 단 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고
 * 방문할 수 있는 점이 없느 ㄴ경우 종료한다.
 * 정점 번호느 1부터 N까지
 * 
 * 입력
 * 정점의 개수 N 1000이하
 * 간선의 개수 M 10000이하
 * 탐색을 시작할 정점의 번호 V가 주어진다. 
 * 다음M 개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
 * 두 정점 사이에는 여러 개의 간선이 있을 수 있다. 
 * 간선은 양방향이다. 
 * 
 * 출력
 * DFS 수행한 결과
 * BFS 수행한 겨로가
 * V부터 방문된 점을 순서대로 출력
 * 
 * 풀이 
 * 리스트를 이용
 * visited 사용
 * DFS는 재귀
 * BFS는 queue
 * 답은 배열에 저장
 */
public class Main {
	
	static int N, M, V, dfsCNT;
	static boolean[] visited;
	static int[] dfsArray, bfsArray;
	static HashMap<Integer, PriorityQueue<Integer>> hashmap;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		hashmap = new HashMap<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(!hashmap.containsKey(start)) {
				PriorityQueue<Integer> queue = new PriorityQueue<>();
				queue.add(end);
				hashmap.put(start, queue);
			}
			else {
				hashmap.get(start).add(end);
			}
			
			if(!hashmap.containsKey(end)) {
				PriorityQueue<Integer> queue = new PriorityQueue<>();
				queue.add(start);
				hashmap.put(end, queue);
			}
			else {
				hashmap.get(end).add(start);
			}
		}
		
		// dfs
		visited = new boolean[N + 1];
		dfsArray = new int[N];
		dfsArray[0] = V;
		visited[V] = true;
		dfs();
		for(int i = 0 ; i < N; i++) {
			if(dfsArray[i] == 0) break;
			System.out.print(dfsArray[i] + " ");
		}
		System.out.println();
		
		//bfs
		visited = new boolean[N + 1];
		bfsArray = new int[N];

		Queue<Integer> q = new ArrayDeque<>();
		q.add(V);
		visited[V] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int num = q.poll();
			bfsArray[cnt] = num;
			
			if(!hashmap.containsKey(num)) continue;
			
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>(hashmap.get(num));
			while(!queue.isEmpty()) {
				int n = queue.poll();
				if(visited[n]) continue;
				q.add(n);
				visited[n] = true;
			}
			cnt++;
		}
		for(int i = 0 ; i < N; i++) {
			if(bfsArray[i] == 0) break;
			System.out.print(bfsArray[i] + " ");
		}
		System.out.println();
	}
	
	public static void dfs() {
		if(dfsCNT == N) return;
		
		if(!hashmap.containsKey(dfsArray[dfsCNT])) return;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(hashmap.get(dfsArray[dfsCNT]));
		while(!queue.isEmpty()) {
			int num = queue.poll();
			if(visited[num]) continue;
			visited[num] = true;
			dfsCNT++;
			dfsArray[dfsCNT] = num;
			dfs();
		}
	}
}