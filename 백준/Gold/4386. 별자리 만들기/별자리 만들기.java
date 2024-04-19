import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. n개의 별을 이어서 별자리를 만든다. 
 * 2. 조건은 다음과 같다
 * 2.1 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태
 * 2.2 모든 별들은 별자리 위의 선을 통해 직간접적으로 이어져 있어야 한다. 
 * 3. 별자리를 만들 때 최소 비용
 * 
 * 입력
 * 1. 별의 개수 n
 * 2. 각 별의 x, y 좌표가 실수 형태로 주어지며 최대 소수접 둘째자리까지 주어짐
 * 3. 좌표응 1000이하의 양의 실수
 * 
 * 아이디어
 * 다익스트라 ? => 가장 짧은 경로부터 연결
 * 루프 없어야 함
 * 
 * 풀이
 * 1. N입력
 * 2. class Node 생성 int x, int y
 * 3. Node[N] 배열 생성 후 입력
 * 4. class Distance 생성 Node node1, Node node2, double distance
 * 5. PQ<Distance> 생성 distance 짧은 순으로 정렬
 * 6. for i 가 0부터 N까지
 * 7. for j가 i + 1부터 N까지 거리구해서 PQ 삽입
 * 8. int[] root 생성
 * 9. find() 함수 uninon()함수 생성
 * 10. PQ에서 하나씩 뽑아서 넣기 루프 생기면 넣지 않기
 * 11. 출력
 * 
 * 
 * 
 */
public class Main {
	
	static int N;
	static int[] root;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y =  Double.parseDouble(st.nextToken());
			
			nodes[i] = new Node(x, y);
		}
		
		PriorityQueue<Distance> distances = new PriorityQueue<>(new Comparator<Distance>() {
			@Override
			public int compare(Distance o1, Distance o2) {
				// TODO Auto-generated method stub
				return (int)((o1.distance - o2.distance) * 1000);
			}
		});
		
		for(int i = 0 ;i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				Node n1 = nodes[i];
				Node n2 = nodes[j];
				
				double x = n1.x - n2.x;
				double y = n1.y - n2.y;
				
				double distance = Math.pow(Math.pow(x, 2) +Math.pow(y,  2), 0.5);
				distances.add(new Distance(i,  j, distance));
			}
		}
		
		root = new int[N];
		for(int i = 0; i < N; i++) root[i] = i;
		
		double answer = 0;
		while(!distances.isEmpty()) {
			Distance d = distances.poll();
			
			boolean flag = union(d.node1, d.node2);
			if(flag) answer += d.distance;
		}
		System.out.println(answer);
	}
	
	public static int find(int num) {
		if(num == root[num]) return num;
		return root[num] = find(root[num]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		root[aRoot] = bRoot;
		return true;
	}
	
	public static class Node{
		double x, y;

		public Node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}
	
	
	public static class Distance{
		int node1, node2;
		double distance;
		
		public Distance(int node1, int node2, double distance) {
			super();
			this.node1 = node1;
			this.node2 = node2;
			this.distance = distance;
		}
		
		@Override
		public String toString() {
			return "Distance [node1=" + node1 + ", node2=" + node2 + ", distance=" + distance + "]";
		}

	}
}