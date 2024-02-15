import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.InputMap;

/*
 * 문제
 * 최적의 BC를 선택하는 알고리즘을 개발하고자 한다.
 * 10 * 10 영역이 주어졌을 떄
 * BC의 충전 범위가 C일 때 BC의 거리가 C이하이면 BC에 접속할 수 있다. 거리를 차의 절대값의 합이다.
 * 겹치는 지점은 두 BC 중 하나를 선택하여 접속할 수 있다.
 * 
 * 사용자 A와 B의 이동 궤적이 있다고 가정할 때 T는 초를 의미한다. 매 초마다 특정 BC의 충전 범위 안에 들어오면 해당 BC에 접근 가능하다.
 * 이떄 접속한 BC의 범위 만큼 베터리를 충전할 수 있다. 만약 한 BC에 두 명의 사용자가 접속한 경우 접속한 사용자의 수 만큼 충전 양을 균등하게 분배한다. 
 * 모든 사용자가 추엊한 양의 합의 최댓값을 구하는 프로그램을 작성하라
 * 
 * 제약사항
 * 가로 세로 크기는 10
 * 사용자는 총 2명이고 A는 1,1 B는 10 10지점에서 출발
 * 이동 시간은 20 이상 100이하의 정수
 * BC의 개수 A는 8 이하의 자연수
 * BC 충전 범위 C는 1이상 4이하의 정수
 * BC의 성능 P는 10이상 500이하의 짝수
 * 초기 위치 부터 충전할 수 있다.
 * 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 AB가 동시에 같은 위치로는 이동 할 수 있다. 
 * 
 * 입력
 * 총 테스트 케이스T
 * 총 이동 시간 M , BC의 개수 A
 * A의 이동 정보
 * B의 이동 정보
 * 0 이동하지 않음 1상 2우 3하 4좌
 * 
 * BC의 정보 좌표 XY 충전범위 C 처리량P
 * 
 * 풀이방식
 * AB의 이동을 좌표로 표시
 * 충전 가능 위치를 표시 -> 이차원 배열
 * 리스트를 이용하여 각 배열에 얼만큼 크기의 BC를 사용할 수 있는지 확인 --> 내림차순으로 정렬
 * 같은 위치 A와 B가 있을 경우 시간 순으로 정렬
 */
public class Solution {
	
	static int M, A;
	static int[] dx = {0, 0, 1, 0, -1}, dy = {0, -1, 0, 1, 0};
	static int[][] arrayA, arrayB, arrayAC;
	static int[][][][] AC;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			arrayA = new int[M + 1][2];
			arrayA[0][0] = 1;
			arrayA[0][1] = 1;
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < M + 1; i++) {
				int n = Integer.parseInt(st.nextToken());
				arrayA[i][0] = arrayA[i - 1][0] + dy[n];
				arrayA[i][1] = arrayA[i - 1][1] + dx[n];
			}
			
			arrayB = new int[M + 1][2];
			arrayB[0][0] = 10;
			arrayB[0][1] = 10;
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < M + 1; i++) {
				int n = Integer.parseInt(st.nextToken());
				arrayB[i][0] = arrayB[i - 1][0] + dy[n];
				arrayB[i][1] = arrayB[i - 1][1] + dx[n];
			}
			
			arrayAC = new int[11][11];
			AC = new int[11][11][2][2];
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				visited = new boolean[11][11];
				visited[row][col] = true;
				f(row, col, c, p, row, col, i + 1);
			}
			
			// 이동
			int answerA = 0;
			int answerB = 0;
			visited = new boolean[11][11];
			for(int i = 0;i < M + 1; i++) {
				int rowA = arrayA[i][0];
				int colA = arrayA[i][1];
				
				int rowB = arrayB[i][0];
				int colB = arrayB[i][1];
				
				
				if(AC[rowA][colA][1][1] !=  AC[rowB][colB][1][1]) {
					answerA += AC[rowA][colA][1][0];
					answerB += AC[rowB][colB][1][0];
				}
				else {
					if(AC[rowA][colA][0][0] >= AC[rowB][colB][0][0]) {
						answerA += AC[rowA][colA][0][0];
						answerB += AC[rowB][colB][1][0];
						
					}
					else {
						answerA += AC[rowA][colA][1][0];
						answerB += AC[rowB][colB][0][0];
					}
				}
			}
			System.out.println("#" + test_case + " " + (answerA + answerB));
		}
	}
	public static void f(int row, int col, int c, int p, int startRow, int startCol, int check) {
		
		int num = Math.abs(row - startRow) + Math.abs(col - startCol);
		if(num > c) return;

		// 앞에가 가장 작은 것
		// p가 같지만 위치가 다른 경우는 ???
		if(AC[row][col][0][0] < p) {
			AC[row][col][0][0] = p;
			AC[row][col][0][1] = check;
			Arrays.sort(AC[row][col], Comparator.comparingInt(arr -> arr[0]));
			
			if(AC[row][col][0][0] != 0) {
				arrayAC[row][col] = 2;
			}
			else {
				arrayAC[row][col] = 1;
			}
		}

		for(int i = 1; i < 5; i++) {
			if(row + dy[i] <= 0 || row + dy[i] > 10) continue;
			if(col + dx[i] <= 0 || col + dx[i] > 10) continue;
			if(visited[row + dy[i]][col + dx[i]]) continue;
			visited[row + dy[i]][col + dx[i]] = true;
			f(row + dy[i], col + dx[i], c, p, startRow, startCol, check);
		}
	}
	
}