import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 문제
 * 계단 오르기
 * 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임
 * 각 게단에는 일정한 점수가 존재하는데 계단을 밟으면 게단에 쓰여있는 점수를 얻음
 * 
 * 계단을 오르는 규칙
 * 1. 한번에 하나 혹은 두개 가능
 * 2. 연속된 세개의 계단을 모두 밟아서는 안됨(시작점은 계단 포함 X)
 * 3. 마지막 도착 계단은 반드시 밟아야한다. 
 * 최대 값을 구하는 프로그램 작성
 * 
 * 입력
 * 첫째줄 계단의 개수
 * 둘째줄 제일 아래 놓인 계단으로 부터 순서대로 각 계단에 쓰여있는 점수 주어짐
 * 계단의 개수 300이하, 점수는 10000이하의 자연수
 * 
 * 출력
 * 총 점수의 최댓값
 * 
 * 해결 방안
 * dp를 이용하여 구현한다. 
 * 마지막까지 더하는 것을 + 3을 하여 해당 값이 무조건 포함하도록 구현한다. 
 * 
 */
public class Main {
	
	static int N;
	static int numArray[], dp[];
	static boolean check[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArray = new int[N + 3];
		dp = new int[N + 3];
		check = new boolean[N + 3];
		Arrays.fill(check, true);
		
		for(int i = 0; i < N; i++) {
			numArray[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = numArray[0];
		if(N > 1) {
			// dp[1] = numArray 0 과 1
			dp[1] = dp[0] + numArray[1];
			check[0] = true;
			check[1] = false;
		}
		
		if(N > 2) {
			// dp[2] = numArray 0 과 1 혹은 1 2 중 큰 값 넣기, 1 2 일 경우 3 접근 못함 삽입
			int mathNum = Math.max(numArray[0] + numArray[2], numArray[1] + numArray[2]);
			if(mathNum == numArray[0] + numArray[2]) {
				check[2] = true;
			}
			else {
				check[2] = false;
			}
			dp[2] = mathNum;
			for(int i = 3; i < N + 2; i++) {
				// 이전 값이 이어서 더할 경우가 아닐 때
				if(check[i - 1]) {
					// 이전값 혹은 전전 값중 큰 값 선택
					dp[i] = Math.max(dp[i - 1], dp[i - 2]) + numArray[i];
					if(dp[i] == dp[i - 1] + numArray[i]) {
						check[i] = false;
					}
					
				}
				// 이전 값이 이어서 더한 값이어서 전 값을 못 더할 경우
				else{
					// 전전 값 혹은 전전전 dp에서 이전 값 numArray를 더한 값 중 큰 값 넣기
					dp[i] = Math.max(dp[i - 2], dp[i - 3] + numArray[i - 1]) + numArray[i];
					if(dp[i] != dp[i - 2] + numArray[i]) check[i] = false;
				}
			}
			
		}
		System.out.println(dp[N - 1]);

	}
}