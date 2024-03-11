import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. N과 M이 주어짐
 * 2. 아래 조건을 만족하는 길이가 M이 수열을 모두 구하는 프로그램
 * 	- 1부터 N개의 자연수 중 M개를 고른 수열
 * 	- 같은 수를 여러 번 골라도 가능
 * 	- 고른 수열은 비 내림차순
 * 	- 길이가 K인 수열이 오름차순을 만족하면 비 내림차순
 * 
 * 입력
 * 첫째줄에 자연수 N과 M
 * 
 * 출력
 * 한줄에 하나의 문제의 조건을 만족하는 수열을 출력
 * 중복 수열을 여러번 출력 안됨
 * 각 수열은 공백으로 수분
 * 수열은 사전 순으로 증가
 * 
 * 풀이
 * 조합
 */
public class Main {
	
	static int N, M;
	static int[] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[M];
		f(1, 0);
	}
	
	public static void f(int idx, int cnt) {
		
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(array[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = idx; i < N + 1; i++) {
			array[cnt] = i;
			f(i, cnt + 1);
		}
	}

}