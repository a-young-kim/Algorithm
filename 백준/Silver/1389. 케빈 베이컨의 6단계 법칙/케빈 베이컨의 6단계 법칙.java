import java.util.*;
import java.io.*;
/*
문제
1. 모든 사람들을 최대 6단계 이내에서 서로 아는 사람과 연결 가능
2. 임의의 두 사람이 최소 몇 단계만에 이어질 수 있는 지 계산
3. 수와 친구 관계가 입력으로 주어졌을 때 케빈 베이컨의 수가 가장 작은 사람을 구하시오
4. 케빈 베이컨 수란 모든 사람과 케빈 베이컨 게임을 했을 때 나오는 단계의 합

입력
1. 유저 수 N 100 이하, 친구 관계수 M 5000이하
2. 친구 관계, a, b
2.1 중복 가능
2.2 친구가 없는 사람은 없음
2.3 모든 사람은 친구 관계로 연결
3. 사람의 번호는 1부터 N

출력
1. 케빈 베이컨의 수가 가장 작은 사람
1.1 여러명인 경우 번호가 가장 작은 사람

풀이
1. N, M 입력
2. int[N][N] dp 생성
3. 입력
3.1 Integer.Max로 초기화
3.2 본인만 0
4. for i in range(1, N + 1)
4.1 for j in range(1, N + 1)
4.2 for k in range(1, N + 1)
4.3 dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]
5. 각 합이 가장 작은 것 출력

시간복잡도
100 * 100 * 100
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];
        for(int i = 1; i < N + 1; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[a][b] = 1;
            dp[b][a] = 1;
        }


        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dp[i][k] + dp[k][j] >=0 && dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i = 1; i < N + 1; i++){
            int count = 0;
            for(int j = 1; j < N + 1; j++) count += dp[i][j];
            result = Math.min(result, count);
        }
        int answer = 0;
        for(int i = 1; i < N + 1; i++){
            int count = 0;
            for(int j = 1; j < N + 1; j++) count += dp[i][j];
            if(count == result){
                answer = i;
                break;
            }
        }
        //for(int i = 0; i < N; i++) System.out.println(Arrays.toString(dp[i]));
        System.out.println(answer);
    }

}