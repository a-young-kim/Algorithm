import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 하는 게임
 * 하나의 이닝은 공격과 수비로 이루어져있고 
 * 총 N번 동안 게임을 진행
 * 한 이닝에 3 아웃이 발생하면 이닝 종료 후 두 팀의 공격 수비를 바꾼다. 
 * 
 * 두 팅ㅁ은 경기 시작 전까지 타순을 정하고 경기 중에는 타순 변경 불가
 * 9번타자까지 공을 쳤는테 3아웃이 발생 안하면 1번 타자사 슴
 * 2이닝에 6번 타자가 마지막 타자였다면 3이닝에서는 7번 부터 타석에ㅐ
 * 
 * 공격 팅믜 선수가 1루 2루 3루를 돌고 홈에 도착함녀 1점
 * 
 * 타자가 공을 쳐서 얻을 수 있는 결과
 * 
 * 안타: 모든 주자가 1루
 * 2루타: 모든 주자가 2루
 * 3루타: 모든 주자가 3루 질루
 * 홈런; 타자와 모든 주자가 홈까지 진루
 * 아웃: 모든 주자는 진루하지 못하고 공격 팀에 아웃이 하나 증가
 * 
 * 한 야구 팀의 감독이 타순ㄴ을 정하려고 한다. 
 * 1번 선순를 미리 4번 타자로 결정
 * 다른 선수의 타순을 결정 
 * 감독은 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다. 
 * 가장 많은 득점을 하는 타순을 찾고 그때의 득점을 구하자
 * 
 * 입력
 * 이닝 수 N
 * N개의 줄에 각 선수가 이닝에서 얻느 ㄴ결과가 순서애도
 * 이닝에서 얻는 결과는 9개의 정수
 * 1: 안타, 2: 2루타 3, 3루타, 홈런 4 아웃 0
 * 
 * 출력
 * 최대 점수
 * 
 * 풀이
 * dfs, 순열
 * 
 */
public class Main {
	
	static int N, answer = Integer.MIN_VALUE, count, runner;
	static int[][] array;
	static int[] order;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		array = new int[N][9];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j < 9; j++) {
				array[i][j ] = Integer.parseInt(st.nextToken());
			}
		}
		
		//순열
		order = new int[9];
		visited = new boolean[10];
		order[3] = 1;
		dfs(0);
		System.out.println(answer);
	}
	
	public static void dfs(int cnt) {
		if(cnt == 3) cnt ++;
		if(cnt == 9) {
			count = 0;
			runner = 0;
			for(int i = 0; i < N; i++) {
				play(i);
			}
			answer = Math.max(answer,  count);
			return;
		}
		
		for(int i = 1; i < 10; i++) {
			if(cnt == 3) cnt ++;
			if(i == 1) continue;
			if(visited[i]) continue;
			visited[i] = true;
			order[cnt] = i;
			dfs(cnt + 1);
			visited[i] = false;
		}
	}
	
	public static void play(int cnt) {
		
		int[] location = new int[3]; // 1루 2루 3루
		int out = 0;
		while(out != 3) {
			if(runner == 9) runner = 0;
			int run = order[runner];
			
			switch(array[cnt][run - 1]) {
			case 4:{
				for(int i = 0;i < 3; i++) {
					count += location[i];
					location[i] = 0;
				}
				count += 1;
				break;
			}
			case 3:{
				for(int i = 0;i < 3; i++) {
					count += location[i];
					location[i] = 0;
				}
				location[2] = 1;
				location[1] = 0;
				location[0] = 0;
				break;
			}
			case 2:{
				// 3루 2루 0으로 
				for(int i = 2; i > 0; i--) {
					count += location[i];
					location[i] = 0;
				}
				location[2] = location[0];
				location[1] = 1;
				location[0] = 0;
				break;
			}
			case 1:{
				// 3루으로 
				count += location[2];
				location[2] = location[1];
				location[1] = location[0];
				location[0] = 1;
				break;
			}
			case 0:{
				out++;
				break;
			}
			}
			runner++;
		}
	}

}