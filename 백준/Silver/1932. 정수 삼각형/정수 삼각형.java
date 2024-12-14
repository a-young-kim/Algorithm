/*
문제
1. 삼각형에서 맨 위층 부터 시작하여 아래의 수 중 하나를 선택해서 내려온다
2. 이때 선택한 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성
3. 아래 층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 혹은 오른쪽 선택 가능

제약조건
삼각형의 크기는 1 이상 500 이하
각 수는 모드 정수 0이상 9999이하

입력
1. 삼각형 크기 n
2. n번째 줄까지 정수 삼각형

출력
합이 최대가 되는 경로에 있는 수의 합

아이디어
0번째 -> 0, 1
1번째 -> 1, 2
2번쨰 -> 2, 3
3번쨰 -> 3, 4
4번쨰 -> 4, 5

풀이과정
1. N입력
2. int[][] arr = new int[N][N] 생성
4. for i in range(0, N)
4.1 for j in range(0, N)
4.1.1 arr[i][j] 입력
5. int[][] dp 생성
6. for i in range(0, N - 1)
6.1 for j in range(0, i + 1)
6.1.1 if(i >= 0 && j >= 0) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1])
6.1.2 else if(i >=0) dp[i][j] = dp[i - 1][j]
6.1.3 dp[i][j] += arr[i][j]
7. int answer = 0
8. for i in range(0, N)
8.1 answer = Math.max(answer, dp[N - 1][i])
9. 출력

시간 복잡도
O(N 제곱)
 */
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());

                if(i > 0 && j > 0) {
                    dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                else if(i > 0){
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }
        int answer = 0;
        for(int i = 0; i < N; i++){
            answer = Math.max(answer, dp[N - 1][i]);
        }

        System.out.println(answer);
    }
}
