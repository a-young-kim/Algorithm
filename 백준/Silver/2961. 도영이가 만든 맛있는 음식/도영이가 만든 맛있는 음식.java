import java.util.Arrays;
import java.util.Scanner;

/*
 * 문제
 * 재료 N, 신맛 S 쓴맛 B를 알고 있음
 * 신맛은 사용한 재료의 신맛의 곱, 쓴 맛은 합
 * 쓴맛과 신맛의 차이를 적게 만드는 방법
 * 적어고 하나 이상의 재료
 * 
 * 입력
 * 첫째 줄 재료의 개수N 10이하 자연수
 * 다음 N개 줄에는 신맛과 쓴맛이 공백으로 구분
 * 모두 10억이하의 양의 정수
 * 
 * 출력
 * 가장 작은 요리의 차이 출력
 * 
 * 문제 해결
 * 부분 집합 문제 
 * 재귀를 돌려서 배열에 넣어줌
 * 차이가 1일때는 return, 음식의 개수가 현재 음식 개수가 같을 
 * 파라미터로 음식의 개수와 현재 신맛, 쓴맛의 값 그리고 인덱스 값 리턴, 현재 구하고자하는 음식의 개수 
 * ㅅ
 */
public class Main {
	
	static int N;
	static int[] sour, bitter;
	static boolean[] visited;

	static int[] array;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		sour = new int[N];
		bitter = new int[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			sour[i] = sc.nextInt();
			bitter[i] = sc.nextInt();
		}
		
		
		f(0, 1, 0);
		
		
		System.out.println(answer);
		sc.close();
	}
	
	public static void f(int idx, int s, int b) {
		if(idx != 0) {
			answer = Math.min(answer, Math.abs(s-b));
		}
		if(idx == N) return;
		
		
		for(int i = idx; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			f(i + 1, s * sour[i], b + bitter[i]);
			visited[i] = false;
		}
	}
}