import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. R행 C열 지도
 * 2. 7가지 기본 블록
 * 	- 블록 '|'	블록 '-'	블록 '+'	블록 '1'	블록 '2'	블록 '3'	블록 '4'
 * 3. 가스는 양방향으로 흐를 수 있다. 
 * 4. 해커가 지운 블록의 위치와 종류를 구하여라
 * 
 * 입력
 * 1. 유럽의 크기 R, C 25이하
 * 2. R개 줄에 C개 글자
 * 	- 빈칸을 나타내는 '.'
	- 블록을 나타내는 '|'(아스키 124), '-','+','1','2','3','4'
	- 모스크바 M, 자그레브 Z
	
	3. 가스의 흐름이 유일한 경우만 입력으로 주어진다. 
	4. 모스크바와 자그레브는 블록과 인접해 있다. 
	5. 불필요한 입력이 없다. 
	
	풀이
	1. R, C 입력
	2. 상하좌우 순으로 1111 비트 마스킹에 따른 값은 hashmap에 입력
	3. Z, M부터 탐색하여 지운 곳으로 이동
	3.1 이동 방식
		- Z 주변을 탐색하여 빈칸이 아닌 공간을 찾음 이동 방향 ^ 해당 문자의 값을 하여 다음 이동 방향 탐색
		- 1000이면 상승, 0100 하락, 0010 왼쪽, 0001 오른쪽
		- 이동할 수 없을 경우 저장
	4.두 쪽 값이 모두 같은 경우 | 연산 ->  ^ 연산하여 값 확인
	 -> 사방 이 모두 있으면 +
	
 */
public class Main {
	
	static int R, C;
	static HashMap<Integer, Integer> block;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] idxArray = new int[2];
	
	static final int TB = 12;
	static final int LR = 3;
	static final int TBLR = 15;
	static final int BR = 5;
	static final int TR = 9;
	static final int TL = 10;
	static final int BL = 6;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		int[] zArray = new int[2];
		int[] mArray = new int[2];
		
		for(int i = 0 ;i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'M') {
					mArray[0] = i;
					mArray[1] = j;
				}
				
				else if(map[i][j] == 'Z') {
					zArray[0] = i;
					zArray[1] = j;
				}
			}
		}
		
		block = new HashMap<>();
		block.put((int) '|', TB);//12
		block.put((int) '-', LR);//3
		block.put((int) '+', TBLR);//15
		block.put((int) '1', BR);//5 0101
		block.put((int) '2', TR);//9
		block.put((int) '3', TL);//10
		block.put((int) '4', BL);//6
		
		int zDirect = getDirect(zArray);
		int blankZdirect = getBlanckDirect(zArray, zDirect);
		
		int mDirect = getDirect(mArray);
		int blankMdirect = getBlanckDirect(mArray, mDirect);
		
		int direct = (blankZdirect | blankMdirect);
		
		if(direct == TB || direct == LR) {}
		else if(direct == TBLR) {}
		else direct = TBLR ^ direct;
		
		char answer = 0;
		for(Integer key: block.keySet()) {
			Integer value = block.get(key);
			
			if(value != direct) continue;
			answer = (char) key.intValue();
		}
		
		System.out.println((idxArray[0] + 1) + " " + (idxArray[1] + 1) + " " + answer);
		
	}
	
	public static int getBlanckDirect(int[] array, int direct) {
		int row = array[0];
		int col = array[1];
		int myDirect = direct;

		while(true) {
			myDirect =  direct;
			int idx = -1;
			// 상 하 좌 우 
			while(direct != 0) {
				direct = direct >> 1;
				idx ++;
			}

			row = row + dy[3 - idx];
			col = col + dx[3 - idx];

			
			if(map[row][col] == '.') {
				idxArray[0] = row;
				idxArray[1] = col;
				int count = 0;
				int plusDirect = 0;
				// + 가 될 수 있는 지 확인
				for(int i = 0; i < 4; i++) {
					int nr = row + dy[i];
					int nc = col + dx[i];
					
					if(nr < 0 || nr >= R) continue;
					if(nc < 0 || nc >= C) continue;
					if(map[nr][nc] == '.' || map[nr][nc] == 'Z' || map[nr][nc] =='M') break;
					
					count++;
					plusDirect = plusDirect | block.get((int)map[nr][nc]);
					
				}
				
				if(count == 4) {
					return plusDirect;
				}
				
				return myDirect;
			}
			
			
			char c = map[row][col];
			int getDirect = block.get((int)c);
	
			
			/*
			 *  1 -> 우
			 *  2 -> 좌
			 *  4 -> 하
			 *  8 -> 상
			 */
			if(myDirect == 1) myDirect = 2;
			else if(myDirect == 2) myDirect = 1;
			else if(myDirect == 4) myDirect = 8;
			else if(myDirect == 8) myDirect = 4;
			

			direct = myDirect ^ getDirect;
			
			// + 일 경우
			if(direct == 1) {}
			else if(direct == 2) {}
			else if(direct == 4) {}
			else if(direct == 8){}
			else {
				if(myDirect == 1) direct = 2;
				else if(myDirect == 2) direct = 1;
				else if(myDirect == 4) direct = 8;
				else if(myDirect == 8) direct = 4;
			}

		}
	}
	
	public static int getDirect(int[] array) {
		
		int direct = 0;
		for(int i = 0; i < 4; i++) {
			// 상 하 좌 우 
			int nr = array[0] + dy[i];
			int nc = array[1] + dx[i];
			
			if(nr < 0 || nr >= R) continue;
			if(nc < 0 || nc >= C) continue;

			if(map[nr][nc] =='.' || map[nr][nc] == 'Z' || map[nr][nc] == 'M') continue;
			
			direct = 1 <<  (3 - i);
			
		}
		return direct;
	}

}