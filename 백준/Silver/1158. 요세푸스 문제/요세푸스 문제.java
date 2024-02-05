import java.util.LinkedList;
import java.util.Scanner;

/*
 * 문제
 * 1번부터 N번까지의 사람이 원을 이루면서 앉아있고 양의 정수 K
 * 순서대로 K번쨰 사람 제거
 * N명의 사람이 제거될때까지 계쏙
 * 
 * N과 K가 주어지면 요세푸스 순열을 구하여라
 * 
 * 입력 
 * 첫째줄 N과 K
 * 출력
 * 요세푸스 순열
 * 
 * 
 */
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] answer = new int[N + 1];
		
		LinkedList<Integer> numList = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			numList.add(i + 1);
		}
		
		int cnt = 0;
		for(int i = 1; i < N + 1; i ++) {
			if(i != 0) cnt += K - 1;
			if(cnt > numList.size() - 1) {
				while(cnt > numList.size() - 1) {
					cnt = cnt - numList.size();
				}
			}
			answer[i] = numList.get(cnt);
			numList.remove(cnt);
		}
		sc.close();
		
		System.out.print("<");
		for(int i = 1 ; i < N + 1; i++) {
			if(i != N) System.out.print(answer[i] + ", ");
			else System.out.print(answer[i]);
		}
		System.out.print(">");
	}

}