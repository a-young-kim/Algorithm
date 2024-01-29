import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] numArrays = new int[19][19];;
	static int[][] visited = new int[19][19];
	static final int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
	static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	
	public static int dfs(int check, int k, int row, int col, int num, boolean setVisited) {
		if(row < 0 || row >= 19 || col < 0 || col >= 19) return num;
		
		if(numArrays[row][col] == check) {
			if(setVisited) {
				visited[row][col] = 1;
			}
			num = dfs(check, k, row + dy[k], col + dx[k], num + 1, setVisited);
		}
			
		return num;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test5.txt"));
		//여기에 코드를 작성하세요.
		
		Scanner sc = new Scanner(System.in);

		for(int i = 0 ; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				numArrays[i][j] = sc.nextInt();
			}
		}
		
		int answer = 0;

		// 하얀색 돌
		first: for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(numArrays[i][j] != 0) {
					int[] setNum = new int[8];
					// 0 1 가로 2 3세로 4 5 좌하 대각선 6 7 좌상 대각선
					for(int k = 0; k < 8; k++) {
						setNum[k] = dfs(numArrays[i][j], k, i + dy[k], j + dx[k], 0, false);
						if(k % 2 == 1 && (setNum[k] + setNum[k - 1]) == 4) {
							visited[i][j] = 1;
							dfs(numArrays[i][j], k - 1, i + dy[k - 1], j + dx[k - 1], 0, true);
							dfs(numArrays[i][j], k, i + dy[k], j + dx[k], 0, true);
							answer = numArrays[i][j];
							break first;
						}
					}
					
					
				}
			}
		}
		System.out.println(answer);
		first: for(int i = 0; i < 19; i ++) {
			for(int j = 0; j < 19; j++) {
				if(visited[j][i] == 1) {
					System.out.println(j + 1 +" "+ (i + 1));
					break first;
				}
			}
		}
		sc.close();	
	}
}