import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제
 * 트리의 지름을 구하여라
 * 
 * 아이디어
 * dfs
 * 차수가 1인것 부터 확인, 더 들어 갈 수 없다면 return
 * 
 * 시간 복잡도 
 * N
 * 
 * 풀이 
 * 1. V 입력
 * 2. List<List<Node>> nodes 노드 배열 생성
 * 3. 간선 정보 입력
 * 4. bfs 탐색 시작
 * 5. answer 출력
 * 
 * bfs
 * 1. 입력 노드의 연결 노드 확인
 * 2. 노드의 개수만큼 for문 돌기
 * 3. bfs 재귀 들어감
 * 4. 탐색 시마다 weight 저장-> max 값
 * 
 */

import java.util.*;

public class Main {
	
	static int answer = 0, maxNode = 0;
	static List<List<Node>> nodes;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		
		nodes = new ArrayList<>();
		for(int i = 0; i < V + 1; i++) {
			nodes.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < V; i++) {
			st= new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			while(true) {
				int n1 = Integer.parseInt(st.nextToken());
				if(n1 == -1) break;
				
				int n2 = Integer.parseInt(st.nextToken());
				
				nodes.get(node).add(new Node(n1, n2));
			}
		}
		
		
		// 아무 노드나 dfs를 통해 가장 먼 node 선택
		visited = new boolean[V + 1];
		visited[1] = true;
		bfs(1, 0);
		
		visited = new boolean[V + 1];
		visited[maxNode] = true;
		bfs(maxNode, 0);
	
		
		System.out.println(answer);
	}
	
	public static void bfs(int node, int weight) {
		
		if(weight > answer) {
			answer = weight;
			maxNode = node;
		}
	
		List<Node> list = nodes.get(node);
		for(Node n: list) {
			if(visited[n.node]) continue;
			
			visited[n.node] = true;
			
			bfs(n.node, weight + n.weight);
		}
	}
	
	public static class Node{
		int node;
		int weight;
	
		public Node(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [node=" + node + ", weight=" + weight + "]";
		}
	}
}