import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * N명의 참가자, 0부터 N - 1번으로 번호가 매겨져 있음
 * 일부 사람들을 친구
 * 
 * 입력
 * 사람 수 N 2000이하
 * 친구 관계 수 M 2000이하
 * 두번쨰 줄 부터 a, b 둘 사이는 친구
 * 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.
 * 
 * 
 * 출력
 * 관계가 a - b - c - d -e인 관계가 주어지면 1 없으면 0
 * 
 * 풀이
 * 조합 구하기
 * dfs
 * a - b - c - d - e 관계를 만족 시키는 지 확인
 */
public class Main {
	
	static int N, M, answer;
	static boolean[] visited;
	static HashMap<Integer, List<Integer>> hashMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];
		hashMap = new HashMap<>();
		for(int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			
			if(!hashMap.containsKey(a)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(b);
				hashMap.put(a,  list);
			}
			else {
				hashMap.get(a).add(b);
			}
			
			if(!hashMap.containsKey(b)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(a);
				hashMap.put(b,  list);
			}
			else {
				hashMap.get(b).add(a);
			}
			
		}
		
		for(int i = 1; i < N + 1; i++) {
			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;
		}
		
		System.out.println(answer);
	}
	
	public static void dfs(int before, int cnt) {
		if(cnt == 5) answer = 1;
		if(answer == 1) return;
		
		if(!hashMap.containsKey(before)) return;
		for(int key: hashMap.get(before)) {
			if(visited[key]) continue;
			visited[key] = true;
			dfs(key, cnt + 1);
			visited[key] = false;
		}
	}

}