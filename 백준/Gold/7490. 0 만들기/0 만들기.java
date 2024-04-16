import java.io.*;
import java.util.*;

/*
 * 문제
 * 1. 1부터 N까지 오름차순 수열
 * 2. 숫자 사이에 +, -, 공백을 삽입하여 수식을 계산
 * 3. 그 결과가 0이 되는 것을 찾음
 * 
 * 입력
 * 1. 테스크 케이스 개수 10 이하
 * 2. N 입력
 * 
 * 출력
 * 각 테스트 케이스에 대해 아스키 순서에 따라 결과가 0이 되는 모든 수식을 출력
 * 
 * 아이디어
 * 순열
 * 
 * 풀이
 * 1. T입력
 * 2. for i가 0부터 T까지, N입력
 * 3. List<String> 생성, int save[N] 생성
 * 4. f(0, 0) 실행
 * 
 * void f(int cnt, int num)
 * 1. cnt == N 이면
 * 1.1 num == 0이면 save 
 */

public class Main {
	
	static int N;
	static int[] save, arr;
	static List<String> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N + 1];
			for(int i = 1; i < N + 1; i++) arr[i] = i;
			
			save = new int[N];
			list = new ArrayList<>();
			f(1);
			Collections.sort(list);
			for(String word: list) System.out.println(word);
			System.out.println();
		}
	}
	
	public static void f(int cnt) {
		if(cnt == N) {

			String word = "";
			String word2 = "";
			for(int i = 1; i < N; i++) {
				word += Integer.toString(arr[i]);
				word2 += Integer.toString(arr[i]);
				switch (save[i]) {
				case 1: // 합
					word += '+';
					word2 += " + ";
					break;
				case 2: // 차
					word += '-';
					word2 += " - ";
					break;
				case 3: // 붙이기
					word += ' ';
					break;
				}
			}

			word += Integer.toString(arr[N]);
			word2 += Integer.toString(arr[N]);
			String[] words = word2.split(" ");

			int answer = Integer.parseInt(words[0]);
			for(int i = 1; i < words.length; i = i + 2) {
				switch (words[i]) {
				case "+":
					answer += Integer.parseInt(words[i + 1]);
					break;

				case "-":
					answer -= Integer.parseInt(words[i + 1]);
					break;
				}
			}
			
			if(answer == 0) {
				list.add(word);
			}
			return;
		}
		
		for(int j = 1; j < 4; j++) {
			save[cnt] = j;
			f(cnt + 1);
		}
	}

}