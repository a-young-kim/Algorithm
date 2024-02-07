import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 가로 세로 크기가 100인 정사각형 모양의 흰색 도화지
 * 가로 세로의 크기가 10인 정사각형 모양의 검은 색 색종이를 도화지와 평행하도록 붙인다
 * 건은 영역의 넓이를 구하는 프로그램을 작성하기오
 * 
 * 입력
 * 색종이 수 100이하
 * 색종이가 붙은 위치, 색종이의 왼쪽 변과 도화디의 외쪽변 사이의 거리, 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 ㄱ리
 * 
 * 출력
 * 검은 영역의 넓이
 * 
 * 풀이 방식
 * 좌표를 구한다. 
 * 100 * 100 배열에서 색종이가 차지하는 구간을 1로 표시하고 
 * 나중에 배열을 더해준다. 
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] array = new int[100][100];
		int[][] num = new int[N][2];
		int size = 10;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			num[i][0] = Integer.parseInt(st.nextToken());
			num[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = num[i][1]; j < num[i][1] + size && j < 100; j++) {
				for(int k = num[i][0]; k < num[i][0] + size && k < 100; k++) {
					array[j][k] = 1;
				}
			}
		}
		int answer = 0;
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				answer += array[i][j];
			}
		}
		System.out.println(answer);
	}

}