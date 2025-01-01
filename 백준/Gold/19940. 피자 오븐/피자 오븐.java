/*
실패 -> 메모리 초과

문제
1. N분 동작
2. 버튼을 누르면 5가지 동작을 한다 -> 문제 참조
3. 피자 오븐 첫 시간은 0분
4. 시간 감소 버튼으로 인해 0보다 작아질 경우 0분으로 설정
5. 설정해야 할 시간이 주어졌을 때, 그 시간을 만들기 위해 눌러야 하는 버튼의
    최소 횟수와 그 방법

제한 시간
0.25초 -> 시간 복잡도 고려

입력
1. T 개 테스트 케이스
2. 설정해야하는 시간 N

출력
각각 케이스마다 5개의 정수를 한 줄에 공백으로 출력
각 정수는 버튼을 누르는 횟수를 출력
최소 횟수가 여러가지 경우 사전 순으로 가장 앞서는 방법 출력

제한
T는 100이하
N은 천만 이하
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 0; test_case < T; test_case ++){
            int[] answer = new int[5];

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            answer[0] = N / 60;
            N = N % 60;

            if(N <= 35){
                if(N % 10 > 5){
                    answer[1] = N / 10 + 1;
                    answer[4] = 10 - N % 10;
                }
                else{
                    answer[1] = N / 10;
                    answer[3] = N % 10;
                }
            }
            else{
                answer[0] ++;
                if(N % 10 >= 5){
                    answer[2] = 6 - (N / 10 + 1);
                    answer[4] = 10 - N % 10;
                }
                else{
                    answer[2] = 6 - N / 10;
                    answer[3] = N % 10;
                }
            }


            for(int i = 0; i < 5; i++){
                sb.append(answer[i]);
                if(i != 4) sb.append(" ");
            }
            if(test_case != T - 1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}