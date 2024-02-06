import java.util.Scanner;

/*
 * 문제
 * 가로 9 세로 9 스토쿠
 * 가로 세로 3*3 사각형 일치하면 안됨
 * 겹치는 숫자가 없으면 0 있으면 1
 * 
 * 제약사항
 * 퍼즐은 모두 숫자로 채워진 상태
 * 입력으로 주어진 퍼질 모든 숫자는 1이상 9이하
 * 
 * 입력
 * 첫 줄 테스트 케이스 개수 T
 * 
 * 풀이 방식
 * 가로 세로 사각형 따로따로
 * 비트마스킹
 * 
 * 00000 & 00001 = 00000 아니면  0
 * 
 */
public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int[][] array = new int[9][9];
			for(int i = 0; i < 9; i ++) {
				for(int j = 0; j < 9; j ++) {
					array[i][j] = sc.nextInt();
				}
			}
			int answer = 1;
			// 가로 
			first: for(int i = 0; i < 9; i++) {
				int b = 0;
				for(int j = 0; j < 9; j ++) {
					int c = b & (1 << (array[i][j] - 1));
					if(c != 0) {
						answer = 0;
						break first;
					}
					b = b | (1 << (array[i][j] - 1));
				}
			}
			if(answer == 1) {
				// 세로
				first: for(int i = 0; i < 9; i++) {
					int b = 0;
					for(int j = 0; j < 9; j ++) {
						int c = b & (1 << (array[j][i] - 1));
						if(c != 0) {
							answer = 0;
							break first;
						}
						b = b | (1 << (array[j][i] - 1));
					}
				}
			}
			
			if(answer == 1) {
				// 사각 형
				first: for(int i = 0 ; i < 9 ; i = i + 3) {
                    int b = 0;
					for(int j = i ; j < i + 3; j++) {
						for(int k = i; k < i + 3; k++) {
							int c = b & (1 << (array[j][k] - 1));
							if(c != 0) {
								answer = 0;
								break first;
							}
							b = b | (1 << (array[j][k]  - 1));
						}
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
			
		}
	}
	

}