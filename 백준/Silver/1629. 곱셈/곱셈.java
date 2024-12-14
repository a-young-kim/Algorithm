/*
문제
1. 자연수 A를 B번 곱한 수를 C로 나눈 나머지를 구하여라

입력
1. A B C

제약조건
A B C는 int형으로 표현 가능

출력
1. A를 B번 곱한 수를 C로 나눈 나머지

아이디어
나머지의 규칙성 찾기

풀이과정
1. int a b c 입력
2. mod(a, b, c) 실행
3. 출력

public int mod(int a, int b, int c)
1. if(b == 1) return a % c
2. else
2.1 k = mod(a, (int)(b // 2), c
2.2 if(b % 2 == 0) return k * k % c
2.3 else return k * k * a % c
*/
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(mod(A, B, C));
    }

    public static long mod(int A, int B, int C){
        if(B == 1){
            return A % C;
        }

        long k = mod(A, (int)(B / 2), C);
        if(B % 2 == 0) {
            return k * k % C;
        }
        else{
            return k * k % C * A % C;
        }
    }
}
