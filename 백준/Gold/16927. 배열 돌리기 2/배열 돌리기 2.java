import java.util.Arrays;
import java.util.Scanner;
/*
 * 메모리 
 */
/*
 * 문제
 * 크기가  N * M 배열이 있을 때
 * 배열은 반시계방향으로 돌려야한다. 
 * 배열과 정수 R이 주어졌을 때 배열을 R번 회전한 결과를 구하여랴
 * 
 * 입력
 * 첫째줄 배열의 크기 n, m 수행해야하는 회전 수 R
 * 둘째줄 부터 배열 A의 원속
 * 
 * 출력
 * 회전 시킨 결과
 * 
 * 문제 풀이
 * 
 * 바깥 수 개수 : (N - 1) * 2 + (M - 1) * 2 
 			(N - 3) * 2 + (M - 3) * 2
 	식을 통해 R을 나눠서 최소한의 회전 개수 구하기 
 	
 	해당 값이 0보다 작아지지 않을 떄 까지 while문 돌고
 	R이 0이 될 때의 왼쪽 상단의 index이 이동 위치 구해주기
 	
 	원래 배열과 새로운 배열을 for문을 돌아서 값 삽입
 	delta 이용
 * 
 */
public class Main {
	
	static int N, M, R;
	static int[][] array;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		array = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				array[i][j] = sc.nextInt();
			}
		}
		
		int[][] answer = new int[N][M];
		
		int index = 0;
		while(((N - 1 - index * 2) > 0) && ((M - 1 - index * 2) > 0)) {
			int cntEle = (N - 1 - index * 2) * 2 + (M - 1 - index * 2) * 2;

			int rotation = R % cntEle;
			int startRow = index, startCol = index;
			int startFlag = 0;
			while(rotation > 0) {
				int[] set = setIndex(startRow, startCol, index, startFlag);
				startRow = set[0];
				startCol = set[1];
				startFlag = set[2];
				rotation --;
			}
	
			int arrayRow = index, arrayCol = index;
			int arrayFlag = 0;
			
			for(int i = 0; i < cntEle; i++) {
				answer[startRow][startCol] = array[arrayRow][arrayCol];
				int[] set = setIndex(startRow, startCol, index, startFlag);
				startRow = set[0];
				startCol = set[1];
				startFlag = set[2];
				
				int[] set2 = setIndex(arrayRow, arrayCol, index, arrayFlag);
				arrayRow = set2[0];
				arrayCol = set2[1];
				arrayFlag = set2[2];
			}
			index ++;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
		sc.close();
	}
	public static int[] setIndex(int row, int col, int index, int flag) {
		int[] set = new int[3];
		
		if(row + dy[flag] >= N - index || row + dy[flag] < index) flag ++;

		else if(col + dx[flag] >= M - index || col + dx[flag] < index) flag ++;
		
		flag = flag % 4;
		set[0] = row + dy[flag];
		set[1] = col + dx[flag];
		set[2] = flag;
		return set;
	}
}