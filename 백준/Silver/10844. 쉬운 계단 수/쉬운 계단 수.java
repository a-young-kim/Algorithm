/*
문제
1. N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자
2. 계단 수 -> 인접한 모든 자리의 차이가 1
3. 0으로 시작하는 수는 계단 수가 아니다

입력
1. N
2. N은 1보다 크거나 100이하

출력
정답을 1,000,000,000 으로 나눈 나머지

아이디어
dfs => 시간 초과 날 듯
dp

 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static long[][] dp;
    static final int DIVIDE = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][10];
        for(int i = 0; i < 10; i++) dp[1][i] = 1;

        for(int i = 2; i <= N; i++){
           for(int j = 0; j < 10; j++){
               if(j == 0) dp[i][j] = dp[i - 1][j + 1] ;
               else if( j == 9) dp[i][j] = dp[i - 1][j - 1];
               else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];

               dp[i][j] %= DIVIDE;
           }
        }

        long answer = 0;
        for(int i = 1; i < 10; i++){
            answer += (dp[N][i] % DIVIDE);
            answer %= DIVIDE;
        }

        System.out.println(answer);
    }
}
