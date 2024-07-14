import java.io.*;
import java.util.*;

/*
문제
1. n개의 정수로 이루어진 임의의 수열
2. 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중
    가장 큰 합
3. 단 수는 한개 이상 선택
4. 수열에서 수를 하나 제거 가능

입력
1. n 십만 이하
2. n개의 정수로 이루어진 수열
3.수는 -천 이상 천 이하

출력
답

풀이
1. int n 입력
2. int[] arr 입력
3. int[][][] map 생성
3.1 세로는 start 가로는 end 높이는 그 사이에서 제거 한 것
4.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[2][n];
        dp[0][0] = arr[0];
        dp[1][0] = arr[0];

        for(int i = 1; i < n; i++){
            dp[0][i] = Math.max(dp[0][i - 1] + arr[i], arr[i]);
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + arr[i]);
        }

        int answer = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }
        System.out.println(answer);
    }
}