
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 삼각형시 나선으로 존재, 모든 삼각형은 정삼각형
 * 2. 나선에서 가장 긴 변의 길이를 K라 했을 때 그 변의 길이가 K인 정삼각형을 추가한다. 
 * 
 * 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12
 * 
 * 풀이 
 * dp?
 * p[n] = p[n - 1] + [ n - 5];
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int[] arr = new int[T];
		int max = 6;
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  arr[i]);
		}
		
		long[] dp = new long[max + 1];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		for(int i = 6; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
		//System.out.println(Arrays.toString(dp));
		for(int i = 0; i < arr.length; i++) {
			System.out.println(dp[arr[i]]);
		}
	}	

}
