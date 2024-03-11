import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. N개의 빌딩
 * 2. i 번째 키가 hi이고, 모든 빌딩은 일렬로 서 있고 오른쪽으로만 볼 수 있다. 
 * 	즉 i번째 빌딩 관리인이 볼 수 있는 다른 빌딩 정원은 i+1, N이다. 
 * 3. 그런데 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음에 있는 모든 빌딩의 옥상은 보지 못한다. 
 * 
 * 
 * 입력
 * 1. 빌딩의 개수 N 8만 이하
 * 2. 두번쨰 줄 부털 N + 1까지 각 빌딩의 높이 10억이하
 * 
 * 출력
 * 각 관리인이 벤치 마킹 할 수 있는 빌딩 수의 합
 * 
 * 풀이
 * 1. N 입력, array배열 입력
 * 2. 앞에서부터 찾기
 * 3. start가 end보다 크면 (start, end + 1), (start + 1, end + 1)을 통해 이후 찾기
 * 4. start가 end보다 작거나 같으면 (end, end + 1) 로 end가 접근할 수 있는 것 찾기

 * 
 * 시간 복잡도 
 */
public class Main {
	
	static int N;
    static long answer;
	static int[] array;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		array = new int[N + 1];
		for(int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 array[i] = Integer.parseInt(st.nextToken());
		}
		array[N] = Integer.MAX_VALUE;
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < N + 1; i++) {
			while(!stack.isEmpty()) {
				Integer idx = stack.pollLast();
				if(array[idx] > array[i]) {
					stack.add(idx);
					break;
				}
				else {
					answer += i - idx - 1;
				}
				
			}
			
			stack.add(i);
		}
		System.out.println(answer);
		
	}
}