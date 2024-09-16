import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
문제
1. 왼쪽 부터 1번부터 N번까지
2. 사람들은 자신의 입장권 자리나 왼쪽 오른쪽에 앉을 수 있다.
3. VIP 회원들은 자기 좌석에만 앉아야 하며 옆 좌석으로 옮길 수 없다.
4. VIP 회원들의 좌석 번호가 주어졌을 떄 사람들이 좌석에 앉는
    서로 다른 가짓수를 구하라

입력
1. 좌석 개수 N, 1이상 40이하
2. 고정석 개수 M, 0이상 N이하
3. M개의 줄에는 고정석의 번호가 작은 수부터 큰 수의 순서로 한줄에 하나씩 입력

출력
1. 방법의 가짓수 long

아이디어
완전 탐색 + 백트래킹

풀이
1. N입력
3. M입력
4. boolean[] visited 생성
    -> for i in range(0, M) 입력하며 바뀔수 없는 자리는 true로 입력
5. f(int num, int N + 1) 실행

f(int num, int end):
1. if(num == end + 1): answer ++ return;
2. if(visited[num]) f(num + 1, end);
3. if(visited[num - 1] == 0)
    visited[num - 1] = true;
    f(num + 1, end)
    visited[num - 1] = false;
4. if(visited[num] == 0)
    visited[num ] = true;
    f(num + 1, end)
    visited[num] = false;
 5. if(visited[nun + 1] == 0)
     visited[num + 1] = true;
    f(num + 1, end)
    visited[num + 1] = false;

dp 풀이
1. dp[i] = dp[i - 2] + dp[i - 1]
2.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        if(N == 1) {
            System.out.println(1);
            return;
        }
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < N + 1; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int answer = 1;
        int vipSeat = 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            /* 현재 VIP 좌석과 이전 VIP 좌석 사이의 빈 좌석의 수를 계산하여
                이 빈 좌석 사이에서 사람들이 자유롭게 앉을 수 있는 경우의 수 계상
             */
            answer = answer * dp[num - vipSeat - 1];
            vipSeat = num;
        }
        answer = answer * dp[N - vipSeat];
        System.out.println(answer);
    }


}