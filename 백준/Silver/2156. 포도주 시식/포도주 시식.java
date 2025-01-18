/*
문제
1. 포도주 시식 규칙
1.1 포도주 잔을 선택하면 그 잔은 모두 마셔야 하고, 마신 후 원래 위치에 놓여야 함
1.2 연속으로 놓여 있는 3잔은 모두 마실 수 없다
2. 될 수 있는 대로 많은 양의 포도주를 맛봐야함
3. 1부터 N까지의 번호가 붙어 있는 N개의 포도주 잔이 순서대로 테이블 위에 놓여있다
4. 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램 잣겅

입력
1. 포도주 잔의 개수 N => 10000이하
2. 포도주의 양은 순서대로 주어짐 => 포도주의 양은 1000이하

출력
최대로 마실 수 있는 포도주의 양

아이디어 => dp
이전 값을 포함한 것과 포함하지 않은 값으로 이차원 배열 만들기
연속으로 놓여진 잔들 간에는 비교해서 계산 필수

풀이 과정
1. N 입력
2. int[][] dp = new int[N + 1][2]
3. for i in range(1, N + 1)
3.1 dp[i][0] = Math.max(dp[i - 2][1], dp[i - 3][1]) + arr[i]
3.1 dp[i][1] = dp[i - 1][0] + arr[i]


 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());


        int[] arr = new int[N + 1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][2];
        int answer = arr[1];
        dp[1][0] = arr[1];

        for(int i = 2; i <= N; i++){
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]);

            if(i >= 3){
                dp[i][0] = Math.max(dp[i][0], dp[i - 3][0]);
                dp[i][0] = Math.max(dp[i][0], dp[i - 3][1]);
            }

            dp[i][0] += arr[i];

            dp[i][1] = dp[i - 1][0] + arr[i];

            answer = Math.max(answer, dp[i][0]);
            answer = Math.max(answer, dp[i][1]);
        }

//        for(int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(answer);
    }
}
