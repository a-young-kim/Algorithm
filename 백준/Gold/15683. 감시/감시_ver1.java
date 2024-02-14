import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] nums;
	static int answer = 0;
	
	// 상
		public static void goTop(int row, int col, boolean isZero) {
			int cnt = -1 * row * N - col - 1;
			for(int i = row - 1; i > -1; i--) {
				if(nums[i][col] == 6) break;
				if(!isZero && nums[i][col] == 0) nums[i][col] = cnt;
				if(isZero && nums[i][col] == cnt) nums[i][col] = 0;
			}
		}
		// 하
		public static void goBottom(int row, int col, boolean isZero) {
			int cnt = -1 * row * N - col - 1;
			for(int i = row + 1; i < N; i++) {
				if(nums[i][col] == 6) break;
				if(!isZero && nums[i][col] == 0) nums[i][col] = cnt;
				if(isZero && nums[i][col] == cnt) nums[i][col] = 0;
			}
		}
		
		//좌
		public static void goLeft(int row, int col, boolean isZero) {
			int cnt = -1 * row * N - col - 1;
			for(int i = col - 1; i > -1; i--) {
				if(nums[row][i] == 6) break;
				if(!isZero && nums[row][i] == 0) nums[row][i] = cnt;
				if(isZero && nums[row][i] == cnt) nums[row][i] = 0;
			}
		}
		//우
		public static void goRight(int row, int col, boolean isZero) {
			int cnt = -1 * row * N - col - 1;
			for(int i = col + 1; i < M; i++) {
				if(nums[row][i] == 6) break;
				if(!isZero && nums[row][i] == 0) nums[row][i] = cnt;
				if(isZero && nums[row][i] == cnt) nums[row][i] = 0;
			}
		}

	public static void changeNums(int num, int row, int col, boolean isZero) {
		switch(num) {
			case 1: {
				goTop(row, col, isZero);
				break;
			}
			case 2:{
				goBottom(row, col, isZero);
				break;
			}
			case 3:{
				goLeft(row, col, isZero);
				break;
			}
			case 4:{
				goRight(row, col, isZero);
				break;
			}
			default: break;
		}
	}
	
	public static void findAll(int startRow, int startCol) {
		if(answer == 0) return;
		
		for(int row = startRow; row < N; row++) {
			if(answer == 0) return;
			for(int col = 0; col < M; col++) {
				if(answer == 0) return;
				if(row == startRow && col < startCol) continue;
				if(nums[row][col] == 0 || nums[row][col] == -1 || nums[row][col] == 6) continue;
				switch(nums[row][col]) {
					// 한 방향
					case 1:{
						for(int i = 1; i < 5; i++) {
							changeNums(i, row, col, false);
							findAll(row, col + 1);
							changeNums(i, row, col, true);
						}
						break;
					}
					// 두 방향 확인, 마주보고 있는 두개
					case 2:{
						for(int i = 1; i < 4; i = i + 2) {
							// 상하, 좌우
							changeNums(i, row, col, false);
							changeNums(i + 1, row, col, false);
							findAll(row, col + 1);
							changeNums(i, row, col, true);
							changeNums(i + 1, row, col, true);
						}
						break;
					}
					// 두 방향 확인, 90도 두개
					case 3:{
						for(int i = 1; i < 3; i++) {
							for(int j = 3; j < 5; j++) {
								changeNums(i, row, col, false);
								changeNums(j, row, col, false);
								findAll(row, col + 1);
								changeNums(i, row, col, true);
								changeNums(j, row, col, true);
							}
						}
						break;
					}
					// 세 방향 확인
					// 우(4) 좌(3) 하(2) 상(1)
					// 상하좌, 상하우, 좌하우
					case 4:{
						for(int i = 1; i < 3; i++) {
							changeNums(i, row, col, false);
							changeNums(i + 1, row, col, false);
							changeNums(i + 2, row, col, false);
							findAll(row, col + 1);
							changeNums(i, row, col, true);
							changeNums(i + 1, row, col, true);
							changeNums(i + 2, row, col, true);
						}
						changeNums(1, row, col, false);
						changeNums(2, row, col, false);
						changeNums(4, row, col, false);
						findAll(row, col + 1);
						changeNums(1, row, col, true);
						changeNums(2, row, col, true);
						changeNums(4, row, col, true);
                        
                        changeNums(1, row, col, false);
						changeNums(3, row, col, false);
						changeNums(4, row, col, false);
						findAll(row, col + 1);
						changeNums(1, row, col, true);
						changeNums(3, row, col, true);
						changeNums(4, row, col, true);
						break;
					}
					
					// 네 방향 모두
					case 5:{
						changeNums(1, row, col, false);
						changeNums(2, row, col, false);
						changeNums(3, row, col, false);
					    changeNums(4, row, col, false);
						findAll(row, col + 1);
						changeNums(1, row, col, true);
						changeNums(2, row, col, true);
						changeNums(3, row, col, true);
						changeNums(4, row, col, true);
						break;
					}
				}
			}
		}
		
		int cnt = 0;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(nums[row][col] == 0) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		nums = new int[N][M];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				nums[row][col] = sc.nextInt();
				if(nums[row][col] == 0) answer++;
			}
		}
		
		findAll(0, 0);
		System.out.println(answer);
		
		sc.close();
	}
}