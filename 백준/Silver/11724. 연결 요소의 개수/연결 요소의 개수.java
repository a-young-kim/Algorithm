import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 방향성이 없는 그래프가 주어졌을 때 연결 요소의 개수를 구하라
 * 
 * 입력
 * 정점의 개수 N 간선의 개수 
 * 둘쨰줄 부터 양 끝점 u와 v가 주어진다. 
 * 
 * 출력
 * 첫째줄에 연결요소의 개수를 출력하라
 */
public class Main {
	
	static int[] array;
	static int N, M;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			array[i] = i;
		}
		
		for(int i = 0;i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		for(int i = 1; i < N + 1; i++) {
			find(i);
		}
		
		System.out.println(Arrays.stream(array).distinct().count() - 1);

	}
	
	public static int find(int num) {
		if(num == array[num]) return num;
		return array[num] = find(array[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) array[aRoot] = bRoot;
		else array[bRoot] = aRoot;
	}

}