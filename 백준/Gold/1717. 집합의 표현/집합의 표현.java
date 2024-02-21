import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 초기 n +1 집합이 있을 때
 * 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산
 * 
 * 입력
 * 첫째 줄 n, m
 * m은 입력으로 주어지는 연산의 개수
 * n은  백만 이하 m은 십만 이하
 * 합집합은 0, a, b a가 포함되어 있는 집합과 b가 포함되어 있느 집합 합친다
 * 두원소가 같은 집합에 포함되어 있는지 확인하는 연산는 1, a, b
 * a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산
 * 
 *출력
 *1로 시작하는 입력에 대해서 같은 집합에 포함되어 있느면 yes
 *아니면 no
 *
 */
public class Main {
	
	static int N, M;
	static int[] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N + 1];
		for(int i = 0;i < N + 1; i++) {
			array[i] = i;
		}
		
		for(int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(flag == 0) {
				union(a, b);
			}
			else {
			    if(find(a) == find(b)) System.out.println("yes");
				else System.out.println("no");
			}
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