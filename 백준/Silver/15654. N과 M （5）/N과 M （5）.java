import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] array, answer, visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N];
		visited = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		answer = new int[M];
		Arrays.sort(array);
		f(0);
		
	}
	public static void f(int cnt) {

			if(cnt == M) {
				for(int i = 0; i < M; i++) {
					System.out.print(answer[i] + " ");
				}
				System.out.println();
				return;
			}
			for(int i = 0; i < N; i++) {
				if(visited[i] != 0) continue;
				visited[i] = 1;
				answer[cnt] = array[i];
				f(cnt + 1);
				visited[i] = 0;
			}
		}

}