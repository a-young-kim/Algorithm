import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 메모리: 11600
 * 시간: 80
 */
/*
 * 문제
 * 1. N *N 격자 --> 각각의 칸은 (r, c)
 * 2. 파이프는 2칸을 차지하는 형태
 * 3. 파이프는 밀어서 이동한다. 
 * 4. 파이프는 벽을 긁으면 안되며 항상 빈칸만 차지해야한다.
 * 5. 파이프를 밀 수 있는 방향은 우측, 대각선 우측 아래, 아래
 * 6. 처음에 파이프는 (1, 1)과 (1, 2)를 차지하고 방향은 가로
 * 
 * 입력
 * 1. 집의 크기 N 16이하
 * 2. 집의 상태, 0은 빈칸, 1은 벽
 * 
 * 출력
 * (1, 1) 에서 (N, N)으로 이동시키는 방법의 수 출력, 이동할 수 없는 경우 0 출력
 * 방법의 수는 천만이하
 * 
 * 아이디어
 * bfs
 * dp
 * 
 * 풀이
 * <dp>
 * 1. int N 입력
 * 2. int[N + 1][N + 1] map 생성 후 입력
 * 3. int[3][N + 1][N + 1] dp 생성 -> 가로 위치(0), 세로 위치(1), 대각선(2)
 * 4. dp[1][2] = 1
 * 5. dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1]
 * 	  dp[1][i][j] =  dp[1][i - 1][j] + dp[2][i - 1][j]
 * 	  dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1]
 * 6. 마지막 dp[i][N][N]의 합 출력
 * 
 * <bfs>
 * 1. int N입력
 * 2. int[N + 1][N + 1] map 생성 후 입력
 * 3. Node 생성 int row, int col, int state(현재 상태: 0은 가로, 1은 세로, 2는 대각선)
 * 4. queue 생성 -> 첫번째 위치 저장
 * 5. bfs 시작
 * 6. 정답 출력
 * 
 * bfs
 * 1. queue poll()
 * 2. for문 3미만 
 * 2.1 경계값 처리
 * 2.2 node state를 기준으로 안되는 경우 
 * 3. nr, nc가 N, N이면 answer 증가
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        
        for(int i=1; i<N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N + 1; j++) {
            	map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        long[][][] dp = new long[3][N + 1][N + 1];
        
        for(int i = 1; i < N + 1; i++) {
        	for(int j = 1; j < N + 1; j++) {
        		if(map[i][j] == 1) continue;
        		  dp[0][1][2] = 1;
        		  if(j - 1 >= 0) dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
        		  if(i - 1 >= 0)  dp[1][i][j] =  dp[1][i - 1][j] + dp[2][i - 1][j];
        		  if(j - 1 >= 0 && i - 1 >= 0) {
        			  if(map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
        			  dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
        		  }
        	}
        }
        /*for(int i = 0; i < 3; i++) {
        	System.out.println(i);
        	for(int j = 0; j < N + 1; j++) System.out.println(Arrays.toString(dp[i][j]));
        	System.out.println();
        }*/
        
        System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }

}