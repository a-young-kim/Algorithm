import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 정사각형 구역 안에 K개의 미생물 군집
 * 구역은 N * N 동일한 크기의 정사각형 셀
 * 미생물은 구역을 법어나지 않음
 * 
 * 1. 치초 미생물의 군집의 위치와 군집 내 미생물 수, 이동 방향, 약품이 칠해진 부분에는 미생물 없음 이동 방향은 상하좌우
 * 2. 간 군집들은 1시간 마다 이동 방향이 있는 다음 셀로 이동
 * 3. 약품이 칠해진 셀에 도착하면 군집내의 미생물의 절반이 죽도 이동 방향이 반댈 바뀌다. 
 * 	홀 수 있경우 나눈 후 버림
 * 4. 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군비을 합친다. 합쳐진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 
 * 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동 방향
 * 
 * M 시간 동안 미생물의 군집을 격리할 때 M 시간 후 남아있는 미생물의 총합
 * 
 * 제약사항
 * N은 100이하의 정수
 * K는 1000이하의 정수
 * M은 1000이하의 정수
 * 미생물 수는 10000이하의 정수
 * 군집의 이동 방향은 상하좌우
 * 위치는 0번 부터 시작
 * 
 * 입력
 * 테스트 케이스 T
 * 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K가 순서대로 주어지며 다음 K줄에 걸쳐서 미생물 군집 K개의 정보
 * 미생물 군집의 정보는 세로 위치 가로 위치 미생물 수 이동방향 순으로 4개의 정수가 주어진다. 
 * 
 * 출격
 * #테스트케이스번호      M시간후 남아있는 미새물 수의 총합.
 * 
 * 풀이 
 * bfs level 별 탐색
 */
public class Solution {
	
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
		
			Queue<Node> queue = new ArrayDeque<>();
			int[][] map = new int[N][N];
			for(int i = 0;i < N; i++) {
				Arrays.fill(map[i],  -1);
			}
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				queue.add(new Node(count, row, col, dir - 1));
			}
			
			int size = queue.size();
			int level = 0;
			while(!queue.isEmpty()) {
				if(size == 0) {

					boolean[][] visited = new boolean[N][N];
				
					queue = union(visited, queue);
					size = queue.size();
					level ++;
				}
				
				if(M == level) break;
				Node node = queue.poll();
				
				node.row = node.row + dy[node.dir];
				node.col = node.col + dx[node.dir];
				
				size --;
				if(node.row * node.col == 0 || node.row == N - 1 || node.col == N - 1 ) {
					node.count = node.count / 2;
					if(node.count == 0) continue;
					
					if(node.dir == 0) node.dir = 1;
					else if(node.dir == 1) node.dir = 0;
					else if(node.dir == 2) node.dir = 3;
					else if(node.dir == 3) node.dir = 2;
				}

				queue.add(node);
			}
			
			int answer = 0;
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				answer += node.count;
			}
			System.out.println("#" + test_case + " " + answer);
		}

	}
	
	public static Queue<Node> union(boolean[][] visited, Queue<Node> queue) {
		
		Queue<Node> newQueue = new ArrayDeque<>();
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			Node maxNode = node;
			int count = node.count;
			int size = queue.size();
			while(size != 0) {
				Node node2 = queue.poll();
				
				if(node2.col == node.col && node.row == node2.row) {
					if(node2.count > maxNode.count) maxNode = node2;
					count += node2.count;
				}
				else queue.add(node2);
				size --;
			}
			
			maxNode.count = count;
			newQueue.add(maxNode);
		}

		return newQueue;
	}
	
	public static class Node{
		int count;
		int row;
		int col;
		int dir;
		
		public Node(int count, int row, int col, int dir) {
			this.col = col;
			this.count = count;
			this.row = row;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "row: " + row + " col: " + col + " count: " + count + " dir: " + dir + "\n";
		}
	}

}