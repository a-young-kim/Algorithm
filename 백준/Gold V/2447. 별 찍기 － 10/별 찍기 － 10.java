import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * N이 3의 거듭제곰이라고 할 때 N * N 정사각형 재귀적 패턴
 * 재귀적 패턴으로 별을 찍자
 * 가운데 공백이 있고 가은데를 제외한 모든 칸에 별이 하나씩 이싿. 
 * 
 *입력
 *첫째줄 N, N은 3의 거듭제곱
 *
 *출력
 *별 출력
 *
 *풀이 
 *재귀
 *N이 3이 될때까지 줄이고 별을 배열에 저장
 *배열에 저장된 것을 출력
 */
public class Main {
	
	static int N;
	static char[][] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		array = new char[N][N];
		f(N, 0, 0);
		
		for(int i = 0; i < N; i++) {
			sb.append(array[i]);
			sb.append('\n');
		}

		System.out.println(sb);
	}
	public static void f(int num, int row, int col) {
		if(num == 3) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(i == 1 && j == 1) array[row + i][col + i] = ' ';
					else array[row + i][col + j] = '*';
				}
			}
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) 
					setArray(num / 3, row + num / 3, col + num / 3);
				else f(num / 3, row + num / 3 * i, col + num / 3 * j);
			}
		}
	}
	public static void setArray(int num, int row, int col) {
		for(int i = row; i < row + num ; i++) {
			for(int j = col; j < col + num ; j++) {
				array[i][j] = ' ';
			}
		}
	}
}