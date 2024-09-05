import java.util.*;
import java.io.*;

/*
문제
1. N X M 크기의 미로
2. 1 X 1 크기의 방에 사탕이 있음
2.1 방은 (1, 1) 부터 (N, M) 까지
3. 이동은 아래, 오른쪽, 대각선 가능
4. (N, M)으로 이동할 떄, 가져올 수 있는 사탕 개수 최대값

입력
1. N, M 각각 1000이하
2. N개의 줄에는 총 M개의 숫자 (r, c)에 놓여있는 사탕의 개수, 100이하

출력
도착지에 도착할 때 사탕의 개수

아이디어
bfs --> 메모리 초과
dp

풀이
1. N, M 입력
2. int[N][M] map 입력
3. map 입력 받기
4. dp 생성
5. dp[i][j] = Math.max(위에, 왼쪽, 대각선
6. 출력

bfs
1. poll()
2. for i in range(0, 3)
2.1 nr = row + dy[i]
2.2 nc = col + dx[i]
2.3 nr nc 범위 확인
2.4 nr = N - 1 nc = M - 1이면
2.5 result에 적힌 숫자가 본인 숫자보다 작으면 queue에 넣음

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0;j < M; j++){
                // 위에
                if(i >= 1) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                // 왼쪽
                if(j >= 1) dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                // 대각선
                if(i >= 1 && j >= 1) dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i][j]);
                dp[i][j] += map[i][j];
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}