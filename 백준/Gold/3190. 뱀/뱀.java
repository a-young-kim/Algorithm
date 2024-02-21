import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 뱀이 나오서 기어 다니는데 사과를 먹으면 뱀의 길이가 늘어난다.
 * 뱀이 자기 자신 혹은 벽과 부딪히면 게임은 끝난다.
 * 
 * 게임은 N * N의 정사각형 보드 위에서 진행되고 몇몇 칸에는 사ㅗ가가 놓여져 있다.
 * 보드의 상화좌우 끝에는 벽이 있다.
 * 게임은 N * N 정사각형 보드 위에서 진행되고 0, 0에서 위치 뱀의 길이는 1, 처음에 뱀은 오론쪽을 향한다.
 * 
 * 1. 맵은 몸길이를 늘려 머리를 다음칸에 위치 시키다. 
 * 2. 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다. 
 * 3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다. 
 * 4. 만약 이동한 칸에 사과가 없다면 몸 길이를 줄여서 꼬리가 위치한 칸을 비워준다.  즉 몸 길이는 변하지 않는다.
 * 
 * 입력
 * 보드의 크기 N 2이상100이하
 * 사과의 개수 K 100이하
 * 사과의 위치는 모두 다르며 첫 시작에서는 사과가 없다.
 * 뱀의 방향 변환 회수 L가 주어짐 L은 100이하
 * 정수 X와 문자 C 게임 시작 시간으로 부터  X초가 끝난 뒤에 왼쪽(L)과 오른쪽(D)로 90방향으로 회전
 * X는 만 이하의 양의 정수이면 
 * 방향 전환 정보는 X가 증가하는 순으로 주어짐
 * 
 * 출력
 * 게임이 몇 초에 끝난는지 출력
 * 
 * 풀이
 * 위치를 queue에 저장
 * node 클래스를 만들어서 해보자
 * 
 */
public class Main {
	
	static int N, K, L;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			// 사과 표시
			map[row][col] = 2;
		}
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());

		Queue<Command> queue = new ArrayDeque<>();
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			Command command = new Command(sec, c);
			queue.add(command);
		}
		
		LinkedList<Node> list = new LinkedList<>();
		Node node = new Node(0, 0);
		list.add(node);
		map[0][0] = 1;
		
		int flag = 0;
		int answer = 1;
		first: while(!list.isEmpty()) {
			node = list.get(list.size() - 1);
			int nextRow = node.row + dy[flag];
			int nextCol = node.col + dx[flag];
			
			if(nextRow < 0 || nextRow >= N) break;
			if(nextCol < 0 || nextCol >= N) break;
			switch(map[nextRow][nextCol]) {
			// 빈칸일 경우
			case 0:{
				Node n = new Node(nextRow, nextCol);
				map[nextRow][nextCol] = 1;
				list.add(n);
				Node n2 = list.poll();
				map[n2.row][n2.col] = 0;
				break;
			}
			//이미 지나갔을 경우
			case 1:{
				break first;
			}
			// 사과
			case 2:{
				Node n = new Node(nextRow, nextCol);
				map[nextRow][nextCol] = 1;
				list.add(n);
				break;
			}
			}
			Command command = queue.peek();
			if(command != null && command.sec == answer) {
				if(command.c == 'L') {
					flag--;
					flag = flag < 0 ? 3: flag;
				}
				else {
					flag ++;
					flag = flag % 4;
				}
				queue.poll();
			}
			
			answer++;
		}
		System.out.println(answer);
	}
	
	public static class Node{
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static class Command{
		int sec;
		char c;
		
		public Command(int sec, char c) {
			this.sec = sec;
			this.c = c;
		}
	}

}