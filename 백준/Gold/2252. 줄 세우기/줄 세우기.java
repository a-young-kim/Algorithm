import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * N명의 학생을 줄세우기
 * 두 학생의 키를 비교하는 방법 사용
 * 모든 학생이 아닌 일부 학생의 수
 * 이부 학생들의 키가 주어져씅ㄹ 떄 줄 세우는 프로그램
 * 
 * 입력
 * N, 32000이하, M 100000 이하
 * M은 키를 비교한 회수 
 * M개의 줄에는 키를 비교한 두 학생의 번호 A B
 * A개 학생 B의 앞에 서야한다. 
 * 학생 번호는 1번 부터 N번
 * 
 * 출력
 * 앞에서 부터 줄 세운 결과 출력
 * 답이 여러가지인 경우에는 아무거나 출력
 * 
 * 풀이
 * 학생의 위치 배열을 만들어서 
 * 학생들의 위치 차이를 +1로 표시
 */
public class Main {
	
	static int N, M;
	static boolean[] visited;
	static HashMap<Integer, List<Integer>> hashMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		hashMap = new HashMap<>();
		int[] node = new int[N + 1];
		for(int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(!hashMap.containsKey(start)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(end);
				hashMap.put(start,  list);
			}
			else {
				hashMap.get(start).add(end);
			}
			node[end]++;
		}
		
		
		Queue<Integer> queue = new ArrayDeque<>();
		visited = new boolean[N + 1];
		for(int i = 1; i < N + 1; i++) {
			if(node[i] == 0) {
				visited[i] = true;
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {

			int num = queue.poll();
			System.out.print(num + " ");
			if(!hashMap.containsKey(num)) continue;
			for(Integer value: hashMap.get(num)) {
				if(visited[value]) continue;
				node[value]--;
			}

			for(int i = 1; i < N + 1; i++) {
				if(visited[i]) continue;
				if(node[i] == 0) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}