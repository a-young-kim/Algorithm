/*
문제
1. 평범한 배낭
2. N개의 물건
3. 각 물건은 무게 W와 가치 V를 가짐
4. 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있음
5. 최대 K만큼의 무게만 넣을 수 있는 배낭

입력
1. 물품의 수 N 100이하, 준서가 버틸 수 있는 무게 K 십만 이하
2. 각 물건의 무게 W와 물건의 가치 V

출력
배낭에 넣을 수 있는 물건들의 가치합의 최댓값 출력

아이디어
그리디 -> 무게 당 가치 가 가장 높은 것들을 PQ 다 넣기 => 안됨
dp
=> i번째 물품을 넣었을 때 얻을 수 있는 최대 값
=> 이전 값는 업데이트

풀이과정
1. int N, int K 입력
2. int[][] arr = new int[N][2] 입력
3. int[][] dp = new int[N + 1][K + 1] 생성
4. for i in range(1, N + 1) =>
4.1 int weight = arr[0];
4.2 int value = arr[1];
4.3 for j in range(weight, K + 1)
4.3.1 if(dp[i][j] < dp[i - 1][j - weight])
4.3.1.1 dp[i][j] = dp[i - 1][j - weight] + value;
4.4 else dp[i][j] = dp[i - 1][j];
4.5 answer = Math.max(answer, dp[i][K]);
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];
        for(int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int[][] dp = new int[N + 1][K + 1];
        for(int i = 1; i < N + 1; i++){
            int weight = arr[i][0];
            int value = arr[i][1];

            for(int j = 0; j < K + 1; j++){
                if(j < weight){
                    dp[i][j] = dp[i - 1][j];
                }
                else{
                    int num = dp[i - 1][j - weight] + value;
                    dp[i][j] = Math.max(num, dp[i - 1][j]);
                }

            }
            answer = Math.max(answer, dp[i][K]);
        }
        
        System.out.println(answer);

    }

}
