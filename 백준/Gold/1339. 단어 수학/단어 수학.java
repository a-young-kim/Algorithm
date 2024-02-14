import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * N개의 단어로 이루어져있으면 알파벳 대문자로 이우러짐
 * 대문자를 0부터 9까지의 숫자 중 하나로 바꿔 N개의 수를 합하는 문제
 * 같은 알파벳은 같은 숫자로 바꿔야하며, 알파벳이 같은 숫자로 바뀌어서는 안된다.
 * N개의 단어가 주어졌을 때 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 * 
 * 입력
 * 단어의 개수 N
 * N개의 줄에 단어가 한 줄에 하나씩 주어짐
 * 안어는 알파벳 대문자로만 이루어졌다.
 * 모든 단어에 포함되어 있는 알파벳은 최대 10개이고 최대 길이는 8 
 * 서로다른 문자는 서로 다은 숫자를 나타낸다.
 * 
 * 출력
 * 최대값
 * 
 * 풀이
 * 1의 자리면 10 2의 자리면 100 으로 지정하여 각 알파벳을 key로 가진 hashmap에 저장해 두고
 * 같은 알파벳이 추가로 들어오면 더해준다.
 * 크기 순으로 정렬하여 그 값이 가장 큰값이 9이고 1씩 줄어든다. 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String[] array = new String[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = st.nextToken();
		}
		
		HashMap<Integer, Long> hashMap = new HashMap<>();
		for(int i = 0; i < N; i++) {
			int len = array[i].length();
			for(int j = 0; j < len; j++) {
				int c = array[i].charAt(j);
				if(!hashMap.containsKey(c)) {
					hashMap.put(c, (long)Math.pow(10, len - j));
				}
				else {
					hashMap.put(c, hashMap.get(c) +  (long)Math.pow(10, len - j));
				}
			}
		}
		
		List<Map.Entry<Integer, Long>> list = new ArrayList<>(hashMap.entrySet());
		Collections.sort(list, Map.Entry.comparingByValue());
		
		int cnt = 9;
		long answer = 0;
		for(int i = list.size() - 1; i >= 0; i--) {
			answer += cnt * list.get(i).getValue();
			cnt--;
		}
		
		System.out.println(answer / 10);	
	}

}