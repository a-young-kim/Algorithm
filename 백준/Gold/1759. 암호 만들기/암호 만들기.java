import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 메모리:18720
 * 시간:112
 */
/*
 * 문제
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 
 * 최소 한 개의 모음(a, e, i, o, u)와 최소 두 개의 자음으로 구성
 * 오름차순으로 정렬
 * 문자의 종류가 C가지일 때 가능성 있는 모든 암호를 구성
 * 
 * 입력
 * 두 정수 L, C
 * C개의 문자들이 공백
 * 알파벳 소문자만
 * 
 * 출력
 * 각 줄에 사전식으로 모두 출력
 */
public class Main {
	
	static int L, C, vowel, con;
	static char[] array;
	static List<String> list;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		array = new char[C];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C; i++) {
			array[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(array);
		
		f(0, 0, 0, 0, "");
		for(int i = 0 ; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
	}
	
	public static void f(int idx, int cnt, int vowel, int con, String word) {
		if(cnt == L) {
			if(vowel >= 1 && con >= 2) {
				list.add(word);
			}
			return;
		}
		
		if(idx == C) return;
		
		for(int i = idx; i < C; i ++) {
			if(array[i] == 'a' ||array[i] == 'e'||array[i] == 'i'||array[i] == 'o'||array[i] == 'u') {
				f(i + 1, cnt + 1, vowel + 1, con, word + array[i]);
			}
			else f(i + 1, cnt + 1, vowel, con + 1, word + array[i]);
		}
	}
}