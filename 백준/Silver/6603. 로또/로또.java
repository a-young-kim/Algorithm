import java.util.Arrays;
import java.util.Scanner;

/*
 * 로또
 * 문제 1 ~ 49중 수 6개 선택
 * 모든 방법을 구하는 프로그램 작성
 * 
 * 입력
 * 첫째 수 K
 * 다음 k개의 수는 집한 S에 포함되는 수
 * S이 원소는 오름차순
 * 입력의 마지막 줄에는 0이 하나
 * 
 * 출력
 * 각 테스트 케이스마다 수를 고르는 모든 방법 출력, 사전순
 * 각 테스트 케이스 사이의 빈 줄 추
 */
public class Main {
	
	static int[] array;
	static int[] answer;
	static int K;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			K = sc.nextInt();
			
			if(K == 0) break;
			
			array = new int[K];
			answer = new int[6];
			for(int i = 0; i < K; i++) {
				array[i] = sc.nextInt();
			}
			f(0, 0);
			System.out.println();
		}
		
		sc.close();
	}
	
	public static void f(int index, int cnt) {
		if(cnt == 6) {
			Arrays.stream(answer).forEach(a -> System.out.print(a + " "));
			System.out.println();
			return;
		}
		
		for(int i = index; i < K; i++) {
			answer[cnt] = array[i];
			f(i + 1, cnt + 1);
		}
	}

}