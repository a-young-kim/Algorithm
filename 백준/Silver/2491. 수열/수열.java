import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		int N = sc.nextInt();
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		int answer = 0;
		Stack<Integer> queue = new Stack<Integer>();
		// 연속해서 커지는 것 탐색
		int cnt = 0;
		while(cnt < N) {

			if(queue.size() != 0 && queue.peek() > nums[cnt]) {
				answer = Math.max(answer, queue.size());
				queue = new Stack<Integer>();
			}
			queue.add(nums[cnt]);
			cnt++;
		}
		answer = Math.max(answer, queue.size());
		// 연속해서 작아지는 것 탐색
		cnt = 0;
		queue = new Stack<Integer>();
		while(cnt < N) {

			if(queue.size() > 0 && queue.peek() < nums[cnt]) {
				answer = Math.max(answer, queue.size());
				queue = new Stack<Integer>();
			}
			queue.add(nums[cnt]);
			cnt++;
		}
		answer = Math.max(answer, queue.size());
		System.out.println(answer);
		sc.close();
		
	}

}
