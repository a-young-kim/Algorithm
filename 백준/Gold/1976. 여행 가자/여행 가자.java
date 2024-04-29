
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 한국에는 도시가 N개
 * 2. 여행 일정이 주어졌을 때 가능한 여행 경로인지 확인
 * 3. 중간에 다른 도시를 경유 가능
 * 4. 도시들의 개수와 도시들 간의 연결 여부가 주어져 있고
 * 		동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때 가능한지 여부 판별
 * 5. 같은 도시 여러번 방문 가능
 * 
 * 입력
 * 1. 도시 수 N개 200 이하
 * 2. 여행 계획에 속한 도시의 수 M, 1000이하
 * 3. N개의 정수,i번쨰 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보 의미
 * 		1이면 연결, 0이면 연결안한
 * 
 * 아이디어
 * union-find
 */
public class Main {
	
	static int[] node;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		node = new int[N + 1];
		for(int i = 0; i < N + 1; i++) node[i] = i;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N + 1; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N + 1; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(i < j && num == 1) union(i, j);
			}
		}
		

		st = new StringTokenizer(br.readLine());
		int root = find(Integer.parseInt(st.nextToken()));
		int i = 1;
		for(; i < M; i++) {
			if(root != find(Integer.parseInt(st.nextToken()))) break;
		}
		
		if(i != M) System.out.println("NO");
		else System.out.println("YES");
	}
	
	public static int find(int num) {
		if(num == node[num]) return num;
		return node[num] = find(node[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) node[aRoot] = bRoot;
		else node[bRoot] = aRoot;
	}

}
