import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 문제 - 텀 포로젝트
 * 1. 프로젝트 팀 수에는 제한이 없다. 
 * 2. 모든 학생들이 동일한 팀의 팀원인 경우 한 팀만 있을 수도 있다. 
 * 3. 모든 학생들은 프로젝트를 함께하고 싶은 학생을 한명 선택
 * 4. 혼자 하고 싶은 경우 자기 자신 선택 가능
 * 
 * 5. 사이클이 존재해야 하나의 팀이 된다. 
 * 6. 어느 프로젝트 팀에도 속하지 않는 학생들의 수를 계산하는 프로그램 작성
 * 
 * 입력
 * 1. 테스트케이스의 개수  T
 * 2. 학생 수 n 10만 이하
 * 3. 선택된 학생들의 번호 1부터 n 까지
 * 
 * 출력
 * 케이스마다 한줄씩 프로젝트 팀에 속하지 못한 학생 수
 * 
 * 풀이
 * 유니온 파인드
 * 1. T 입력 -> for문
 * 2. n입력 -> 학생 수
 * 3. 선택 번호 입력할 때 마다 union 함수 실행
 * 4. union에서 넣으려는 값과 저장하려는 값이 같아질 경우 개수 return
 * 
 * 시간 복잡도 해결 방법
 * -> 중간에 연결 된 친구들은 끝까지 루프가 될 수 없다. 
 */
public class Main {
	static int[] array;
	static Set<Integer> set, preset;
	static Set<Integer> nogroup;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 0; test_case < T; test_case++) {
			st= new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			array = new int[N + 1];
			
			for(int i = 1; i < N + 1; i++) {
				array[i] = i;
			}
			
			set = new HashSet<>();
			nogroup = new HashSet<>();
			st= new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				int next = Integer.parseInt(st.nextToken());
				
				union(i, next);
			}
			System.out.println(N - set.size());
		}
	}
	
	public static int find(int num) {
		if(nogroup.contains(num)) return 0;
		if(set.contains(num)) return 0;
		preset.add(num);
		if(array[num] == num) return num;
		return find(array[num]);
	}
	
	
	
	public static void union(int a, int b) {
		preset = new HashSet<>();
		
		int bRoot = find(b);
		if(bRoot == 0) nogroup.add(a);
		
		if(a == bRoot) {
			set.addAll(preset);
			for(Integer num: preset) array[num] = num;
		}
		else array[a] = b;
	}
}