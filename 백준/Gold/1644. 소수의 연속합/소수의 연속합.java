import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수
 * 2. 한 소스는 한 번의 덧셈에 사용도리 수 있다. 
 * 3. 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수
 * 
 * 입력
 * 1. 자연수 N 4백만 이하
 * 
 * 출력
 * 소수의 합으로 나타낼 수 있는 경우의 수
 * 
 * 아이디어 
 * 투포인터
 * 
 * 풀이
 * 1. 자연수 N 입력
 * 2. booealn[N + 1] check 배열 생성
 * 3. check[1] = false 처리
 * 4. for 2부터 N + 1까지
 *  4.1 for문 2부터 N 까지
 *  4.2 int nNum = n * j 가 N이하이면 false 처리
 * 5. List<Integer> decimal 에 true인 값만 넣어주기
 * 6. int start = 0, end = 0, weight = 0;
 * 7. weight가 N보다 작으면 end ++, 크면 start +
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		boolean[] check = new boolean[N + 1];
		check[0] = true;
		check[1] = true;
		
		for(int i = 2; i < N + 1; i++) {
			if(check[i]) continue;
			for(int j = 2; j < N + 1; j++) {
				int nNum = i * j;
				if(nNum > N) break;
				check[nNum] = true;
			}
		}
		
		List<Integer> decimal = new ArrayList<>();
		for(int i = 2; i < N + 1; i++) {
			if(check[i]) continue;
			decimal.add(i);
		}
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		int start = 0, end = 0, weight = decimal.get(0);
		int answer = 0;
		while(start <= end) {
			if(weight < N) {
				end++;
				if(end >= decimal.size()) break;
				weight += decimal.get(end);
			}
			else if(weight > N) {
				if(start + 1 >= decimal.size()) break;
				weight -= decimal.get(start);
				start ++;
			}
			else {
				answer++;
				end++;
				if(start + 1 >= decimal.size()) break;
				weight -= decimal.get(start);
				if(end >= decimal.size()) break;
				weight += decimal.get(end);
				start++;
			}
		}
		
		System.out.println(answer);
		
	}

}