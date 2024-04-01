import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 수열 A가 주어졌을 때 가장 긴 증가하는 부분 수열을 구하는 프로그램 작성
 * 
 * 입력
 * 1. 수열 A의 크리  백만 이하
 * 2. 각 수열의 값 백만 이하
 * 
 * 아이디어 
 * dp
 * 
 * 풀이
 * 1. map에 수열을 입력
 * 2. N 만큼 int[] dp 배열 생성
 * 3. dp[1] = map[0]으로 저장, startIdx = 1, endIdx
 * 4. 이진 탐색
 * 
 */
public class Main {
	
	static int N;
	static int[] dp;
	static int[] map;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N + 1];
		dp[1] = map[1];
		int startIdx = 1, endIdx = 1;
		for(int i = 2; i < N + 1; i++) {
			int idx = f(startIdx, endIdx, i);
			endIdx = Math.max(endIdx,  idx);
			dp[idx] = map[i];
		}
		System.out.println(endIdx);
	}
	
	public static int f(int startIdx, int endIdx, int myIdx) {
		
		int mid = 1;
		while(startIdx <= endIdx) {
			mid = (startIdx + endIdx) / 2;
			
			if(dp[mid] > map[myIdx]) endIdx = mid - 1;
			else if(dp[mid] < map[myIdx]) startIdx = mid + 1;
			else return mid;
		}
		
		if(dp[mid] < map[myIdx]) return mid + 1;
		
		return mid;
	}

}