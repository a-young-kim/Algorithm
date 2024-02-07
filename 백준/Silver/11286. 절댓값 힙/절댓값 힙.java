import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 배열에 정수 X 삽입 0 아님
 * 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거
 * 절대값이 가장 작은 값이 여러개 일떄 가장 작은 수를 출력하고 그 값을 배열에서 제거
 * 
 * 입력
 * 연산의 개수 N 십만 이하
 * 연산에 대한 정보를 나타내는 정수 x
 * x가 0이 아니라면 배열에 x라는 값일 추가하는 것, 
 * x가 0으면 절대값이 가장 작은 값을 출력하고 그 값을 배열에서 제거
 * 입력 정수는 int에서 가능
 * 
 * 출력
 * 입력에서 0이 주어진 횟수만큼 답을 출력
 * 배열이 비어있을 경우에는 0을 출력
 * 
 * 풀이 
 * 우선순위 큐를 넣고 comparable을 사용하여 조정
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->{
			if(Math.abs(a) == Math.abs(b)) return a - b;
			return Math.abs(a) - Math.abs(b);
		});
		
		for(int i = 0 ;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0) {
				if(pq.isEmpty()) {
					System.out.println(0);
					continue;
				}
				System.out.println(pq.poll());
			}
			else {
				pq.add(num);
			}
		}

	}

}