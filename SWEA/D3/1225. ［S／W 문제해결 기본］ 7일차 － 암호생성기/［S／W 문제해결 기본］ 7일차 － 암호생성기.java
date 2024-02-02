import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * N개의 수를 처리하면 8자리의 암호 생성
 * 8자리 숫자 입력
 * 첫번째 순서를 1 갑소시키고 맨뒤로 보냄
 * 다음 수는 2감소시키고 맨뒤, 그 다음은 3 ....
 * 이와 같은 작업을 한 사이틀
 * 숫자가 감소할 때 0보다 작아지면 0으로 유지하고 프로그램 종료 이때 8자리 숫자 값 암호
 * 
 * 제약 사항
 * inter 암호 배열은 한자리 수
 * 
 * 입력
 * 테스트 케이스 번호
 * 8개의 데이터
 * 
 * 출력 
 * #테스트케이스번호 답
 * 
 * 풀이
 * queue에 입력 받고 
 * while문 forans 이용하여 poll offer
 * 0을 만나면 break
 * 0은 맨 뒤에 넣어주기
 * 
 * 
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for(int test_case = 1; test_case <= 10; test_case++)
		{	
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 st.nextToken();

			 st = new StringTokenizer(br.readLine());
			 Queue<Integer> q = new ArrayDeque<>();
			 
			 for(int i = 0; i < 8; i++) {
				 q.offer(Integer.parseInt(st.nextToken()));
			 }
			 int cnt = 1;
			 first: while(true) {
				 if(cnt == 6) cnt = 1;
				 
				 int ele = q.poll();
				 if(ele - cnt <= 0) {
					 q.offer(0);
					 break first;
				 }
				 q.offer(ele - cnt);
				 cnt++;
				 
			 }
			 System.out.printf("#" + test_case + " ");
			 for(int i = 0; i < 8; i++) {
				 System.out.printf(q.poll() + " ");
			 }
			 System.out.println();

		}
	}

}