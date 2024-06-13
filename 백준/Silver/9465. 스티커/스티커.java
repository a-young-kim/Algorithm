import java.util.*;
import java.io.*;

/*
문제
1. 스티커 2N개 구매
2. 스티커는 2행 N열로 배치
3. 스티커 한 장을 뗴면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용 불가
4. 각 스티커에 점수가 존재 점수의 합이 최대가 되면서 서로 변을 공유하지 않는 스티커 집합

입력
1. 테스트 케이스 개수 T
2. 첫째 줄에 N 십만 이하
3. 두 줄에는 n개의 정수 그 위치에 해당하는 스티커 정수

출력
각 테스트마다 스티커 점수의 최대값

풀이
1. T입력
2. for문
3. int[2][n] map 입력
4. int[2][n] dp 생성
5. 초기화 하기
6. dp[0][i] = Math.max(dp[0][i - 2], dp[1][i - 1], dp[1][i - 2])
7. dp[1][i] = Math.max(dp[1][i - 2], dp[0][i-1], dp[0][i - 2]) 해서
8. 적혀있는 것 중 가장 큰 거 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 0; test_case < T; test_case++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[][] map = new int[2][n];
            for(int i = 0; i < 2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            int[][] dp = new int[2][n];
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];
            
            if(n >= 2) {
                dp[0][1] = map[0][1] + map[1][0];
                dp[1][1] = map[1][1] + map[0][0];

                for (int i = 2; i < n; i++) {
                    dp[0][i] = Math.max(Math.max(dp[0][i - 2], dp[1][i - 1]), dp[1][i - 2]) + map[0][i];
                    dp[1][i] = Math.max(Math.max(dp[1][i - 2], dp[0][i - 1]), dp[0][i - 2]) + map[1][i];
                }
            }

            for(int i = 0; i < n; i++){
                answer = Math.max(answer, dp[0][i]);
                answer = Math.max(answer, dp[1][i]);
            }
            System.out.println(answer);
        }
    }
}