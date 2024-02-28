import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 문제
 * 정육면체 주사위를 사용한다. 1부터 6까지 수가 하나씩 적혀있다.
 * 게임은 크기가 10 * 10이고, 총 100개의 칸으로 나누어져 있는 보드판
 * 보드판에는 1무터 100까지의 수가 하나씩 순서대로 적혀있다. 
 * 플레이어는 주사위를 굴려 나운 수만큼 이동해야 한다. 
 * 도착한 칸이 사다리면 사다리를 타고 위로 올라간다. 
 * 뱀이 있는 칸에 도착하면 뱀을 따라서 내려가게된다. 
 * 
 * 1번 칸에서 시작하여 100번 칸에 도착하는 것이다. 
 * 게임판의 상태가 주어졌을 때 100번 칸 에 도착하기 위해 주사위르 ㄹ굴려야 하는 횟수의 최솟갑
 * 
 * 입력
 * 게임판의 사다리 수 N,  뱀의 수 M
 * 둘째 줄 부터 사다리 정보를 의미하는 x, y가 주어진다. x번 칸에 도착하면 y번 칸으로 이동
 * 다음 M개의 줄에는 뱀의 정보를 의미하는 u,v가 주어진다
 * 
 * 출력
 * 100번 칸에 도착하기 위해 주사위를 최소 몇 번 굴려야 하는지 출력
 * 
 * 풀이
 * 최소는 최대 값이 주사위에 나오고 모든 
 */
public class Main {
	
	static HashMap<Integer, Integer> ladders = new HashMap<>();
	static HashMap<Integer, Integer> snakes = new HashMap<>();
	static int[] array = new int[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladders.put(a, b);
		}

		for(int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			snakes.put(a, b);
		}
		
		Arrays.fill(array,  Integer.MAX_VALUE);
		array[0] = 0;
		array[1] = 0;
		
		f(2, 101);
		System.out.println(array[100]);
	}
	
	public static void f(int start, int end) {
		for(int i = start; i < end; i ++) {
			
			for(int j = 6; j > 0; j--) {
				if(i - j < 0) continue;
				if(snakes.containsKey(i - j)) continue;
				array[i] = Math.min(array[i],  array[i - j] + 1);
			}

			if(snakes.containsKey(i) && array[snakes.get(i)] > array[i]) {
				array[snakes.get(i)] = array[i];
				f(snakes.get(i) + 1, i);
			}

			if(ladders.containsKey(i) && array[ladders.get(i)] > array[i]) {
				array[ladders.get(i)] = array[i];
			}
		}
	}

}