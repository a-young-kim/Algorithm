import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 상자를 최대한 높이 쌓는다.
 * 2. 높이 쌓는 규칙은 다음과 같다. 
 * 2.1 상자를 회전 시키면 안된다 -> 옆면을 밑면으로 사용할 수 없다. 
 * 2.2 상자의 크기가 제각각이다. 밑면의 넓이가 같은 상자는 없으며 상자의 무게 또한 다르다. 
 * 2.3 상자의 높이는 같을 수 있다. 
 * 2.4 밑면이 좁은 상자 위에 밑면이 더 넓은 상자를 놓을 수 없다. 
 * 2.5 상자의 무게가 무거운 상자는 가벼운 상자 위에 쌓을 수 없다. 
 * 
 * 입력
 * 1. 상자의 수 N 100이하
 * 2. 상자의 정보, 밑면의 넓이, 상자의 높이, 상제의 무게 순으로 양의 정수
 * 3. 상자는 입력 순을 1번부터 연속적인 번호 
 * 4. 상자의 넓이 무게는 만 이하
 * 
 * 출력
 * 1. 사용된 상자의 수
 * 2. 가장 위에 있는 상자부터 가장 아래 있는 상자까지 차례대로 한 줄에 하나씩 상자의 번호를 빈칸 없이 출력
 * 
 * 풀이
 * 1. N 입력
 * 2. class Node 생성
 * 2.1 num, width, height, weight
 * 3. PQ 2개 생성
 * 3.1 하나는 넓이 무게 순으로 정렬
 * 3.2 다른 하나는 무게 넓이 순으로 정렬
 * 4. List<Integer> answer1, answer 2 생성
 * 5. 각각 PQ를 돌면서 이전 값이후에 이후 값이 올수 있는지 정결정
 * 6. answer1, answer2 중에 더 큰 것 출력
 */
public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> queue1 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.weight == o2.weight) return o2.width - o1.width;
				return o2.weight - o1.weight;
			}
		});
		
		PriorityQueue<Node> queue2 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.width == o2.width) return o2.weight - o1.weight;
				return o2.width - o1.width;
			}
		});
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			queue1.add(new Node(i, width, height, weight));
			queue2.add(new Node(i, width, height, weight));
		}

		List<Integer> answer1 = new ArrayList<Integer>();
		List<Node> list1 = new ArrayList<Node>(); 
		
		while(!queue1.isEmpty()) {
			list1.add(queue1.poll());
		}
		
		
		int max1 = f(list1, answer1);
		
		List<Integer> answer2 = new ArrayList<Integer>();
		List<Node> list2 = new ArrayList<Node>(); 
		while(!queue2.isEmpty()) {
			list2.add(queue2.poll());
		}
		
		int max2 = f(list2, answer2);
		
		List<Integer> answer ;
		if(max1 > max2) {
			answer = answer1;
		}
		else {
			answer = answer2;
		}

		System.out.println(answer.size());
		for(int num : answer) System.out.println(num);
	}
	
	public static int f(List<Node> list, List<Integer> answer) {
		
		int[][] dp = new int[2][N];
		for(int i = 0; i < N; i++) dp[0][i] = i;
		dp[0][0] = 0;
		dp[1][0] = list.get(0).height;
		
		int max = 0, maxIdx = 0;
		for(int i = 1; i < N; i++) {
			Node node = list.get(i);
			int j = i - 1;
			for(; j >= 0; j--) {
				
				if(list.get(j).width > node.width 
						&& list.get(j).weight > node.weight) {

					if(dp[1][i] < dp[1][j] + node.height) {
						dp[0][i] = j;
						dp[1][i] = dp[1][j] + node.height;
					}
					
				}
			}
			
			if(dp[1][i] < node.height) {
				dp[0][i] = i;
				dp[1][i] = node.height;
			}
			
			if(max < dp[1][i]) {
				maxIdx = i;
				max = dp[1][i];
			}
		}
		
		boolean[] visited = new boolean[N];
		
		int idx = maxIdx;
		
		while(true) {
			
			if(visited[idx]) continue;
			visited[idx] = true;
			
			answer.add(list.get(idx).num);
			if(idx == dp[0][idx]) break;
			idx = dp[0][idx];
		}
		/*System.out.println(list);
		System.out.println(Arrays.toString(dp[0]));
		System.out.println(Arrays.toString(dp[1]));
		System.out.println(answer);
		System.out.println();*/
		
		return max;
	}
	
	public static class Node{
		int num;
		int width;
		int height;
		int weight;
		
		public Node(int num, int width, int height, int weight) {
			super();
			this.num = num;
			this.width = width;
			this.height = height;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", width=" + width + ", height=" + height + ", weight=" + weight + "]\n";
		}
		
	}

}