import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제
 * 고이는 빗물의 총량
 * 
 * 입력
 * 1. 세로길이 H, 가로길이 W 각각 1이상 500이하
 * 2. 블록이 쌓인 높이를 의미하는 0이상 H이하 정수가 W개
 * 3. 블록 내부 공간에는 빈공간 없다, 바닥은 맞춰져 있다.
 * 
 * 출력
 * 한칸 용량은 1
 * 
 * 풀이
 * 1.stack 사용, 블록 크기와 index 넣기
 * 2. 비어있고 들어올 값이 0이면 넣지 않음
 * 3. 마지막으로 넣은 값이랑 넣을 값이랑 비교했을 때 크거나 같으면 pop()하고 새로운 값 넣기
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int answer = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < W; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(list.isEmpty()) {
				list.add(num);
				continue;
			}
			
			if(list.get(0) <= num) {
				if(list.size() >= 2) {
					int min = Math.min(list.get(0),  num);
					answer += min * (list.size());
					for(int j = 0; j < list.size(); j++) {
						answer = answer - list.get(j);
					}
				}
				list = new ArrayList<Integer>();
				list.add(num);
			}
			else {
				list.add(num);
			}
		}
	
		
		while(list.size() > 2) {

			int max = 0;
			int maxIdx = 0;
			
			for(int i = 1; i < list.size(); i++) {
				int newMax = Math.max(max,  list.get(i));
				
				if(newMax == max) continue;
				max = newMax;
				maxIdx = i;
			}
			
			answer += max * (maxIdx - 1);
			for(int j = 1; j < maxIdx; j++) {
				answer = answer - list.get(j);
			}
			
			list = list.subList(maxIdx, list.size());
		}

		 System.out.println(answer);

	}
	

}