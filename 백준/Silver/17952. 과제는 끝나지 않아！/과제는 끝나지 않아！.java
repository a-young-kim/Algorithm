import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제
 * 많은 업무가 주어지는데 업무가 줄어들 기미가 보이지 않는다. -> 분단위로 업무가 추가된다. 
 * 업무의 마감 기한은 1분기가 끝날 때까지이다. 
 * 다음 순서로 업무를 처리해 나가고 있다.
 * 
 * 1. 업무는 가장 최근에 주어진 순서로 한다. 또한 업무를 받으면 바로 시작한다. 
 * 2. 업무를 하던 도중 새로운 업무가 추가된다면 하던 업무를 중잔하고 새로운 업무를 진행한다. 
 * 3. 새로운 업무가 끝났다면 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다. 
 * 
 * 업무를 받자마자 업무가 몇 분이 걸릴지 정확하게 알 수 있고 마감한 업무는 무조건 만점을 받는다.
 * 
 * 자기가 받을 업무 평가를 점수로 예상해 보자
 * 
 * 입력
 * 이분 분기가 몇 분인지를 나타내는 정수 N백만 이하
 * N줄 동안 이번 분기가 시작하고 N분쨰 주어진 업무의 정보가 두 경우 중 하나
 * 1 A T : 업무의 만점의 A점이고 이 과제를 해결하는데 T분이 걸린다. 
 * 0 : 해당 시점에는 업무가 주어지지 않았다.
 * 
 * 출력
 * 이번 분기 업무 평가 점수
 * 
 * 풀이 
 * Stack 사용하고 
 * class 만들고
 */
public class Main {
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Deque<Work> works = new ArrayDeque<>();

		int answer = 0;
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num == 1){
				int weight = Integer.parseInt(st.nextToken());
				int work = Integer.parseInt(st.nextToken());
				works.add(new Work(work, weight));
			}
			
			if(works.size() == 0) continue;
			Work work = works.pollLast();
			work.work--;
			if(work.work != 0) works.add(work);
			else {
				answer += work.weight;
			}
		}
		
		System.out.println(answer);
	}
	
	public static class Work{
		int weight;
		int work;
	
		public Work(int work, int weight) {
			this.work = work;
			this.weight = weight;
		}
	}

}