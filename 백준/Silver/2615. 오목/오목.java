import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 19 * 19 크기의 바둑판
 * 연속적으로 5알을 놓이면 그 색이 이김 -> 가로, 세로, 대각선
 * 입력으로 바둑판의 상태가 주어졌을 때 승부 결과 출력
 * 
 * 입력
 * 검은 바둑알 1, 흰바둑알 2 알이 놓이지 않는 자리 0
 * 
 * 출력
 * 검은 색이 이겼을 경우1, 흰색이 이겼을 경우2 승부가 결정되지 않은 경우0
 * 
 * 풀이 방식
 * 재귀로 풀음
 * 이전 값이 현재 값과 같으면 return
 * 세로 먼저 탐색
 * 5개인 것을 찾으면 return
 */
public class Main {
	static int[][] array = new int[19][19];
		static int[] dx = {1, 0, 1, 1}, dy = {0, 1, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ;j < 19; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int i, j = 0;
		int answer = 0;
		first: for(i = 0; i < 19; i++) {
			for(j= 0; j < 19; j++) {
				int cnt = 0;
				if(array[j][i] == 0) continue;
				for(int k = 0; k < 4; k++) {

					if(j - dy[k] >= 0 && i - dx[k] >= 0 &&  i - dx[k] < 19 && j - dy[k] < 19 &&
							array[j - dy[k]][i - dx[k]] == array[j][i]) continue;
					cnt = f(j, i, k, array[j][i], 0);
					if(cnt == 5) {
						answer = array[j][i];
						break first;
					}
				}
			}
		}

		if(i == 19 && j == 19) System.out.println(0);
		else {
			System.out.println(answer);
			System.out.println((j + 1) + " " + (i + 1));
		}
	}
	
	public static int f(int row, int col, int flag, int state, int count) {
		
		
		if(array[row][col] != state) return count;
		count++;
		if(row + dy[flag] >= 19 || row + dy[flag] < 0) return count;
		if(col + dx[flag] >= 19 || col + dx[flag] < 0) return count;
		count = f(row + dy[flag], col + dx[flag], flag, state, count);
		return count;
	}

}