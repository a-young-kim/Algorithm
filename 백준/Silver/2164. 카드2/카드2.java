import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * N장의 카드, 카드는 1부터 N까지, 1번 카드가 가장 위, N카드가 가장 아래
 * 제일 위의 카드 바닥에 버리고 그다음 제일 위에 있는 카드를 아래카드로 옮기는 것을 한장 남을 때 까지 반복
 * 
 * 입력
 * 정수 N 5십만 이하
 * 
 * 출력 
 * 카드 번호
 * 
 * 문제 풀이
 * 큐로 풀기 한개 남을 때 까지
 * while문 이용 poll()을 이용해서 삭제 offer() 삽입 size로 크기 확인
 * ArrayDeque
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		while(q.size() != 1) {
			q.poll();
			q.offer(q.poll());
		}
		
		System.out.println(q.poll());
	}

}