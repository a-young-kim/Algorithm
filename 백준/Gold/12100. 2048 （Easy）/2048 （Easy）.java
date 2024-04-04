import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 전체 블록을 상하좌우 네 방향 중 하나로 이동
 * 2. 이 때 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다. 
 * 3. 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 었다. 
 * 4. 보드의 크기는 N * N 디고 보드의 크기와 보드판의 블록 상태가 주어졌을 때 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값
 * 
 * 입력
 * 1. N은 20 이하
 * 2. 게임판의 초기 상태 입력 -> 0은 빈칸 이외의 값은 블록
 * 3. 블록의 수는 2이상 1024 이하, 2의 제곱꼴
 * 
 * 출력
 * 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력
 * 
 * 아이디어
 * 완전 탐색 -> 모든 경우의 수를 구함
 * 
 * 풀이
 * 1. 보드의 크기 N 입력
 * 2. int[6][N][N] map 생성후 map[0][N][N]에 저장
 * 3. int[5] order 생성
 * 4. 중복 순열 구하는 함수 f 실행
 * 
 * f(int cnt)
 * 1. cnt == 5이면 
 * 2. for(int i = 0; i < 5; i++) 실행
 * 3. order[cnt] = i;
 * 4. moveMap 실행
 * 5. f(cnt + 1); 실행
 * 
 * moveMap(int cnt) 
 * 1. int order[cnt] 확인
 * 2. 상하좌우에 따라 map 이동
 * 3. for(int col = 0; col < N; col++) --> 아래로 이동할 경우
 * 4. List<Integer> list 생성
 * 5. for(int row = N -1 ; row)
 * 6. if(map[row][col] == 0이면 continue;
 * 7. 아니면 queue에 넣기
 * 8. row가 끝나면 map에 저장하는 함수  실행
 * 8.1. boolean combine = false, int cnt = 
 * 8.2. for(int i = 0; i < list.size() - )까지
 * 8.3. list.get(i) == list.get(i + 1) 이면 false
 * 8.4 저장
 */

public class Main {
	
	static int N, answer;
	static int[][][] maps;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		maps = new int[6][N][N];
		
		for(int row = 0; row < N; row ++) {
			 st = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col ++) {
				maps[0][row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		f(0);
		System.out.println(answer);
	}
	
	public static void f(int cnt) {
		if(cnt == 5) {
			check();
			return;
		}
		
		// 0123 : 상하좌우
		for(int i = 0; i < 4; i ++) {
			moveMap(cnt, i);
				
			f(cnt + 1);
		}
	}
	
	public static void printAll() {
		for(int i = 0; i < 6; i++) {
			System.out.println(i);
			for(int j = 0; j < N; j++) {
				System.out.println(Arrays.toString(maps[i][j]));
			}
			System.out.println();
		}
	}
	
	public static void print(int cnt) {
		System.out.println(cnt);
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(maps[cnt][i]));
		}
		System.out.println();
	}
	
	public static void check() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(maps[5][i][j] > answer) {
					answer = maps[5][i][j];
				}
			}
		}
	}
	
	public static void moveMap(int cnt, int order) {
		
		switch(order) {
		case 0:{
			//상
			moveTop(cnt);
			break;
		}
		case 1:{
			//하
			moveBottom(cnt);
			break;
		}
		case 2:{
			moveLeft(cnt);
			//좌
			break;
		}
		case 3:{
			//우
			moveRight(cnt);
			break;
		}
		default: break;
		}
	}
	
	public static void moveRight(int cnt) {
		
		for(int row = 0; row < N; row ++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int col = N - 1; col >= 0; col --) {
				if(maps[cnt][row][col] == 0) continue;
				list.add(maps[cnt][row][col]);
			}
			
			int idx = N - 1;
			
			for(int i = 0; i < list.size(); i++) {
				if(i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					maps[cnt + 1][row][idx--] = list.get(i) * 2;
					i++;
					
				}
				else {
					maps[cnt + 1][row][idx--] = list.get(i);
				}
			}
			
			while(idx >= 0) {
				maps[cnt + 1][row][idx--] = 0;
			}
		}
	}
	
	public static void moveLeft(int cnt) {
		
		for(int row = 0; row < N; row ++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int col = 0; col < N; col ++) {
				if(maps[cnt][row][col] == 0) continue;
				list.add(maps[cnt][row][col]);
			}
			
			int idx = 0;
			
			for(int i = 0; i < list.size(); i++) {
				if(i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					maps[cnt + 1][row][idx++] = list.get(i) * 2;
					i ++;
				}
				else {
					maps[cnt + 1][row][idx++] = list.get(i);
				}
			}
			
			while(idx < N) {
				maps[cnt + 1][row][idx++] = 0;
			}
		}
	}
	
	public static void moveBottom(int cnt) {
		
		for(int col = 0; col < N; col ++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int row = N - 1; row >= 0; row --) {
				if(maps[cnt][row][col] == 0) continue;
				list.add(maps[cnt][row][col]);
			}
			
			int idx = N - 1;
			
			for(int i = 0; i < list.size(); i++) {
				if(i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					maps[cnt + 1][idx--][col] = list.get(i) * 2;
					i++;
				}
				else {
					maps[cnt + 1][idx--][col] = list.get(i);
				}
			}
			
			while(idx >= 0) {
				maps[cnt + 1][idx--][col] = 0;
			}
		}
	}
	
	public static void moveTop(int cnt) {
		
		for(int col = 0; col < N; col ++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int row = 0; row < N; row ++) {
				if(maps[cnt][row][col] == 0) continue;
				list.add(maps[cnt][row][col]);
			}
			
			int idx = 0;
			
			for(int i = 0; i < list.size(); i++) {
				if(i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					maps[cnt + 1][idx++][col] = list.get(i) * 2;
					i ++;
				}
				else {
					maps[cnt + 1][idx++][col] = list.get(i);
				}
			}
			
			while(idx < N) {
				maps[cnt + 1][idx++][col] = 0;
			}

		}
	}

}