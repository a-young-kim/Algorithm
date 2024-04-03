/*
 * 문제
 * 1. 4개의 자석 -> 각 자석은 8개의 날을 가짐
 * 2. 각 자석은 N극 S극 자성을 가짐
 * 3. 임의의 자석을 1칸씩 K번 회전 시킬 때 자성이 다른 경우에만 반대 방향으로 1칸 회전
 * 4. 점수 계산 위의 자성이 S이면 1, 2, 4, 8점 획득
 * 5. ㅋ칸식 K번 회전한 후 점수의 총합
 * 6. 회전 되지 않으면 다음 값도 회전 안됨
 * 
 * 입력
 * 1. 총 테스트 케이스 개수 T
 * 2. 자석 회전 횟수 K
 * 3. 1번 자석 부터 각각 8개 날의 자성정보가 차례대로 주어진다.
 * 4. 다음 K개의 줄에 회전 정보 주어짐
 * 5. 1이 경우 시계 -1인 경우 반시계
 * 
 * 풀이
 * 1. T, K 입력받음
 * 2. int[][] map = new int[4][8]; 에 입력
 * 3. int[4] start  = new int[4]; 시작 위치의 index를 저장하는 배열
 * 4. for i가 K - 1까지
 * 4.1 for j가 4까지
 * 4.2 int b = start[i] + 2 -> if b >= 8이면 b = b - 8;
 * 4.3 int a =  start[i + 1] -2 -> if a < 0 이면 a = 8 + a
 * 4.4 if( map[i][b] == map[i +1][a]) break;
 * 4.5 다른 자성일 경우 start[i + 1]++ start[i]-- 혹은 반대 -> 만약 start[i + 1]이 8이상이면 8에서 빼줌
 * 5. for i 4까지 돌면서
 * 5.1 map[i][start[i]] == S이면 answer += 2 ^ i
 * 
 * 시간 복잡도
 * 4 * 20
 * 
 */
import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        
        for(int t=1; t<=T; t++) {
            int K = Integer.parseInt(br.readLine());
            
            int[][] map = new int[4][8];
            int[] start = new int[4];
            
            for(int i = 0; i < 4; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0; j < 8; j++) {
            		map[i][j] = Integer.parseInt(st.nextToken());
            	}
            }
            
            for(int i = 0; i < K ; i++) {
            	st = new StringTokenizer(br.readLine());
            	int num = Integer.parseInt(st.nextToken()) - 1;
            	int rotation = Integer.parseInt(st.nextToken());
            	
            	int[] change = new int[4];
            	change[num] = -1 * rotation;
            	
            	for(int j = num; j < 3; j++) {
            		int before = start[j] + 2 >= 8 ? start[j] + 2 - 8 :start[j] + 2;
            		int after = start[j + 1] - 2 < 0 ? start[j + 1] - 2 + 8: start[j + 1] - 2;
            		
            		if(map[j][before] == map[j + 1][after]) break;
            		change[j + 1] = rotation * (int)Math.pow(-1,  j - num );

            	}
            	
            	for(int j = num ; j > 0; j--) {
            		int after = start[j] - 2 < 0 ? start[j] - 2 + 8: start[j] - 2;
            		int before = start[j - 1] + 2 >= 8 ? start[j - 1] + 2 - 8 :start[j - 1] + 2;
            		
            		if(map[j - 1][before] == map[j][after]) break;
            		change[j - 1] = rotation * (int)Math.pow(-1,  num - j);

            	}
            	
            	for(int j = 0; j < 4; j++) {
            		start[j] = start[j] + change[j];
            		if(start[j] < 0) start[j] += 8;
            		else if(start[j] >= 8) start[j] -= 8;
            	}
            }
            
            int answer = 0;
            for(int i = 0; i < 4; i++) {

            	if(map[i][start[i]] == 1) answer += Math.pow(2,  i);
            }

            System.out.println("#" + t + " " + answer);
        }
    }

}