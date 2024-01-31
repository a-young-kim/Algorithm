import java.util.Arrays;
import java.util.Scanner;

/*
 * 문제
 * 자연수 N과 M이 주어졌을 때 
 * 1. 1부터 N까지 자연수 중 중복 없이 N개르 고른 수열
 * 2. 수열을 오름차순
 * 3. 길이는 M
 * 을 모두 구하여서
 * 
 * 입력
 * 첫째 줄 N과 M 자연수
 * 
 * 출력
 * 한 줄에 하나씩 수열을 출력
 * 중복되는 수열을 여러번 출력 안됨
 * 공백으로 구분
 * 사전 순으로
 * 
 * 해결 방법
 * 재귀를 이용
 * 파라미터 시작 index cnt
 * array에 넣고 cnt가 M이면 출력 후 return
 */
public class Main {
	static int N,M;
	static int[] array;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		array = new int[M];
		
		sc.close();
		f(1, 0);
	}
	
	public static void f(int idx, int cnt) {
		if(cnt == M) {
			for(int i = 0; i < array.length; i++) {
				System.out.print(array[i] +" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i <= N; i++) {
			array[cnt] = i;
			f(i + 1, cnt + 1);
		}
	}
}