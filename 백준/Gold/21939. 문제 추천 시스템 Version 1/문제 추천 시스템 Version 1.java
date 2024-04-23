import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 새로운 기능 추가
 * 2. 명렬어
 * 	2.1 recommend x 
 * 		2.1.1 추천 문제 리스트에서 가장 어려운 문제 번호 출력
 * 		2.1.2 가장 어려운 문제가 여러개라면 문제 번호가 큰것
 * 		2.1.3 x가 -1일 경우에는 가장 쉬운 문제 번호 출력
 * 		2.1.4 가장 쉬운 문제가 여러 개면 문제 번호가 작은 것을 출력
 * 	2.2 add P L
 * 		2.2.1 추천 문제 리스트에 난이도가 L인 문제번호 P를 추가
 * 		2.2.2 추천 문제 리스트가 없는 문제 번호 P만 입력
 * 	2.3 solved P
 * 		2.3.1 추천 문제리스트에서 문제 번호 P 제거
 * 		2.3.2 추천 문제 리스트에 있는 문제 번호만 입력으로 주어짐
 * 
 * 입력
 * 1. 문제의 개수 N
 * 2. 문제 번호 P와 난이도 L이 공밸그로
 * 3. 명령문 개수 M
 * 4. 명령문 입력
 * 
 * 출력
 * recommend 명령이 주어질 때마다 문제 번호를 한 줄씩 출력
 * 
 * 제한 
 * N, P 십만 이하
 * M만 히아
 * L 100이하
 * x는 +-1
 * 
 * 풀이
 * 1. int N 입력
 * 2. class Node 생성
 * 	2.1 int num, int level
 * 3. PQ 2개 생성 --> 아래 조건 반대도
 * 	3.1 난이도가 큰 순서대로 정력
 * 	3.2 번호가 큰 순서대로 정렬
 * 4. boolean[] state 생성
 * 5. 문제 입력
 *	5.1 PQ에 삽입
 *	5.2 들어온 num을 state에 true 표시
 * 6. M 임력
 * 7. add이면 PQ 삽입 state 변경
 * 8. solved 이면 state 변경
 * 9. recommend 이변
 * 	9.1 x= 1면 큰 PQ 접근, state 확인 출력
 * 	9.2 x = -1 이면 작은 PQ 접근 , state 이면인 출력
 */
public class Main {
	
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> queueLarge = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.level == o2.level) return o2.num - o1.num;
				return o2.level - o1.level;
			}
		});

		PriorityQueue<Node> queueSmall = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.level == o2.level) return o1.num - o2.num;
				return o1.level - o2.level;
			}
		});

		int[] state = new int[100001];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			Node node = new Node(p, l);
			queueLarge.add(node);
			queueSmall.add(node);
			state[p] = l;
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());

			String s = st.nextToken();
			int p, l;
			switch (s) {
			case "recommend":
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					getAnswer(queueLarge, state);
				}
				else {
					getAnswer(queueSmall, state);
				}
				break;
			case "solved":
				p = Integer.parseInt(st.nextToken());
				state[p] = 0;
				break;
			case "add":
				p= Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				
				Node node = new Node(p, l);
				queueLarge.add(node);
				queueSmall.add(node);
				state[p] = l;
				break;
			}
		}
		System.out.println(sb);
	}
	
	public static void getAnswer(PriorityQueue<Node> queue, int[] state) {
		
		while(!queue.isEmpty()) {
			Node node = queue.peek();
			
			if(state[node.num] != node.level) queue.poll();
			else {
				//System.out.println(node.num);
				sb.append(node.num).append("\n");
				break;
			}
		}
	}

	public static class Node{
		int num;
		int level;

		public Node(int num, int level) {
			super();
			this.num = num;
			this.level = level;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", level=" + level + "]";
		}

	}
}