/*
 * 메모리: 14828
 * 시간:124
 */
/*
 * 문제
 * 1. 상근이네 집에서 출발, 맥주 한 박스(20개)
 * 2. 50미터에 한 병씩 마신다. 50미터 가기전에 한병씩
 * 3. 편의점에 들렸을 때, 빈 병은 버리고 새 맥주를 산다. --> 단 박스의 맥주는 최대 20
 * 4. 편의점을 나가고 나서도 50미터 가기전에 한명
 * 5. 편의점, 상근이네 집, 도착지가 좌표로 주어질 때 도착지에 도착할 수 있는지
 * 
 * 입력
 * 1. 테스트 개수 T 50이하
 * 2. 편의점 개수 n 100 이하
 * 3. n + 2개 줄에 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표
 * 4. 두 좌표 사이의 거리는 x 좌표 차이 + 좌표 차이
 * 
 * 출력
 * 도착할 수 있으면 "happy"
 * 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad" 출력
 * 
 * 아이디어
 * 
 * 
 * 풀이
 * 1. 테스트 케이스 개수 t 입력
 * 2. int[] start, int[][] convi 생성  -32768 부터  
 * 3. start 위치는 int[], end 위치 int[] 저장 convi 위치 저장 int[] --> 각 노드 사이의 모든 거리 저장 간선 배열 리스트 저장
 * 4. class Node 생성 row, col
 * 5. bfs 탐색
 * 5.1 queue.poll()
 * 5.2 for convi 편의점 개수 만큼
 * 5.3 거리가 1000이하이면  queue에 넣기 --> visited 처리
 * 5.4 end node를 만나면 happy 출력 후 return
 * 6. sad 출력
 * 
 * 
 * 시간복잡도
 * 100 * 100 = 10000
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc= 0; tc<T; tc++) {
            
            int N = Integer.parseInt(br.readLine());
            Queue<Node> queue = new ArrayDeque<>();
            
            Node[] convi = new Node[N + 2];
            for(int i=0; i<N+2; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                if(i == 0) queue.add(new Node(i, X, Y));
                convi[i] = new Node(i, X, Y);
            }
            
            
            boolean[] visited = new boolean[N + 2];
            visited[0] = true;
            
            boolean flag = false;
            first: while(!queue.isEmpty()) {
            	Node node = queue.poll();
            	
            	for(int i = 0; i < N + 2; i++) {
            		if(visited[i]) continue;
            		if(Math.abs(convi[i].row - convi[node.node].row) + Math.abs(convi[i].col - convi[node.node].col) > 1000) continue;
            		
            		if(i == N + 1) {
            			flag = true;
            			break first;
            		}
            		queue.add(new Node(i, convi[i].row, convi[i].col));
            		visited[i] = true;
            	}
            }
            if(flag) System.out.println("happy");
            else System.out.println("sad");
        }
	}
	
	public static class Node{
		int node;
		int row;
		int col;
		
		
		
		public Node(int node, int row, int col) {
			super();
			this.node = node;
			this.row = row;
			this.col = col;
		}



		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}
		
	}

}