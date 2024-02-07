import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

import javax.xml.transform.Templates;

/*
 * 문제
 * 냉장고 배달을 위해 N명의 고객을 방문
 * 고객의 위치는 이차원 정수 좌표(X, Y) 100이하
 * 두 위치의 거리는 절대값 x1 - x2 + 절대값 y1 - y2
 * 
 * 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려고 한다. 
 * 
 * 모두 방문하고 집으로 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하여라
 * 
 * 제약사항 
 * 고객의 수는 10이하
 * 총 좌표응 N + 1
 * 
 * 입력
 * 테스트케이스 수
 * 테스트 케이스가 하나씩 주어짐, 고객의 수N, 회사 집, 집의 좌표, N명의 고객의 좌표가 차례로 나열
 * 
 * 출력
 * 총 10줄에 10개의 테스트 케이스 각각 답 출력
 * 
 * 풀이 
 * 완탐
 * 
 */
public class Solution {
	
	static int N;
	static int[] house;
	static boolean[] visited;
	static int[][] array;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			answer = Integer.MAX_VALUE;
			N = sc.nextInt();
			int[] company = new int[2];
			house = new int[2];
			
			company[0] = sc.nextInt();
			company[1] = sc.nextInt();
			
			house[0] = sc.nextInt();
			house[1] = sc.nextInt();
			
			array = new int[N][2];
			for(int i = 0; i < N; i++) {
				array[i][0] = sc.nextInt();
				array[i][1] = sc.nextInt();
			}
			visited = new boolean[N];
			
			f(0, 0, company);
			System.out.println("#" + test_case + " " + answer);
		}
		sc.close();

	}
	
	public static void f(int idx, int sum, int[] location) {
		if(idx == N) {
			answer = Math.min(sum + cal(location, house), answer);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			f(idx + 1, sum + cal(location, array[i]), array[i]);
			visited[i] = false;
		}
	}
	
	public static int cal(int[] location1, int[] location2) {
		return Math.abs(location1[0] - location2[0]) + 
				Math.abs(location1[1] - location2[1]);
	}
}