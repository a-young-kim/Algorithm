import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 문제
 * 그래프 정점 간성
 * 두 정점 사이 경로가 있다면 연결
 * 연결 요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합
 * 그래프는 하나 또는 그 이상의 연결 요소로 이루어져있다. 
 * 트리는 사이클이 없는 연결 요소이다.
 * 그래프가 주어 졌을 때 트리의 개수를 세는 프로그램을 작성하시요
 * 
 * 입력
 * 여러개의 테스트케이스
 * 정점 n과 간선 m
 * 같은 간선은 여러번 주어지지 않는다.
 * 정점은 1번 부터 n번까지 번호가 매겨져 있다.
 * 입력의 마지막 줄에는 0이 두개 주어진다.
 * 
 * 출력
 * 트리가 없다면 No trees
 * 한개라면 there is one three를
 * T개라 a forest of T tree를 테스트 번호화 함께 출력
 * 
 * 풀이 방식
 * 사이클이 있으면 안되고 
 * 정점에 연결되는 점이 없더라도 하나의 트리라고 표시
 * for문을 이용해서 queue에 정점을 넣고 while 문을 이용해 dfs
 * visited를 이용해 방문 표시
 */
public class Main {
	
	static int n, m, answer;
	static int[] node;
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			answer = 0;

			if(n == 0 && m == 0) break;

			set = new TreeSet<>();
			node = new int[n + 1];
			for(int i = 1;i < n + 1; i++) {
				node[i] = i;
			}
			
			for(int i = 0;i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);
			}
			
			Set<Integer> set2 =  new TreeSet<>();
			for(int i = 1; i < n + 1; i++) {
				node[i] = find(node[i]);
			}
			for(int num: set) {
				set2.add(find(num));
			}
			
			answer = (int) (Arrays.stream(node).distinct().count() - 1 - set2.size());
			if(answer >= 2) {
				System.out.println("Case " + test_case + ": A forest of " + answer + " trees.");
			}
			else if(answer <= 0) {
				System.out.println("Case " + test_case + ": No trees.");
			}
			
			else {
				System.out.println("Case " + test_case + ": There is one tree.");
			}
			test_case++;
		}
	}
	
	public static int find(int num) {
		if(node[num] == num) return num;
		else num = find(node[num]);
		return num;
	}
	
	public static void union(int first, int second) {
		
		int firstRoot = find(first);
		int secondRoot = find(second);
		if(firstRoot == secondRoot) {
			set.add(firstRoot);
			return;
		}
		
		if(node[firstRoot] < node[secondRoot]) {
			node[secondRoot] = firstRoot;
		}
		else {
			node[firstRoot] = secondRoot;
		}
	}
}