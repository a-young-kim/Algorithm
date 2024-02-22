import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 지민이는 가장 좋아하는 이야기를 말할 때 진실을 말하거나 엄청나게 과장해서 말한다.
 * 하지만 거짓말쟁이로 알려지기는 싫어한다. 
 * 하지만 일부는 진실이 안다.
 * 어떤 사람이 진실을 듣고 과장된 이야기를들었을 때도 거짓말재이로 알려지게 된다.
 * 
 * 사람의 수 N, 그 이야기의 진실을 아는 사람
 * 각 파티에 오는 사람의 번호
 * 지민이는 모든 파티 참가
 * 지민이가 거짓말재이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 개수 최댓값 구하는 프로그램 작성
 * 
 * 입력
 * 사람 수 N 파티 수 M
 * 이야기의 진실을 아는 사람의 수와 번호
 * 진실을 아는 사람의 수  사람들의 번호, 1부터 N까지
 * M개의 줄 까지 각 파티마다 오는 사람의 수와 번호
 * N과 M은 50 이하 자연수
 * 진신을 아는 사람수는 50 이하 정수
 * 각 파티마다 오는 사람 수 는 50이히ㅏ
 * 
 * 출력
 * 문제의 정답
 * 
 *
 * 풀이
 * 유니온 파인드를 사용하여 파티에 같이 가는 사람을 하나의 집합으로 만ㄷ르기
 * 진실을 아는 사람은 0으로 
 * dfs
 * 순열
 */
public class Main {
	
	static int N, M, knownNum, answer;
	static List<List<Integer>> party;
	static int[] node;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		node = new int[N + 1];
		for(int i = 0;i < N + 1; i++) {
			node[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		knownNum = Integer.parseInt(st.nextToken());
		for(int i = 0;i < knownNum; i++) {
			int n = Integer.parseInt(st.nextToken());
			union(0, n);
		}
		
		party = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partyNum = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>();
			for(int j = 0; j < partyNum; j++) {
				int n = Integer.parseInt(st.nextToken());
				list.add(n);
			}
			party.add(list);
		}
		
		for(int i = 0; i < M ; i++) {
			List<Integer> list = party.get(i);
			for(int j = 0;j < list.size() - 1; j++) {
				union(list.get(j), list.get(j + 1));
			}
		}
		
		for(int i = 0;i < M ; i++) {
			if(!check(i)) answer++;
		}
		System.out.println(answer);
	}
	
	public static boolean check(int idx) {
		List<Integer> list = party.get(idx);
		for(Integer num: list) {
			if(find(num) == 0)  return true;
		}
		return false;
	}
	
	public static int find(int num) {
		if(node[num] == num) return num;
		else node[num] = find(node[num]);
		return find(node[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) {
			node[aRoot] = bRoot;
		}
		else {
			node[bRoot] = aRoot;
		}
	}
}