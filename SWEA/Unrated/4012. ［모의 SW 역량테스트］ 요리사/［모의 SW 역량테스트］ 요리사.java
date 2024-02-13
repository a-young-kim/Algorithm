import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 두 명의 손님에게 음식을 제공, 최대한 비슷한 맛의 음식
 * N개의 식재료, 각각 N / 2개씩 나누어 두개의 요리
 * 각각의 음식을 만들기 위해서 A음식과 B음식의 맛의 차이가 최소가 되도록 재료 배분
 * 
 * 식재료 i와 j 같이 요리하면 시너지 발생
 * 
 * 두 음식 간의 맛의 차이가 최소가 되는 경우를 찾고 최소값 출력
 * 
 * 제약사항
 * 식재료 수 N 4이상 16이하 짝수
 * 시너지는 이만 이하 정수
 * 
 * 입력
 * 테스트 T
 * 식재료 수 N
 * 시너지 값
 * 
 * 출력
 * 두 음식의 차
 * 
 * 풀이
 * 조합
 * 순열
 * 재귀
 */
public class Solution {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[][] array;
	static int[] visitedArray;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case < T + 1; test_case++) {
			 answer = Integer.MAX_VALUE;
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 
			 array = new int[N][N];
			 visitedArray = new int[N];

			 for(int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j = 0; j < N; j++) {
					 array[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 f(0, 0);
			 System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static void f(int cnt, int idx) {
		
		if(cnt == N/2) {
			int A = 0;
			int B = 0;
			for(int i = 0; i < N; i++) {
				if(visitedArray[i] == 1) {
					for(int j = i + 1; j < N; j++) {
						if(visitedArray[j] == 1) {
							A += array[i][j];
							A += array[j][i];
						}
					}
				}
		
				else {
					for(int j = i + 1; j < N; j++) {
						if(visitedArray[j] == 0) {
							B += array[i][j];
							B += array[j][i];		
						}
					}
				}
			}
			answer = Math.min(answer, Math.abs(A - B));
		}
		
		for(int i = idx; i < N; i++) {
			visitedArray[i] = 1;
			f(cnt + 1, i + 1);
			visitedArray[i] = 0;
		}
	}

}