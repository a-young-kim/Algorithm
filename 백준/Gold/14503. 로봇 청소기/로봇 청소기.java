

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 청소하는 영역의 개수를 구하는 프로그램
 * 2. N * M 크기의 직사각형, 각 칸은 벽 또는 빈 칸
 * 3. 청소기는 바라보는 방향이 있으며 각 방향은 동서남북 중 하나
 * 4. 각 칸의 좌표는 (r, c) -> 가장 북쪽 줄의 가장 서쪽 칸의 좌표 (0, 0)부터 가장 남쪽 줄의 (N - 1, M - 1)
 * 5. 처음 비 칸은 청소 안되어 있는 상태
 * 6. 청소기의 작동 과정
 * 	6.1 현재 칸이 청소 X인 경우 청소
 * 	6.2 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
 * 		6.2.1 후진 가능하면 후진
 * 		6.2.2 후진 불가능하면 작동을 멈춤
 * 	6.3 주변 칸 중 청소하지 않은 빈칸이 있을 경우
 * 		6.3.1 반시게 방향으로 90 회전
 * 		6.3.2 바라보는 방향 기준으로 앞쪽 칸이 청소되지 않은 빈칸이면 한칸 전진
 * 
 *  입력
 *  1. N, M 입력 각각 50 이하
 *  2. (r, c), d(방향 ) 입력 0123 북동남서
 *  3. 각 장소의 상태 입력 0이면 청소되지 않은 빈칸 1인경우 벽
 *  4. 로봇 청소기가 있는 칸은 항상 빈칸
 *  
 *  출력
 *  청소하는 칸의 개수
 *  
 *  풀이
 *  1. int N, M입력, int[] dx, dy 생성
 *  2. 로봇 청소기의 위치 입력
 *  3. int[][] map 입력
 *  4. while문 시작
 *  4.1 현재 위치 확인
 *  4.2 주변 위치 확인
 *  4.3 후진 확인
 *  
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		//북동남서
		int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
		int[] robot = new int[3];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			robot[i] = Integer.parseInt(st.nextToken());
		}

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) map[i][j] = -1;
			}
		}

		while(true) {
			int row = robot[0];
			int col = robot[1];
			int dir = robot[2];
			
			// 현재 위치 확인
			if (map[row][col] == -1) {
				map[row][col] = 0;
				answer++;


			} else {
				// 주변에 청소되지 않은 빈칸이 있는지 확인
				boolean isExist = false;
				for (int i = 0; i < 4; i++) {
					int nr = row + dy[i];
					int nc = col + dx[i];

					if (nr < 0 || nr >= N)
						continue;
					if (nc < 0 || nc >= M)
						continue;
					if (map[nr][nc] != -1)
						continue;
					isExist = true;
					break;
				}

				// 존재할 경우
				if (isExist) {
					// 바라보는 방향이 청소되지 않았을 경우
					int ndir = dir == 0 ? 3 : dir - 1;
					int nr = row + dy[ndir];
					int nc = col + dx[ndir];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
						// 반시계 북 -> 서
						robot[2] = ndir;
					}
				
					else if(map[nr][nc] == -1) {
						robot[0] = nr;
						robot[1] = nc;
						robot[2] = ndir;
					}
					else {
						dir = dir == 0 ? 3 : dir - 1;
						robot[2] = ndir;
					}

				} else {
					// 존재하지 않은 경우
					switch (dir) {
					case 0:
						dir = 2;
						break;

					case 1:
						dir = 3;
						break;
					case 2:
						dir = 0;
						break;
					case 3:
						dir = 1;
						break;
					}
					
					int nr = row + dy[dir];
					int nc = col + dx[dir];
					
					if(nr < 0 || nr >= N) break;
					if(nc < 0 || nc >= M) break;
					if(map[nr][nc] == 1) break;
					
					robot[0] = nr;
					robot[1] = nc;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static void print(int[][] map) {
		for(int i =0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}
