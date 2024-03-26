import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 탁자 위에 돌 N개
 * 2. 2명이서 번갈아 돌을 가져가면 돌은 1개 또는 3개 가져갈 수 있으며 마지막 돌을 가져가는 사람이 승리
 * 3. 두 사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는 프로그램
 * 4. 게임은 상근이 시작
 * 
 * 입력
 * N 1000 이하
 * 
 * 출력
 * 상근 -> SK
 * 창영 -> CY
 * 
 * 풀이
 * 짝수로 나누어 떨어지면 상근
 * 홀수로 나누어 떨어지면 창영
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		if(N % 2== 0) System.out.println("CY");
		else System.out.println("SK");

	}

}