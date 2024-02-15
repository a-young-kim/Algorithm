import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 프린터는 다음과 같은 조건에 따라 인쇄한다.
 * 1. 가장 앞이 있는 문서의 중요도를 확인한다.
 * 2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있으면 이 문서를 인쇄하지 않도
 *  queue의 가장 뒤에 재배치한다.
 *  
 *  현재 queue에 있눈 문서의 수와 중요도가 주어졌을 때 어떤 한 문서가 몇 번째로 인쇄하는치 알아내는 것이다.
 *  
 *  입력
 *  테스트 수
 *  문서의 개수 N, 궁금한 문서의 순서 M
 *  제일 왼쪽음 0번째부터
 *  Nro개의 문서가 차례로 주어짐 중요도는 1이상 9이하의 정수
 *  
 *  해결방안
 *  중요도가 적힌 queue와 문서의 번호 queue를 만들고
 *  중요도가 적힌 순서를 prior queue에 저장
 *  while문을 돌면서 궁금한 문서가 나올때까지
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Queue<Integer> indexQ = new ArrayDeque<Integer>();
			Queue<Integer> importanceQ = new ArrayDeque<Integer>();
			PriorityQueue<Integer> priQ = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2 - o1;
				}
			});
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				indexQ.add(j);
				importanceQ.add(n);
				priQ.add(n);
			}
			
			int cnt = 0;
			while(!indexQ.isEmpty()) {
				
				if(priQ.peek() == importanceQ.peek()) {
					cnt++;
					priQ.poll();
					importanceQ.poll();
					if(M == indexQ.poll()) break;
					continue;
				}
				
				int iQ = importanceQ.poll();
				int idexQ = indexQ.poll();
				indexQ.add(idexQ);
				importanceQ.add(iQ);
			}
			System.out.println(cnt);
		}
	}
}