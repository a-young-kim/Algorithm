import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 변수면을 짓는 방법
 * 2. 자바에서 이름을 짓는 방법
 *  2.1 첫 단어는 소문자
 *  2.2 두번째 이상은 대문자
 *  2.3 모든 단어는 붙여쓴다
 * 3.C++에서 이름을 짓는 방법
 * 	3.1 소문자만 사용
 * 	3.2 단어와 단어를 구분하기 위해 밑줄 사용
 * 4. 변수명을 입력 받은 뒤 어떤 형식인이 알아내고 이를 다른 형식으로 바꿔야 한다. 
 * 5. 두 형식이 아니라면 에러를 발생한다. 
 * 6. 변수명을 바꿀 때에는 단어의 순서는 유지
 * 
 * 입력
 * 1. 변수명이 주어진다
 * 2. 영어 알파멧과 밑줄로만 이루어져있으면 100 이하
 * 
 * 출력
 * 1. Java 형식이면 C++
 * 2. C++ 이면 Java
 * 3. 둘다 아니면 Error 출력
 * 
 * 풀이
 * 1. Java 형식또는 C++ 형식이 아닌 경우 탐색
 * 	1.1 첫 단어가 대문자일 경우
 *  1.2 대문자가 존재하는데 '_'가 존재할 경우
 * 2. Java 형식인지 탐색
 * 	2.1 두번째 이후가 대문자 인지 확인
 * 	2.2 C++ 바꾸는 법 
 * 	2.2.1 '_' 제거 후 첫글자 대문자 변경
 * 	2.2.2 첫글자 제외 대문자 변환
 * 3. c++ 형식 탐색
 * 	3.1 모두 소문자 인지 탐색
 * 	3.2 java로 변경 _ 뒤의 부분 대문자로 변경
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String word = st.nextToken();
		
		// 첫 글자가 대문자 인지 확인
		char top = word.charAt(0);
		if(top <= 'Z' || word.charAt(word.length() - 1) == '_' || top == '_') {
			System.out.println("Error!");
			return;
		}
		
		// '_'가 포함되는지 확인
		if(word.contains("_")) {
			if(isCapital(word)) {
				System.out.println("Error!");
				return;
			}
			
			// C++ to Java
			String[] words = word.split("_");
			String newWord = "";
			
			for(int i = 0; i < words.length; i++) {
				if(i == 0) newWord += words[i];
				else if(words[i].length() == 0) {
					System.out.println("Error!");
					return;
				}
				else {
					newWord += words[i].toUpperCase().charAt(0) + words[i].substring(1);
				}
			}
			System.out.println(newWord);
		}
		else {
			// Java to C++
			String newWord = "";
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				
				if(c == '_') continue;
				if(c <= 'Z') {
					char c2 = (char) (c + 32);
					newWord += '_' + Character.toString(c2);
				}
				else newWord += c;
			}
			System.out.println(newWord);
		}
		
	}
	
	public static boolean isCapital(String word) {
		boolean flag = false;
		for(int i = 0; i < 26; i++) {
			char c = (char) ('A' + i);
			
			if(word.contains(Character.toString(c))) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}