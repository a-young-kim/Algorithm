import java.util.LinkedList;
import java.util.Scanner;

/*
 * 문제
 * 0 ~ 999999 사이의 수 나열하여 만든 암호문
 * 
 * 1. I삽입 x,y s: 앞에서부터 x의 위치 바로 다음에 y개의 숫자 삽입
 * s는 덧붙일 숫자들
 * 
 * 입력
 * 원본 암호문 길이 N
 * 원본 암호문
 * 명렬어 개수
 * 명령어
 * 
 * 총 10개의 테스트 케이스
 * 
 * 출력 
 * #테스트케이스 번호 
 */
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int N = sc.nextInt();
			LinkedList<Integer> linkedlist = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				linkedlist.add(sc.nextInt());
			}
			
			int M = sc.nextInt();
			for(int i = 0; i < M; i++) {
				sc.next();
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				for(int j = 0; j < y; j++) {
					linkedlist.add(x + j, sc.nextInt());
				}
			}
			
			System.out.print("#" + test_case + " ");
			
		 for (int i = 0; i < 10; i++) {
			 System.out.print(linkedlist.get(i) +" ");
	     }
            System.out.println();
	
		}
		sc.close();
	}

}