/*
문제
1. 잘못된 수를 부를 때마다 0을 외침 => 가장 최근 재민이의 수 삭제
2. 모든 수를 받아 적은 후 그 수 합 출력

입력
1. 정수 K => 십만 이하
2. 정수는 0이상 백만이하

출력
최종적으로 적어 낸 수의 합 출력

아이디어
 stack
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 0) stack.pollLast();
            else stack.add(num);
        }

        int answer = 0;
        while(!stack.isEmpty()){
            answer += stack.poll();
        }

        System.out.println(answer);
    }
}
