import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 각 회원은 다른 회원과 가까운 정도에 따라 점수를 받는다.
 * 2. 어느 회원이 다른 모든 회원과 친구이면 1점, 2점이면 친구의 친구
 * 3. 회장은 회원들 중 점수가 가장 작은 사람
 * 4. 회장의 점수와 회자이 될 수 있는 모든 사람을 찾는 프로그램
 * 
 * 입력
 * 1. 회원의 수(50 이하)
 * 2. 한 줄에 두 개의 회원 번호 -> 두 회원이 친구
 * 	- 회원번호는 1부터 회원의 수
 * 3. 마지막 줄에는 -1 이 두개
 * 
 * 출력
 * 첫째 줄: 회장 후보의 점수와 후보의 수를 출력
 * 두번째 줄에는 회장 부호를 오름차순으로 출력
 * 
 * 풀이
 * 1. bfs로 회장 마다의 숫자 확인 -> level 별
 * 2. 같은 경우가 있으면 오름차순 정렬
 * 
 * 시간 복잡도
 * 50 * 50  
 */
public class Main {

	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<List<Integer>> members = new ArrayList<>();
		for(int i = 0; i < N + 1; i++) {
			members.add(new ArrayList<>());
		}
		
		while(true) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1) break;
			members.get(a).add(b);
			members.get(b).add(a);			
		}
		
		int candidateNum = Integer.MAX_VALUE;
		List<Integer> candidate = new ArrayList<>();
		for(int i = 1; i < N + 1; i++) {

			boolean[] visited = new boolean[N + 1];
			Queue<Integer> queue = new ArrayDeque<>();
			queue.add(i);
			visited[i] = true;
			int size = queue.size(), level = 0;
			while(!queue.isEmpty()) {
				
				if(size == 0) {
					size = queue.size();
					level++;
				}
				
				int num = queue.poll();
				for(int next: members.get(num)) {
					if(visited[next]) continue;
					visited[next] = true;
					queue.add(next);
				}
				size--;
			}

			if(candidateNum > level) {
				candidateNum = level;
				candidate.clear();
			}
			
			if(candidateNum == level) candidate.add(i);
		}
		
		Collections.sort(candidate);
		System.out.println(candidateNum + " " + candidate.size());
		for(Integer num: candidate) {
			System.out.print(num + " ");
		}
		
	}

}