import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 하나의 기타로 모든 곡을 연주할 수 없다. 
 * 2. 최대한 많은 곡을 연주하려고 할 때, 필요한 기타의 최소 개수를 구하는 프로그램
 * 
 * 입력
 * 1. 기타의 개수 N(10이하), 곡의 개수 M(50이하)
 * 2. 각 기타의 이름과 연주할 수 있는 곡의 정보 -> Y이 가능, N으 ㄴ불가능 
 * 3. 기타의 이름은 50이하
 * 4. 기타의 이름이 같은 경우는 없음
 * 
 * 출력
 * 기타의 최소 개수, 연주할 수 있는 곡이 없으면 -1
 * 
 * 풀이
 * 1. N, M입력
 * 2. String[] names, int[] values 생성
 * 3. values에서 비트마스킹으로 저장 --> 최대 2의 50승이므로 비트 마스킹 안됨
 * 4. 1부터 N까지 조합 f를 돌린다. -> 모드
 * 5. 최소값 출력
 * 
 * 조합 메소드 f(int cnt, int idx, int max, int value)
 * 1. cnt == max이면 
 * 	1.1 value가 answerV보다 크면 answerV와 answerCnt교체
 * 2. for i가 inx 부터 N까지
 */
public class Main {
	
	static int N, answerG = -1, answerS;
	static String[] names;
	static boolean[][] values;
	static int[] save;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N =Integer.parseInt(st.nextToken());
		names = new String[N];
		values = new boolean[N][];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			names[i] = st.nextToken();
			String s = st.nextToken();
			if(i == 0) 	{
				values = new boolean[N][s.length()];
				save = new int[s.length()];
			}
		
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == 'Y') values[i][j] = true;
				else  values[i][j] = false;
			}
		}


		f(0, 0);
		System.out.println(answerG);
	}
	
	public static void f(int idx, int cnt) {
		
		int count = 0;
		for(int i = 0; i < save.length; i++) {
			if(save[i] == 0) continue;
			count++;
		}
		
		if(answerS < count) {
			answerS = count;
			answerG = cnt;
		}
		
		else if(answerS == count && answerG > cnt) answerG = cnt;
		
		for(int i = idx; i < N; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(values[i][j] == false) continue;
				save[j] ++;
			}
			f(i + 1, cnt + 1);
			for(int j = 0; j < values[0].length; j++) {
				if(values[i][j] == false) continue;
				save[j] --;
			}

		}
	}

}