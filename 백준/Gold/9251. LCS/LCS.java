/*
 * 문제
 * 1. 최장 공통 부분 수열
 * 
 * 입력
 * 1. 문자열 1
 * 2. 문자열 2
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String word1 = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String word2 = st.nextToken();
		
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		
		for(int i = 1; i < word1.length() + 1; i++) {
			for(int j = 1; j < word2.length() + 1; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
				if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		//for(int i = 1; i < word1.length() + 1; i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[word1.length()][word2.length()]);
		
	}

}