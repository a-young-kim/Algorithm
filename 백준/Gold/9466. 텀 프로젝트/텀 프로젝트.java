import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 문제 - 텀 포로젝트
 * 1. 프로젝트 팀 수에는 제한이 없다. 
 * 2. 모든 학생들이 동일한 팀의 팀원인 경우 한 팀만 있을 수도 있다. 
 * 3. 모든 학생들은 프로젝트를 함께하고 싶은 학생을 한명 선택
 * 4. 혼자 하고 싶은 경우 자기 자신 선택 가능
 * 
 * 5. 사이클이 존재해야 하나의 팀이 된다. 
 * 6. 어느 프로젝트 팀에도 속하지 않는 학생들의 수를 계산하는 프로그램 작성
 * 
 * 입력
 * 1. 테스트케이스의 개수  T
 * 2. 학생 수 n 10만 이하
 * 3. 선택된 학생들의 번호 1부터 n 까지
 * 
 * 출력
 * 케이스마다 한줄씩 프로젝트 팀에 속하지 못한 학생 수
 * 
 * 풀이
 * 노드의 차수를 이용해서 풀어 보기
 * 
 */
public class Main {
	static int[] array;
	static int[] arrayEdge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 0; test_case < T; test_case++) {
			st= new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			array = new int[N + 1];
			arrayEdge = new int[N + 1];
			
			st= new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				arrayEdge[array[i]] ++;
			}
			
			
			boolean[] visited = new boolean[N + 1];
			Queue<Integer> queue = new ArrayDeque<>();
			for(int i = 1; i < N + 1; i++) {
				if(arrayEdge[i] == 0) queue.add(i);
			}
			
			while(!queue.isEmpty()) {
				int node = queue.poll();
				
				visited[node] = true;
				if(array[node] != node) arrayEdge[array[node]] --;
				
				if(visited[array[node]]) continue;
				if(arrayEdge[array[node]] == 0) queue.add(array[node]);
			}

			int answer = 0;
			for(int i = 1; i < N + 1; i++) {
				if(arrayEdge[i] != 1) answer++;
			}
			System.out.println(answer);
		
		}
	}
	
	
}