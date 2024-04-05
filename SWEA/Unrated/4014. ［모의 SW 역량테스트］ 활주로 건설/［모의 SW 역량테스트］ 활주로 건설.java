/*
 * 문제
 * 1. N * N 크기 변수
 * 2. 각 셀의 숫자는 그 지형의 높이
 * 3. 활주로를 가로 또는 세로 방향으로 건설할 수 있는 가능성 확인
 * 4. 활주로 높이가 동일한 구간에서 건설 가능 
 * 5. 높이가 다른 구간의 경우 높이 차이가 1이고 해당 높이가 X인 경우 설치 가능
 * 6. 경사로의 길이 X와 절벽지대의 높이 정보가 주어질 때, 활주로를 건설할 수 있는 경우의 수 계산하는 프로그램 작성
 * 
 * 제약 사항
 * 1. 크기가 6이상 20이하
 * 2. 경사로의 높이는 항상 1이고 X는 2이상 4이하
 * 3. 지형의 높이는 1이상 6이하의 정수
 * 4. 동일한 셀에 두 개이상의 경사로 사용 불가
 * 
 * 입력
 * 1. 테스트케이스 개수 T
 * 2. 지도 한변의 크기 N, 경사로 길이 X
 * 
 * 풀이
 * 1. 테스트 케이스 T 입력
 * 2. int[N][N] map 생성 후 입력, 경사로 길이 X입력
 * 3. 가로 판단 함수 findRow();
 * 
 * findRow()
 * 1. for row가 0부터 N까지
 * 2. int needs = 0
 * 3. for col가 1부터 N까지
 * 4. map[row][col] - map[row][col - 1]의 절대값이 1 이상이면 break
 * 5. needs가 0이고 map[row][col] - map[row][col - 1]의 절대값이 1이면 needs = X - 1
 * 6. needs가 0초과 이고 map[row][col] != map[row][col - 1] 이면 break
 * 6.1 같으면 need --
 * 7. col == N이면 answer ++;
 * 
 */
import java.io.*;
import java.util.*;

public class Solution {
	static int N, X;
	static int[][] map;
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t =1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];
           
            for(int i = 0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0;j<N;j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int answer = 0;
            for(int col = 0; col < N; col++) {
            	boolean[] visited = new boolean[N];
            	int row = 1;
            	first: for(row = 1; row < N; row ++) {
            		if(visited[row]) continue;
            		if(map[row][col] - map[row - 1][col] == -1) {
            			int i = 0;
            			for(i = 0; i < X; i++) {
            				if(row + i >= N || visited[row + i]) break first;
            				if(map[row][col] == map[row + i][col]) continue;
            				break first;
            			}
            			
            			if(i == X) {
            				for(i = 0; i < X; i++) {
            					visited[row + i] = true;
                			}
            			}
            			
            		}
            		else if(map[row][col] - map[row - 1][col] == 1) {
            			int i = 0;
            			for(i = 0; i < X; i++) {
            				if(row - i - 1 < 0 || visited[row - i - 1]) break first;
            				if(map[row - 1][col] == map[row - i - 1][col]) continue;
            				break first;
            			}
            			
            			if(i == X) {
            				for(i = 0; i < X; i++) {
            					visited[row -i -1] = true;
                			}
            			}
            		}
            		else if(map[row][col] == map[row - 1][col]) continue;
            		else break;
            	}
            	if(row == N) {
            		//System.out.println("col: " + " " + col);
            		answer++;
            	}
            }
            
            for(int row = 0; row < N; row ++) {
            	boolean[] visited = new boolean[N];
            	int col = 1;
            	first:for(col = 1; col < N; col ++) {
            		if(visited[col]) continue;
            		
            		if(map[row][col] - map[row][col - 1] == -1) {
            			int i = 0;
            			for(i = 0; i < X; i++) {
            				if(col + i >= N || visited[col + i]) break first;
            				if(map[row][col] == map[row][col + i]) continue;
            				break first;
            			}
            			
            			if(i == X) {
            				for(i = 0; i < X; i++) {
                				visited[col + i] = true;
                			}
            			}
            		}
            		else if(map[row][col] - map[row][col - 1] == 1) {
            			int i = 0;
            			for(i = 0; i < X; i++) {
            				if(col - i - 1 < 0 || visited[col - i - 1]) break first;
            				if(map[row][col - 1] == map[row][col - i - 1]) continue;
            				break first;
            			}
            			
            			if(i == X) {
            				for(i = 0; i < X; i++) {
                				visited[col - i - 1] = true;
                			}
            			}
            		}
            		else if(map[row][col] == map[row][col - 1]) continue;
            		else break;
            	}
            	
            	if(col == N) {
            		//System.out.println("row: " + " " + row);
            		answer++;
            	}
            }
            
            System.out.println("#" + t + " " + answer);
        }
    }
}