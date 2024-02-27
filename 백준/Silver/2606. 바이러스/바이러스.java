import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 한 사람의 SNS를 통해서 소문이 게시되면 그 사람과 연결된 모든 사람들이 
 * 이 소문을 알게 된다. 
 * 
 * 모든 연결 관계는 양방향 상태이다. 
 * 1번 사람이 소문을 SNS에 올렸을 때 소문을 알게되는 사람들의 수를 출력
 * 
 * 입력
 * 사람 수 N 100이하 정수
 * SNS 상에서 직접 연결되어 있는 사람 쌍의 수
 * 그 수만큼 한 줄에 한 쌍씩 SNS 상에서 직접 연결되어 있는 사말들의 번호 쌍
 * 
 * 출력
 * 1번 사람이 소문을 올렸을 때 소문을 알게되는 모든 사람의 수
 * 
 * 풀이
 * visited 사용하고 
 * bfs
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		List<List<Integer>> sns = new ArrayList<>();
		for(int i = 0; i < N + 1; i++) {
			List<Integer> list = new ArrayList<>();
			sns.add(list);
		}
		
		for(int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향
			sns.get(a).add(b);
			sns.get(b).add(a);
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		queue.add(1);
		int answer = 0;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int nextNode: sns.get(node)) {
				if(visited[nextNode]) continue;
				visited[nextNode] = true;
				queue.add(nextNode);
			}
			answer++;
		}
		
		// 본인 제외
		System.out.println(answer - 1);

	}

}