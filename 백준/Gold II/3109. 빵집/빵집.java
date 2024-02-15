import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 지출을 줄이고자 가스비가 가장 크다는 것을 알고 근처 빵집 가스관에 몰래 
 * 파이프를 설치해 훔쳐서 사용하기로 했다.
 * 빵집의 있는 곳은 R * C 격자로 표현 가능
 * 첫째 열은 근처 빵집의 가스관이고 마지막 열은 원웅이의 빵집
 * 
 * 가스관과 빵집을 연결하는 파이프 설치한다. 건물이 있으면 설치 불과
 * 모든 파이프라인은 첫째열에서 시작해야하고 마지막 열에서 끝나야한다. 
 * 각칸은 오른쪽 오른쪽 위대각선, 오른쪽 아래 대각선으로 연결할 수 있고 각 칸의 중심끼리 연결
 * 가능한 많은 가스를 훔치려고 한다.
 * 따라서 여러개의 파이프라인을 설치할 것이다.
 * 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하는 프로그램을 작성
 * 
 * 입력
 * 첫째 줄 R과 C
 * R개의 빵집 근처의 모습 주어집
 * .빈칸, x는 건물
 * 처음과 마지막 열은 항상 비어있다.
 * 
 * 원웅이가 놓을 수 있는 파이프라인의 최대 개수
 * 
 * 풀이
 * 그리디
 */
public class Main {
	static int R, C, answer = 0;
	static char[][] array;
	static boolean[][] visited;
	static List<int[]> list = new ArrayList<>(); 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0;j < C; j++) {
				array[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			if(array[i][0] == 'x') continue;
			visited[i][0] = true;
			f(i, 0, false);
		}
		System.out.println(answer);
	}
	
	public static boolean f(int row, int col, boolean flag) {
		if(flag) return flag;
		if(array[row][col] == 'x') return false;
		if(col == C - 1) {
			answer++;
			return true;
		}

		if(!flag && row - 1 >= 0 && !visited[row - 1][col + 1] && array[row - 1][col + 1] == '.') {
			visited[row - 1][col + 1] = true;
			flag = f(row - 1, col + 1, flag);
		}
		
		if(!flag && !visited[row][col + 1] && array[row][col + 1] == '.') {
			visited[row][col + 1] = true;
			flag = f(row, col + 1, flag);
			
		}
		
		if(!flag && row + 1 < R && !visited[row + 1][col + 1] && col + 1 < C && array[row + 1][col + 1] == '.') {
			visited[row + 1][col + 1] = true;
			flag = f(row + 1, col + 1, flag);
		}
		return flag;
	}
}