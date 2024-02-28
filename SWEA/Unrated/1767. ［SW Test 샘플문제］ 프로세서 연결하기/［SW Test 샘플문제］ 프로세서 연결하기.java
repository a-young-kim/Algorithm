import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리: 24392
 * 실행시간: 3488
 */
/*
 * 문제
 * N * N
 * 한개의 셀에는 한개의 코어 혹은 전선
 * 가장자리에는 전원
 * 전선은 직선으로만 설치 가능, 겹치면 안됨
 * 
 * 초기 상태로 맵의 정보
 * 가장자리에 놓인 코어는 이미 전원이 연결된 것으로 간주
 * 최대한 많은 코어에 전원을 연결하였을 경우 전선 길이의 합
 * 단 여러 방법이 있을 경우 전선 길이의 합이 최소가 되는 값을 구하라
 * 
 * 풀이
 * 현재 연결된 코어 개수 i
 * 연결해야할 코어 개수 j
 * j부터 하나씩 감소해가면서 조합 구하기
 * 
 */
public class Solution {
	
	static int N, coreCount, answer = Integer.MAX_VALUE, answerCore = Integer.MIN_VALUE;
	static int[][] map;
	static List<Node> list;
	static List<int[]> allCoreList;
	static Node[] myCore;
	static int[] coreDir, dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			 st = new StringTokenizer(br.readLine());
			 
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			list = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1 && (i != 0 || j != 0 || i != N - 1 || j != N - 1)) list.add(new Node(i, j));
					if(map[i][j] == 1) map[i][j] = -1;
				}
			}
			
			
			answer = Integer.MAX_VALUE;
			answerCore = Integer.MIN_VALUE;
			
			myCore = new Node[list.size()];
			coreDir = new int[list.size()];
			// 조합
			f(0, 0, 0);

			System.out.println("#" + test_case + " " + answer);
		}

	}
	
	public static int check(int cnt, boolean flag) {
		
		Node node = myCore[cnt];
		int dir = coreDir[cnt];
	
		int count = 0;
		for(int j = 1; j < 13; j++) {
			int nr = node.row + dy[dir] * j;
			int nc = node.col + dx[dir] * j;
			
			if(nr < 0 || nr >= N) break;
			if(nc < 0 || nc >= N) break;
			
			// 넣기
			if(flag) {
				if(map[nr][nc] != 0) {
					count = -1;
					break;
				}
				map[nr][nc] = cnt;
			}
			
			else {
				if(map[nr][nc] == cnt) map[nr][nc] = 0;
				else break;
				
			}

			count++;
		}
		
		return count;
	}
	
	public static void f(int idx, int cnt, int sum) {
		
		if(cnt > answerCore) {
			answerCore = cnt;
			answer = sum;
		}
		
		else if (answerCore == cnt) answer = Math.min(answer,  sum);
		
		if(cnt == list.size()) return;
		if(idx == list.size()) return;
		
		for(int i = idx; i < list.size(); i++) {
			myCore[cnt] = list.get(i);
			for(int j = 0;j < 4; j++) {
				coreDir[cnt] = j;
				int count = check(cnt, true);
				if(count == -1) {
					check(cnt, false);
					continue;
				}
				f(i + 1, cnt + 1, sum + count);
				check(cnt, false);
			}
		}
	}
	
	static class Node{
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}

}