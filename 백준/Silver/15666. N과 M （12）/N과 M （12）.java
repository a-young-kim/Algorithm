import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static Integer[] array, answer;
	static Set<String> set = new HashSet<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = new Integer[M];
		visited = new boolean[N];
		Arrays.sort(array);

		f(0, 0);
		
	}
	public static void f(int cnt, int idx) {

			if(cnt == M) {
				String s = "";
				for(int i = 0; i < M; i++) {
					s += answer[i] + " ";
				}
				if(set.contains(s)) return;
				set.add(s);
				for(int i = 0; i < M; i++) {
					System.out.print(answer[i] + " ");
				}
				System.out.println();
				return;
			}
			for(int i = idx; i < N; i++) {
				answer[cnt] = array[i];
				f(cnt + 1, i);
			}
	}
	
}