import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 크기가 N * N인 도시
 * 도시의 칸은 빈큰, 치킨집, 집 중 하나
 * 도시의 칸은 (r, c)와 같은 형태 1부터 시작
 * 
 * 치킨거리는 집과 가장 가까운 치킨집 사이의 거리, 각각의 집은 치킨거리를 가지고 있다. 
 * 도시의 치킨거리는 모든 집의 치킨 거리의 합
 * 거리는 절대값
 * 
 * 0은 빈칸, 1은 집, 2는 치킨집
 * 
 * 본사에서 수익을 증가시키기 위해 일부 치킨집을 폐업
 * 가장 수익을 많이 낼 수 있는 치킨집의 개수는 최대 M
 * 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램
 * 
 * 입력
 * 첫째줄 N과 M
 * 둘째줄 N개줄까지 도시의 정보
 * 집의 개수는 2N개를 넘지 않으며 적어도 1개 이상
 * 치키집의 개수는 M보다 크고 13보다 작거나 같다.
 * 
 * 출력
 * 폐없시키지 않을 치킨집을 최대 M개 골랐을 때 도시의 치킨 거리의 최솟값
 * 
 * 해결 방안
 * 해시 맵을 이용해서 key는 집 주소 value로 모든 치킨 집의 거리와 주소를 넣기
 * 각 치킨 집의 거리를 key vlaue로 1위의 개수를 넣기
 * 1위의 개수가 같다면 거리 순으로
 * 해당 치킨집이 끝낱으면 hashmap에서 제거
 */
public class Main {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static List<Integer> house, food;
	static int[][] array;
	static HashMap<Integer[], Integer> hashMap;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house = new ArrayList<>();
		food = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					house.add(i);
					house.add(j);
				}
				else if(num == 2) {
					food.add(i);
					food.add(j);
				}
			}
		}
		
		array = new int[M][2];
		f(0, 0);
		System.out.println(answer);
	}
	
	public static void f(int cnt, int idx) {
		if(cnt == M) {
			int min = 0;
			for(int i = 0; i < house.size() / 2; i++) {
				int row = house.get(i * 2);
				int col = house.get(i * 2 + 1);
				int minFood = Integer.MAX_VALUE;
				for(int j = 0; j < M; j++) {
					minFood = Math.min(minFood, Math.abs(row - array[j][0]) + Math.abs(col - array[j][1]));
				}
				min += minFood;
			}
			answer = Math.min(answer, min);
			return;
		}
		
		for(int i = idx; i < food.size() / 2; i++) {
			array[cnt][0] = food.get(i * 2);
			array[cnt][1] = food.get(i * 2 + 1);
			f(cnt + 1, i + 1);
		}
	}
}