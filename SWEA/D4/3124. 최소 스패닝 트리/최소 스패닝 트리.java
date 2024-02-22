import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 
 * 최소 스패닝 트리는 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중 가중치의 합이 최소인 트리
 * 입력으로 주어지는 그래프는 하나의 연결 그래프임이 보장
 * 
 * 임렵
 * 전체 테스트 개수 T
 * 정점의 개수 V 십만 간선의 개수 E 이십만 이하
 * 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 ABC가 주어진다.
 * 이는 A번 정점과 B번 정점이 가중치 C이 ㄴ간선으로 연결되어 있는 의미이니다.
 * C는 음수일 수도 있으며 절대값이 백만이 넘지 않는다. 
 * 
 * 출력
 * 각테스트케이스수 최소스패닝트리의가중치
 * 
 * 힌트
 * 크루스칼알고리즘
 */
public class Solution {
	
	static int V, E;
	static int[] array;
	static PriorityQueue<Node> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			 st = new StringTokenizer(br.readLine());
			 V = Integer.parseInt(st.nextToken());
			 E = Integer.parseInt(st.nextToken());
			 
			 queue = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.weight - o2.weight;
				}
			});
			 
			 
			 for(int i = 0; i < E; i++) {
				 st = new StringTokenizer(br.readLine());
				 int a = Integer.parseInt(st.nextToken());
				 int b = Integer.parseInt(st.nextToken());
				 int c = Integer.parseInt(st.nextToken());
				 
				 queue.add(new Node(a, b, c));
				 queue.add(new Node(b, a, c));
			 }
			 
			 array = new int[V + 1];
			 for(int i = 1; i < V + 1; i++) {
				 array[i] = i;
			 }
			 long answer = 0;
			 int cnt = 0;
			 while(!queue.isEmpty()) {
				 Node node = queue.poll();
				
				 if(find(node.startNode) == find(node.endNode)) continue;
				 union(node.startNode, node.endNode);
				 answer += node.weight;
				 cnt++;
				 if(cnt == V - 1) break;
			 }
			 System.out.println("#" + test_case + " " +answer);
		}
	}
	
	public static int find(int num) {
		if(array[num] == num) return num;
		return array[num] = find(array[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(array[a]);
		int bRoot = find(array[b]);
		
		if(aRoot > bRoot) array[aRoot] = bRoot;
		else array[bRoot] = aRoot;
	}
	

	public static class Node{
		int startNode, endNode, weight;
		
		public Node(int startNode, int endNode, int weight) {
			this.startNode = startNode;
			this.endNode = endNode;
			this.weight = weight;
		}
	}
}