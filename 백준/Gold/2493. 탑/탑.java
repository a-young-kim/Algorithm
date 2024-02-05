import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제
 * N개의 높이가 서로 다른 탑을 수평 직선 왼쪽부터 오른쪽 방향으로 차례로 세우고, 꼭대기에 송신기 설치
 * 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선 왼쪽 방향으로 발사, 
 * 탑의 기둥 모두에는 레이저 신호를 수신하는 장치 설치
 * 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 탑에서만 수신 가능
 * 
 * 6, 9, 5, 7, 4가 있을 때 높이가 6과 9인 탑의 수신은 아무도 받지 못한다.
 * 
 * 탑들의 개수N과 탑들의 높이가 주어질때, 각각의 탐에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램 작성
 * 
 * 입력
 * 첫째줄 탑의수 N N은 1 이상 500,000 이하
 * 둘째 줄 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어짐 탑들의 높이 1 이상 1억 이하의 정수
 *
 *출력
 *첫째줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 두고 출력
 *레이저 신호를 수신하는 탑이 존재하지 않으면 0 츨력
 *
 *문제 풀이 방식
 *해당 탑 이전의 탑들의 높이를 저장한 Map 높이, index
 *queue에 저장하여 하나씩 추출
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> stackIndex = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			stack.add(Integer.parseInt(st.nextToken()));
			stackIndex.add(i + 1);
		}
		
		
		
		int[] answer = new int[N];
		int qSize = stack.size();
		Stack<Integer> newStack = new Stack<>();
		Stack<Integer> newIndex = new Stack<>();
		
		while(stack.size() != 1) {
			Integer last = stack.pop();
			Integer lastIndex = stackIndex.pop();
			
			if(last <= stack.peek()) {
				answer[lastIndex - 1] = stackIndex.peek();
				while(newStack.size() != 0) {
					Integer last2 = newStack.peek();
					Integer lastIndex2 = newIndex.peek();
					
					if(last2 <= stack.peek()) {
						answer[lastIndex2 - 1] = stackIndex.peek();
						newStack.pop();
						newIndex.pop();
					}
					else break;
				}
			}
			else {
				newStack.add(last);
				newIndex.add(lastIndex);
			}
		}
		
		for(int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}