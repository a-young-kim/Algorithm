import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 걸굴읍 개인과 팀의 이름을 검색하여 외우게 하는 퀴즈 프로그램 제작
 * 
 * 입력
 * 총 입력 받을 걸그룹의 수 N 100 이하
 * 맞혀야할 문제의 수 M 이하
 * 총 M개의 퀴즈를 입력받는다
 * 각각의 퀴즈는 두 줄로 이루어져 있고 팀 이름이나 멤버의 이름이 첫줄에 주어지고 
 * 퀴즈의 종류를 나타내는 0 또는 1 이 두번째 줄에 주어진다. 
 * 퀴즈 종류가 0이면 팀이름, 1이면 멤버의 이름
 * 
 * 출력
 * 퀴즈에 답을 출력
 * 0일 경우 해당 팀에 속한 멤버의 이름을 사전순으로 한줄에 한명씩 출력
 * 퀴즈의 종류가 1인 경우 해당 멤버가 속한 팀의 이름 출력
 * 
 * 풀이
 * 1. hashmap<String, List<String>>으로 그룹이릅, 멤버이름
 * 2, 똑같ㅇ; 멤버이름 그룹이름을 마든다. 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, List<String>> teamMember = new HashMap<>();
		HashMap<String, String> memberTeam = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String teamName = st.nextToken();
			List<String> members = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				members.add(name);
				memberTeam.put(name,  teamName);
			}
			Collections.sort(members);
			teamMember.put(teamName,  members);
		}
		
		for(int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			//1인 경우 해당 멤버가 속한 팀의 이름 출력
			if(num == 1) {
				System.out.println(memberTeam.get(name));
			}
			else {
				List<String> list = teamMember.get(name);
				for(String n:list) {
					System.out.println(n);
				}
			}
		}
	}

}