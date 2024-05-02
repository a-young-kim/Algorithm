/*
 * 문제
 * 1. R * C 격자, 각 칸에는 상어가 최대 한 마리 존재, 상어는 크기와 속도
 * 2. 낚시왕은 처음에 1번 열의 한 칸 왼쪽에 위치(0, 0)
 * 3. 1초 동안 일어나는 일
 * 	3.1 오른쪽을 한칸 이동 --> 가장 오른쪽 칸으로 이동하면 이동을 멈춘다
 * 	3.2 낚시 왕이 있는 열에 있는 상어 중 땅에서 제일 가까운 상어를 잡는다
 * 	3.3 상어가 이동
 * 4. 상어의 이동방향은 다음과 같다
 * 	4.1 상어가 보고 있는 방향이 속도의 방향, 왼쪽 아래에 적힌 정수는 속력이다
 * 5. 상어가 이동을 바친 후 한카에 상어가 두마리 있을 수 있다. 이때 크기가 큰 상어가 작은 상어를 잡아먹는다. 
 * 6. 낚시 왕이 잡은 상어 크기의 합을 구하여라
 * 
 * 입력
 * 1. 격자판의 크기 R, C 상어의 수 M
 * 2. M개의 상어의 정보 주어짐
 * 	2.1 r, c, s, d, z 로 각각 위치 속력 이동 방향 크기(1: 위, 2: 아래, 3:오른쪽, 4:왼쪽)
 * 3. 같은 크기의 상어는 없다. 
 * 
 * 풀이과정
 * 1. R, C, M 입력
 * 2. int[R][C] map 생성 후 입력 받음
 * 2. class Node 생성
 * 	2.1 s, d, z
 * 3. for M
 * 3.1 Node[R][C] map 생성 후 입력 받음
 * 4. for C만큼 for문을 돌면서 가장 땅에서 가까운 상어을 찾는다. 
 * 	4.1 for R만큼 [j][i] != null인 것 answer += node.s
 * 5. 탐색을 돌면서 상어를 이동 시킨다. 
 * 6. answer 출력
 * 
 * 탐색
 * 1.Node[][] newMap 생성
 * 2. for(R) for C를 돌면서 상어 이동
 * 3. 도착 위치에 상어가 존재하면 크기 비교를 통해 하나만 선택 
 * 
 */

import java.util.*;
import java.io.*;

public class Main {

	static Node[][] map;
	static int R, C, M;
	static int[] dx = {0, 0, 1, -1}, dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Node[R + 1][C + 1];
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Node(s, d, z);
		}
		
		int answer = 0;
		for(int i = 1; i < C + 1; i++) {
			
			for(int j = 1; j < R + 1; j++) {
				if(map[j][i] == null) continue;
				
				answer += map[j][i].z;
				map[j][i] = null;
				break;
			}
			f();
		}
		System.out.println(answer);
	}
	
	public static void f() {
		
		Node[][] map2 = new Node[R + 1][C + 1];
		for(int i = 1; i < R + 1; i++) {
			for(int j = 1; j < C + 1; j++) {
				if(map[i][j] == null) continue;
				
				Node node = map[i][j];
				int nr = i, nc = j;
				
				for(int k = 0; k < node.s; k++) {					
					if(nr + dy[node.d] <= 0 || nr + dy[node.d] >= R + 1) {
						node.d = f2(node.d);
					}
					
					if(nc + dx[node.d] <= 0 || nc + dx[node.d] >= C + 1) {
						node.d = f2(node.d);
					}
					
					nr += dy[node.d];
					nc += dx[node.d];
				}
				
				if(map2[nr][nc] == null) map2[nr][nc] = node;
				else {
					if(map2[nr][nc].z < node.z) map2[nr][nc] = node;
				}
			}
		}
		map = map2;
	}
	
	public static int f2(int num) {
		switch(num) {
		case 0: return 1;
		case 1: return 0;
		case 2: return 3;
		case 3: return 2;
		}
		return -1;
	}
	
	public static class Node{
		int s, d, z;
		
		
		public Node(int s, int d, int z) {
			super();
			this.s = s;
			this.d = d;
			this.z = z;
		}


		@Override
		public String toString() {
			return "Node [s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
		
	}
}
