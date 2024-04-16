/*
 * 문제
 * 1. 최대한 긴 과자를 나누어 주려고 한다. 
 * 2. 반드시 같은 길이의 막대 과자를 나누어야 한다. 
 * 3. M명의 조카, N개의 과자가 있을 때 막대기의 최대 길이를 구하라
 * 4. 막대 과자는 길이과 상관없이 여러 조각으로 나눠질 수 있으나 하나로 합칠 수는 없다. 
 * 
 * 입력
 * 1. M 백만 이하, N 백만 이하
 * 2. 과자 N의 길이
 * 3. 과자의 길이는 십억 이하
 * 
 * 출력
 * 막대 과자 길이를 출력
 * 모든 조카에게 같은 길이의 막대 과자르 나누어 줄 수 없다면 0을 출력
 * 
 * 아이디어
 * 이분 탐색
 * 
 * 시간 복잡도 
 * NlogN
 * 
 * 풀이
 * 1. int M, N 입력
 * 2. int[N] arr 입력
 * 3. int max, arr의 최대값 찾기
 * 4. int asnwer =   f(0, max) 
 * 5. answer 출력
 * 
 * int f(int start, int end)
 * 1. int mid = 0
 * 2. while(start <= end)
 * 3. mid = (start + end) / 2
 * 4. int result = getCookies(mid)
 * 5. if(result >= M) start = mid + 1;
 * 6. else end = mid - 1
 * 
 * int getCookies(int mid)
 * 1. int result = 0
 * 2. for i 가 0부터 N까지
 * 3. result += arr[i] / mid
 * 4. return result
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, answer;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int max = 0;
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  arr[i]);
		}
		
		answer = f(1, max);

		
		System.out.println(answer);
	}
	
	public static int f(int start, int end) {
		int mid = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			
			int count = getCount(mid);
			if(count >= M) start = mid + 1;
			else end = mid - 1;
		}
		
		if(getCount(mid) >= M) return mid;
		return mid - 1;
	}
	
	public static int getCount(int length) {
		
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			count += arr[i] / length;
		}
		return count;
	}
}