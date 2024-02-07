import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 문제
 * N과 N 형태
 * 위에 i번째 줄의 외쪽에서 j번쨰 방에 1이상 N ^ 2 의 숫자가 적혀 있으며 
 * 모든 방에 대해 서로 다르다
 * 
 * 특정 방에 있다면 상하좌우에 있는 다른 방으로 이동 가능
 * 단 이동하려는 방이 존재하고 있어야하고 이동하려는 방에 적힌 숫자가 펴재 방에 적힌 숫자보다 정확히 1 더 커야한다. 
 * 처음 어떤 수가 적힌 방에 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성
 * 
 * 입력
 * 첫번쨰 줄에 테스트 케이스 수 T
 * 각 테스트 케이스의 첫번쨰 줄에는 하나의 정수 N이 주어짐
 * 다음 N개의 줄에는 N개의  정수
 * 
 * 출력
 * #테스트케이스번호 처음출발해야하는방번호, 최대 몇개의 방을 이동할 수 있는지
 * 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에 적힌 수가 가장 작은 것을 출력
 * 
 * 예제 풀이
 * bfs를 이용해서 풀어야할 듯
 * visited 사용
 * 
 */
public class Solution {

	static int[][] array;
	static boolean[][] visited;
	static int N, cnt, answerNum = Integer.MAX_VALUE, answerVisited = Integer.MIN_VALUE;
	static HashMap<Integer, Integer[]> hashMap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case < T + 1; test_case ++) {
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			visited = new boolean[N][N];
			hashMap = new HashMap<>();
			
			answerNum = Integer.MAX_VALUE;
			answerVisited = Integer.MIN_VALUE;
			
			StringTokenizer st;
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N ; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
					hashMap.put(array[i][j],  new Integer[] {i, j});
				}
			}
			
			for(int i = 1 ; i < N * N + 1 ; i++) {
				if(cnt == N * N + 1) break;
				Integer[] index = hashMap.get(i);
				if(visited[index[0]][index[1]]) continue;
				int visitedNum = dfs(index, 1);
				
				if(visitedNum > answerVisited) {
					answerVisited = visitedNum;
					answerNum = i;
				}
				else if(visitedNum == answerVisited) {
					answerNum = Math.min(i, answerNum);
				}
			}
			
			System.out.println("#" + test_case + " " +answerNum + " " + answerVisited);
			
		}
		
	}
	
	public static int dfs(Integer[] start, int visitedNum) {
		
		int row = start[0];
		int col = start[1];
		int nextNum = array[row][col] + 1;
		cnt ++;
		// 왼쪽
		if(col - 1 >= 0 && array[row][col - 1] == nextNum) {
	
			visitedNum = dfs(hashMap.get(array[row][col - 1]), visitedNum + 1);
		}
		//오른쪽
		else if(col + 1 < N && array[row][col + 1] == nextNum) {
			visitedNum = dfs(hashMap.get(array[row][col + 1]), visitedNum + 1);
		}
		//위
		else if(row - 1 >= 0 && array[row - 1][col] == nextNum) {
			visitedNum = dfs(hashMap.get(array[row - 1][col]), visitedNum + 1);
		}
		//아래
		else if(row + 1 < N && array[row + 1][col] == nextNum) {
			visitedNum = dfs(hashMap.get(array[row + 1][col]), visitedNum + 1);
		}
		else {
			return visitedNum;
		}
		return visitedNum;
	}

}