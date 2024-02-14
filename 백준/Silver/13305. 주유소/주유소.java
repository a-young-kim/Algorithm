import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.InputMap;

/*
 * N개의 도시
 * 왼쪽 도시에서 오른쪽 도시로 이동
 * 기름 무한대로 넣고 1km 마다 1리터의 기름 사용
 * 도시에 하나의 주요소가 있으며 가격은 다를 수 있음
 * 
 * 각 도시의 주요소의 기름 가격과, 각 도시를 연결하는 도로의 길이를 입력으로 받아
 * 제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용을 계산하는 프로그램을 작성
 * 
 * 입력
 * 첫째줄, 도시의 개수 정수 N
 * 도로의 길이
 * 리터당 가격
 * 
 * 출력
 * 최소값
 * 
 * 문제 해결
 * 다음 거리를 이전 주요소들 등에서 최소값으로 구함
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] distance = new int[N - 1];
		int[] price = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N - 1; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			price[i] = Integer.parseInt(st.nextToken());;
		}
		
		int min = price[0];
		int answer = 0;
		for(int i = 0;i < N - 1; i ++) {
			answer += min * distance[i];
			
			min = Math.min(min, price[i + 1]);
		}
		System.out.println(answer);
	}

}