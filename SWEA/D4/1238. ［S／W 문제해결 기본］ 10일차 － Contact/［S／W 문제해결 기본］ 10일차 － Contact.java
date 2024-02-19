import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import org.xml.sax.HandlerBase;

/*
 * 문제
 * 그래프 단방향
 * 다자 간 통화 가능
 * 숫자가 가장 큰 사람을 반환
 * 
 * 제약상항
 * 연락 인원은 최대 100명 번호는 100이하
 * 비어있는 번호 존재
 * 동시에 전달
 * 다시 연락 안됨, 받을 수 없는 사람 존재
 * 
 * 입력
 * 10개 테스트케이스
 * 입력 데이터의 길이와 시작점
 * from to to
 * 동일한 쌍이 여러개가 있을 수도 있음
 * 
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			HashMap<Integer, Set<Integer>> hashMap = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if(!hashMap.containsKey(from)) {
					Set<Integer> set = new HashSet<Integer>();
					set.add(to);
					hashMap.put(from, set);
				}
				else {
					hashMap.get(from).add(to);
				}
			}
			
			Queue<Integer> q = new ArrayDeque<>();
			q.add(V);
			int answer = Integer.MIN_VALUE;
			int size = q.size();
			boolean[] visited = new boolean[101];
			visited[V] = true;
			while(!q.isEmpty()) {
				if(size == 0) {
					size = q.size();
					answer = Integer.MIN_VALUE;
				}
				
				int node = q.poll();
				answer = Math.max(node, answer);
				size --;
				if(!hashMap.containsKey(node)) continue;
				for(int num : hashMap.get(node)) {
					if(visited[num]) continue;
					visited[num] = true;
					//answer = Math.max(num, answer);
					q.add(num);
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
}