/*
문제
1. 어제 받은 떡의 개수와 그저께 받은 떡의 개수를 더한 만큼의 떡을 받아야 함
2. D째 날에 준 떡의 개수가 K개임을 알 때 처음 만난날 준 떡의 개수 A와
    그 다음날 준 떡의 개수 B를 개산하여라
3. 답이 되는 A B가 하나 이상일 떄 하나만 출력

풀이
1. 할머니가 넘어온 날 D 호랑이에게 준 떡 개수 K

제약 조건
1. D는 3이상 30이하
2. K는 10이상 십만 이하

출력
1. 첫째 날에 준 떡의 개수 A
2. 둘째 날에 준 떡의 개수 B

아이디어
1. a
2. b
3. a + b
4. a + 2b
5. 2a + 3b
6. 3a + 5b
7. 5a + 8b

피보나치 수열 구하기

풀이과정
1. int d k 입력
2. int[] fibo = int[d + 1] 입력
3. fibo[0] = 1 fibo[1] = 1
4. for i in range(2, d + 1)
4.1 fibo[i] = fibo[i - 1] + fibo[i - 2]
5. int a = 1; int b = 0
6. while(true)
6.1 int result = k - fibo[d - 3]
6.2 if(result % fibo[d - 2] == 0)
6.2.1 b = (result % fibo[d - 2] break
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] fibo = new int[d + 1];
        fibo[0] = 1;
        fibo[1] = 1;

        for(int i = 2; i < d + 1; i++){
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }

        int a = 1;
        int b = 1;

        while(true){
            int result = k - a * fibo[d - 3];
            if(result % fibo[d - 2] == 0){
                b = result / fibo[d - 2];
                break;
            }
            a++;
        }
        System.out.println(a);
        System.out.println(b);
    }
}
