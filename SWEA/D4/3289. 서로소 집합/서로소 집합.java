import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 
 * N개의 집합 같은 집합에 포함되어 있는지 확인
 * 
 * 입력 테스트 수 T
 * n 백만 이하, m 십만 히아
 * m은 입력으로 주어진 연산의 개수
 * 합집합은 0, a, b의 형태로 입력
 * 포함되어 있는지 확인하는 연산음 1 a b 형태
 * a와 b는 n 이하의 자연수이며 같을 수도 있다. 
 */
public class Solution {
	
	static int n, m;
	static int [] array;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			array = new int[n + 1];
			for(int i = 1; i < n + 1; i++) {
				array[i] = i;
			}
			
			for(int i = 0; i < m; i++) {
				
				st = new StringTokenizer(br.readLine());
				
				int cal = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cal == 0) union(a, b);
				else {
					if(find(a) == find(b)) System.out.print(1);
					else System.out.print(0);
				}
				
			}
			System.out.println();
		}
		
	}
	
	public static int find(int num) {
		if(array[num] == num) return num;
		else array[num] = find(array[num]);
		return array[num];
	}
	
	public static void union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) {
			array[aRoot] = bRoot;
		}
		else {
			array[bRoot] = aRoot;
		}
	}

}