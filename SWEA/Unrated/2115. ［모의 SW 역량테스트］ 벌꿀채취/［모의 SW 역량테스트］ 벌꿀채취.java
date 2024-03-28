
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. N * N 벌통
 * 2. 각 칸의 숫자의 꿀의 양 
 * 3. 두명의 일꾼이 존재, 꿀을 채취할 수 있는 벌통의 수 M
 * 4. 각각의 일꾼은 가로로 연속되도록 M개의 벌통을 선택 후 채취
 * 5. 선택할 벌통은 서로 겹치면 안된다. 
 * 6. 하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 한다. 
 * 7. 일부분만 채취할 수 없고 벌통에 있는 모든 꿀 채취, 
 * 8. 채취할 수 있는 꿀의 양 C
 * 9. 두 일꾼이 꿀을 채취하여 얻을 수 있는 수익의 합이 최대가 되는 경우를 찾고 그 때의 수익을 출력
 * 
 * 제약사항
 * 1. N 10 이하 정수
 * 2. M 5이하
 * 3. C는 10 이상 30 이하
 * 4. 하나의 버롱에서 채취할 수 있는 꿀의 양은 9이하
 * 
 * 아이디어
 * 조합
 * 
 * 풀이
 * 1. T 입력
 * 2. 벌통 크기 N, 벌통 개수 M, 양 C 입력
 * 3. 가능한 벌통의 조합 구하는 방식
 * 		- int[N][N - M][M] 에 넣기
 * 4. 벌통 내에서 구할 수 있는 값
 * 		- M - 1개 선택할 때부터의 값
 * 5. 해당 값을 가지고 조합
 * 
 *  시간 복잡도 N * M * (N * M) + 2^M + 
 * 	
 * 
 */
public class Solution {
	
	static int N, M, C, answer, max;
	static int[] save;
	static int[][] array;
	// 가능한 조합
	static int[][][] array2, array3;
	// 조합 사용
	static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			answer = Integer.MIN_VALUE;
			
			array = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			array2 = new int[N][N - M + 1][M];
			array3 = new int[N][N - M + 1][2]; // 채취한 양, 가격
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N - M + 1; j++) {
					for(int k = 0; k < M; k++) {
						array2[i][j][k] = array[i][j + k];
						array3[i][j][0] +=array[i][j + k];
						array3[i][j][1] += array[i][j + k] * array[i][j + k];
					}
				}
			}
			
			nodes = new Node[N * (N - M + 1)];

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N - M + 1; j++) {
					if(array3[i][j][0] <= C) {
						nodes[(N - M + 1) * i + j] = new Node(i, j, array3[i][j][1]);
						continue;
					}
					max = 0;
					f(i, j, 0, 0, 0, 0);
					nodes[(N - M + 1) * i + j] = new Node(i, j, max);
				}
			}
			
			f2(0, 0, 0, 0, 0);
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static void f2(int idx, int cnt, int beforeRow, int beforeCol, int value) {
		if(cnt == 2) {
			answer = Math.max(answer,  value);
			return;
		}

		for(int i = idx; i <N * (N - M + 1); i++) {
			if(cnt == 0) f2(i + 1, 1, nodes[i].row, nodes[i].col, nodes[i].price);
			else {
				if(nodes[i].row == beforeRow && nodes[i].col - beforeCol <= M ) continue;
				else f2(i + 1, 2, nodes[i].row, nodes[i].col, nodes[i].price + value);
			}
		}
	}
	
	public static void f(int row, int col, int idx, int c, int price, int save) {

		for(int i = idx; i < M; i++) {
			int c2 = c + array2[row][col][i];
			int price2 =  price +  array2[row][col][i] * array2[row][col][i];
			
			if(c2 > C) continue;
			max = Math.max(max, price +  array2[row][col][i] * array2[row][col][i]);
			f(row, col, i + 1, c2, price +  array2[row][col][i] * array2[row][col][i], Math.max(save, price2));
		}
	}
	
	
	public static class Node{
		int row;
		int col;
		int price;
		
		
		public Node(int row, int col, int price) {
			super();
			this.row = row;
			this.col = col;
			this.price = price;
		}


		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", price=" + price + "]";
		}
		
		
	}

}
