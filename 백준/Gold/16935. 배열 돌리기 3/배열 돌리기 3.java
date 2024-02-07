import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문재
 * N * M 배열 배열에 연산을 R번 적용
 * 연산을 총 6개
 * 1. 상하 반전 
 * 2. 좌우 반전 
 * 3. 오른쪽 90도 회전
 * 4. 왼쪽 90도 회전
 *  N/2×M/2인 4개의 부분 배열
 * 5. 1번 -> 2번으로 2번 -> 3번으로 이동
 * 6. 1번 -> 4번으로 이동
 * 
 * 입력
 * 배열 크기 N, M, 연산 수 R
 * N개의 줄에 배열의 원소 주어짐
 * 수행해야하는 연산 순서대로 적용
 * 
 * 풀이
 * while문을 돌려서 각 입력을 처리한다.
 * 1 2는 하나의 함수를 만들어서 index를 마이너스를 통해 바꿔준다
 * 3 4는  index를 좌우 반전으로 만들고 오르쪽 회전이면 가로에 빼주로 왼쪽이면 세로에서 뺴눔
 * 5 6은 인덱스의 범위에 따라 번호를 정하고 오니쪽 오른쪽 구분
 */
public class Main {

	static int N, M, R;
	static int[][] array, nextArray, blankArray;

	public static void five() {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < M; k++) {
				// 3번
				if (j >= N / 2 && k >= M / 2) {
					nextArray[j][k - M / 2] = array[j][k];
				}
				// 4번
				else if (j >= N / 2) {
					nextArray[j - N / 2][k] = array[j][k];
				}
				// 2번
				else if (k >= M / 2) {
					nextArray[j + N / 2][k] = array[j][k];
				} else {
					nextArray[j][k + M / 2] = array[j][k];
				}
			}
		}
	}

	public static void six() {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < M; k++) {
				// 3번
				if (j >= N / 2 && k >= M / 2) {
					nextArray[j - N / 2][k] = array[j][k];
				}
				// 4번
				else if (j >= N / 2) {
					nextArray[j][k + M / 2] = array[j][k];
				}
				// 2번
				else if (k >= M / 2) {
					nextArray[j][k - M / 2] = array[j][k];
				} else {
					nextArray[j + N / 2][k] = array[j][k];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int max = Math.max(N,  M);
		array = new int[max][max];
		nextArray = new int[max][max];
		blankArray =  new int[max][max];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int r = Integer.parseInt(st.nextToken());

			switch (r) {
			case 1: {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						nextArray[N - j - 1][k] = array[j][k];
					}
				}
				break;
			}
			case 2: {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						nextArray[j][M - k - 1] = array[j][k];
					}
				}
				break;
			}
			case 3: {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						nextArray[k][N - j - 1] = array[j][k];
					}
				}
				int c = M;
				M = N;
				N = c;
				break;
			}
			case 4: {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						nextArray[M - k - 1][j] = array[j][k];
					}
				}
				int c = M;
				M = N;
				N = c;
				break;
			}
			case 5: {
				five();
				break;
			}
			case 6: {
				six();
				break;
			}
			}
			
			blankArray = array;
			array = nextArray;
			nextArray = blankArray;
			for(int j = 0; j < N; j++) {
				Arrays.fill(blankArray[j], 0);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			 sb = new StringBuilder();
			for(int j = 0 ;j < M ; j++) {
				sb.append(array[i][j] + " ");
			}
			System.out.println(sb);
		}

	}

}