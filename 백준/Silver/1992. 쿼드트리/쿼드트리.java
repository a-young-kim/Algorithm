import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 흑백 영상 압축 데이터 구조 쿼드 트리
 * 흰점 0, 검은점 1인 이차원 배열
 * 주어진 영상이 모두 0이면 압축 경과는 0이고
 * 모두 1로만 되어 있으면 압축 결과는 1이다.
 * 0과 1이 섞여 있으면 전체를 한번에 나타내지 못하고 왼쪽 위, 오른쪽 위
 * 왼쪽 아래, 오른쪽 아래 이렇게 4개의 영상으로 나누어 압축한다
 * 
 *N * N 압축 영상이 주어 질때 이 영상을 압축한 결과를 출력하시오
 *
 *입력
 *영상 크기 숫자 N
 *N은 언제나 2의 제곱수 64 이하
 *길이 N의 문자열 N개
 *문자열을 0또는 1의 숫자
 *
 *출력
 *
 *문제 풀이
 *분할 정복
 *안에 일이 있는지 없는지 확인해야한다. 이를 dp로 ?? 
 *자기 위치 이하를 해서 저장하고 사이즈를 저장해서 dp[i][j] - dp[i - size][j] - dp[i][j -size] + dp[i - size][j - size]
 *인게 0이면 0을 하고 size가 감소하면 ( 넣고 재귀를 빠져나오면 )넣기
 
 */
public class Main {
	
	static int N;
	static int[][] dp, array;
	static String answer = "";
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][N + 1];
		array =  new int[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 1; j < N + 1; j++) {
				array[i][j] = s.charAt(j - 1) - '0';
			}
		}

		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < N + 1; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + array[i][j];
			}
		}
		f(N, 0, 0);
		System.out.println(answer);
	}
	
	public static void f(int size, int row, int col) {
		
		int sum = dp[row + size][col + size] - dp[row + size][col] - dp[row][col + size] + dp[row][col];
		
		if(sum == 0) answer += "0";
		else if(sum == size * size) answer += "1";
		else {
			answer +="(";
			for(int i = 0; i < 2; i ++) {
				for(int j = 0; j < 2; j++) {
					f(size / 2, row + size / 2 * i, col + size / 2 * j);
				}
			}
			answer += ")";
		}
	}
}