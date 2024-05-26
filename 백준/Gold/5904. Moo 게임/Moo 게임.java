import java.util.*;
import java.io.*;

/*
문제
1. MOO 수열은 만드는데 해당 수열을 재귀적으로 만들 수 있다.
2. SK는 SK-1과 Sk-1 과 m 그리고  o가 k + 2개읜 수열과 Sk-1을 함쳐서 만들 수 있다
3. S0은 m o o 이다
4. 해당 수열은 무한개 만드 수 있다고 할 때 N 번쨰 글자를 구하는 프로그램을 작성해라

입력
N이 주어진다.

출력
N번째 글자를 출력

아이디어
S0 moo 3 / 1
S1 moo mooo moo  3 * 2 + 4 = 10 / 1 4 8
S2 moo moooo moo moooo moo mooo moo  10 * 2 + 5 / 1 4 8 13

풀이
1. N을 입력 받는다
2. for문을 돌면서 길이 가 N보다 커지는 수열을 구한다.
3. 구할 떄 M의 위치를 저장 한다.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        dfs(N, 0, 0);
    }

    public static void dfs(int N, int num, int preLen){
        int len = preLen * 2 + num + 3;

        if(N <= 3){
            if(N == 1) System.out.println("m");
            else System.out.println("o");
            return;
        }

        if(N < len){
            if(N <= preLen + num + 3){
                if(N == preLen + 1){
                    System.out.println("m");
                }
                else{
                    System.out.println("o");
                }
                return;
            }
            else{
                dfs(N -preLen - num - 3, 0, 0);
            }
        }
        else{
            dfs(N, num + 1, len);
        }
    }
}