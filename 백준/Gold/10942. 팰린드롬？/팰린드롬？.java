import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 팰린드롬 놀이
 * 2. 자연수 N개를 적고 질문을 M번함
 * 3. 각 질문을 정수 S E로 나타낼 수 있으며 
 * 	S번째 수부터 E번째 수까지가 팰린드롬을 이루는지 물어보며
 * 	명우는 각 질문에 대해 팰린드롬이다 또는 아니다라고 말해야함
 * 4. 펠린드롬이란 본인에서 시작해서 다시 본인으로 돌아오는 수열
 * 
 * 입력
 * 1. 수열의 크기 N 2000이하
 * 2. 홍준이가 칠판의 적은 수 N 10만 보다 작거나 같은 자연수
 * 3. 홍준이가 한 질문의 개수 M 백만 이하
 * 4. 질문들
 * 
 * 출력
 * M개의 줄에 걸쳐 홍준이의 질문에 대한 명우의 답을 입력으로 주어진 순서에 따라 출력한다. 
 * 펠린드롬인 경우 1 아닌 경우 0
 * 
 * 아이디어
 * dp
 * 
 * 시간 복잡도 
 * 이천 * 이천  + 백만
 * 
 * 풀이
 * 1. N 입력
 * 2. int[] arr 입력
 * 3. int[][] dp 생성


 */
public class Main {
	
	static int N, S, E, M;
	static int[] arr;
	static int[][] dp;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		dp  = new int[N + 1][N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
			dp[i][i] = 1;
		}

		for(int start = N; start > 0; start --) {
			for(int end = start + 1; end < N + 1; end ++) {
				if(arr[start] != arr[end]) continue;
				if(end - start == 1) {
					dp[start][end] = 1;
					continue;
				}
				
				
				if(dp[start + 1][end - 1] == 0) continue;
				dp[start][end] = 1;
			}
		}
		
		//for(int i = 0; i < N + 1; i++)System.out.println(Arrays.toString(dp[i]));

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			sb.append(dp[S][E]).append('\n');
		}
		System.out.println(sb);

	}

}