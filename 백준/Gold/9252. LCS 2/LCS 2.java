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
		
		//for(int i = 0; i < word1.length() + 1; i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[word1.length()][word2.length()]);
		
		int row = word1.length(), col = word2.length(), num = dp[word1.length()][word2.length()];
		Deque<Character> stack = new ArrayDeque<Character>();
		
		while(row > 0 && col > 0) {
			 if(stack.size() == num) break;
			 
			if(word1.charAt(row - 1) == word2.charAt(col - 1)) {
				stack.add(word1.charAt(row - 1));
				row = row - 1;
				col = col - 1;
				continue;
			}
			
			if(dp[row - 1][col] > dp[row][col - 1]) {
				row = row - 1;
			}
			else {
				col = col - 1;
			}
		}
		
		while(!stack.isEmpty())
			System.out.print(stack.pollLast());
		
	}

}