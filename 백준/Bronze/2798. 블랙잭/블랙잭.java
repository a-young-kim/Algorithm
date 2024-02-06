import java.util.Scanner;

/*
 * 문제
 * 카드의 합이 21을 넘지 않는 한도에서 카드의 합을 최대한 크게 만드는 게임
 * 새로운 규칙 제작
 * 딜러는 N장의 카드를 모두 숫자가 보이도록 바닥에 내려놓음 딜러는 숫자 m을 크게 되침
 * 플레이어는 제한된 시간 안에 n장의 카드 중에서 3장의 카드를 골라야한다. 
 * 플러이어나 고른 카드의 합은 m을 넘지 않으면서 M과 최대한 가깝게 만들어야한다. 
 * 
 * N장의 카드에 써져있는 숫자가 주어졌을 때, M을 넘지 않으면서 m에 최대한 가까운 카드 3장의 합을 구해 출력
 * 
 * 입력
 * 카드의 개수 N, M
 * 카드에 쓰여있는 수, 100 000 넘지 않는 양의 정수
 * 
 * 출력
 * 카드 3장의 합
 *
 *문제 풀이
 *조합, 재귀
 * 백만 이하 
 */
public class Main {
	static int N;
	static final int max = 3;
	static int M;
	static int[] array;
	static int answer = 0;
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		array = new int[N];
		
		for(int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		
		f(0, 0, 0);
		System.out.println(answer);
		sc.close();
	}
	
	public static void f(int idx, int sum, int cnt) {
		if(cnt == 3) {
			if(sum <= M) answer = Math.max(answer, sum);
			return;
		}
		
		for(int i = idx; i < N; i++) {
			f(i + 1, sum + array[i], cnt + 1);
		}
	}
}