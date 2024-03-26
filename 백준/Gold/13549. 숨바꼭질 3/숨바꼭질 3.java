import java.io.*;
import java.util.*;


/*
 * 문제
 * 1. 수빈이의 위치 N 0부터 십만 이하
 * 2. 동생의 위치 K 0부터 십만 이하
 * 3. 수빈이는 걷거나 순간이동 가능
 * 	- 걷을 경우 1초 후  X - 1혹은 X + 1
 * 	- 순간이동을 하는 경우 0초 후 2 * X
 * 4. 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
 * 
 * 입력
 * 수빈이 위치 N, 동생 위치 K
 * 
 * 출력
 * 가장 빠른 시간
 * 
 * 풀이
 * 1. bfs 탐색
 * 
 * 시간 복잡도 
 * 십만 이하
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dx = {-1, 1};
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, N));
		boolean[] visited = new boolean[1000000];
		visited[N] = true;
		
		int answer = 0;
		first: while(!queue.isEmpty()) {
			Node node = queue.poll();

			
			// 순간 이동 할 경우
			int jump = node.location * 2;
			
			if(!(jump < 0 || jump > 100000) && !visited[jump]) {
				visited[jump] = true;
				if(jump == K) {
					answer = node.level;
					break;
				}
				queue.add(new Node(node.level, jump));
			}
			
			// 걸을 경우
			for(int i = 0; i < 2; i++) {
				jump = node.location + dx[i];
				if(jump < 0 || jump > 100000) continue;
				if(!visited[jump]) {
					visited[jump] = true;
					if(jump == K) {
						answer = node.level + 1;
						break first;
					}
					queue.add(new Node(node.level + 1, jump));
				}
			}
		}
		System.out.println(answer);
	}
	
	public static class Node{
		int level;
		int location;
		
		public Node(int level, int location) {
			this.level = level;
			this.location = location;
		}

		@Override
		public String toString() {
			return "Node [level=" + level + ", location=" + location + "]";
		}
		
		
	}

}