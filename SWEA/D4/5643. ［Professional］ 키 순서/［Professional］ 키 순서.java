/*
 * 문제
 * 1. 1부터 N번까지의 번호가 붙어져 있는 학생
 * 2. N 명의 학생의 키는 모두 다르다
 * 3. 학생의 키를 비교한 결과가 주어질 때, 자신의 키가 몇번째인지 알 수 있는 학생들을 계산
 * 
 * 입력
 * 1. 테스트 케이스 개수 T
 * 2. 학생들 수 N 500 이하
 * 3. 두 학생의 키를 비교 횟수 M
 * 4. 두 학생들의 키 비교 a > b
 * 
 * 출력
 * 1. 테스트케이스 수 #TC
 * 
 * 아이디어
 * 1. 각 노드의 높이 곱하기
 * 2. 각 높이를 가진 노드의 개수를 구해서 저장
 * 3. 높이가 개수를 가진 노드가 1개인 것들 중 자신이 가진 부모의 개수와 각 높이를 가진 높이의 개수가 같지 않으면 찾을 수 없음
 * 
 * 풀이
 * 1. 테스트케이스 T 입력
 * 2. 학생 수 N 입력 M 입력
 * 4. List<List<Integer>> nodes 생성 후 입력 
 * 4.1 입력 시 들어오는 차수가 없는 것들은 List<Integer>에 삽입 -> int[] arr 차수 저장
 * 5. int[] height 배열 생성 -> 각 노드의 높이를 저장
 * 5.1 dfs를 이용하여 구하기 -> List크기 for문 시작
 * 6. List<List<INteger>> list -> 같은 높이를 가진 노드 저장
 * 7. for i가 2부터 N까지 돌면서 가지고 있는 차수 값이 이전 값이란 같으면 answer++
 * 	  다르면 현재 노드랑 연결된 노드의 차수 감소
 * 
 * dfs(int cnt, int idx)
 * 1. List<Integer> list = list.get(idx);
 * 2. for문 돌리기
 * 3. 저장된 값이 현재 값보다 작으면 들어감
 * 
 */
import java.io.*;
import java.util.*;

public class Solution {
    
	static int N, M, answer, cnt;
	static List<List<Integer>> nodes, nodes2;
	static List<Integer> startNodes;
	static int[] arr;
	static int[] height;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        
        for(int t=1; t<=T; t++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            answer = 0;
            
            nodes = new ArrayList<>();
            nodes2 = new ArrayList<>();
            for(int i = 0; i < N + 1; i++) {
            	nodes.add(new ArrayList<Integer>());
            	nodes2.add(new ArrayList<Integer>());
            }

            
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                
                int a= Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                nodes.get(b).add(a);
                nodes2.get(a).add(b);
            }
            
            for(int i = 1; i < N + 1; i++) {
            	cnt = 0;
            	visited = new boolean[N + 1];
            	visited[i] = true;
            	f(i, nodes);
            	f(i, nodes2);
            	if(cnt + 1 == N) answer++;
            }
            
            System.out.println("#" + t + " " + answer);
        }
        
 
    }
    
    public static void f(int node, List<List<Integer>> nodes) {
    	
    	List<Integer> list = nodes.get(node);
    	for(Integer nextNode: list) {
    		if(visited[nextNode]) continue;
    		visited[nextNode] = true;
    		cnt++;
    		f(nextNode, nodes);
    		
    	}
    }
}