

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 아이디어 
 * dp
 * 
 */
public class Main {
	
	public static int N;
	public static int[][] red, green, blue, arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		red = new int[N][3];
		green = new int[N][3];
		blue = new int[N][3];
		
		arr = new int[N][9];
		for(int i = 0 ;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		
		init(red, 0);
		init(green, 1);
		init(blue, 2);
		
		if(N > 2) {
			set(red, 0);
			set(green, 1);
			set(blue, 2);
		}
		
		int answer = Integer.MAX_VALUE;
		answer = Math.min(answer,  f2(red));
		answer = Math.min(answer,  f2(blue));
		answer = Math.min(answer,  f2(green));
		
		System.out.println(answer);
	}
	
	public static void print(int[][] dp) {
		for(int i = 0; i < N; i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println();
	}
	
	public static int f2(int[][] dp) {
		int num = Integer.MAX_VALUE;
		
		for(int i = 0; i <3; i++) {
			if(dp[N -1][i] == -1) continue;
			num = Math.min(num,  dp[N -1][i]);
		}
		
		return num;
	}
	
	public static void f(int[][] dp) {
		
		for(int i = 0; i < 3; i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < 3; j++) {
				if(i == j) continue;
				if(dp[1][j] == -1) continue;
				min = Math.min(min,  dp[1][j]);
			}
			dp[2][i] = min + arr[2][i]; 
		}
	}
	
	public static void set(int[][] dp, int type) {
		
		f(dp);
		for(int i = 3; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + arr[i][2];
		}
		
		dp[N - 1][type] = -1;
	}
	
	public static void init(int[][] dp, int type) {
		
		// 첫번째 초기화
		for(int i = 0; i < 3; i++) {
			if(i == type) dp[0][type] = arr[0][type];
			else dp[0][i] = -1;
		}
		
		// 두번째 초기화
		for(int i = 0; i < 3; i++) {
			if(dp[0][i] == -1) continue;
			for(int j = 0; j < 3; j++) {
				if(i == j) dp[1][j] = -1;
				else dp[1][j] = dp[0][i] + arr[1][j];
			}
			break;
		}
	}

}
