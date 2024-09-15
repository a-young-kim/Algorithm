import java.util.*;
import java.io.*;

/*
문제
1. 어떤 수와 그 수의 숫자를 뒤집은 수가 일치하는 수를 팰린드롬이라 부른다
2. 어떤 수 N(백만 이하)가 주어졌을 때
3. N보다 크거나 같고 소수이면서 펠린드롬인 수 중에 가장 작은 수를 구하시오

입력
N

출력
1. 첫째 줄에 조건을 ㅁ나족하는 수를 출력

풀이
1. 입력 받기
2. 소수인지 확인 i*i < num 이하일 경우
3. 투포인터로 일치 확인
*/

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        N = Math.max(2, N);
        for(int num = N; num < Integer.MAX_VALUE; num ++){
            // 소수인지 확인
            if(!isPrime(num)) continue;
            // 펠린드롬인지 확인
           if(!isPalindrome(num)) continue;
           System.out.println(num);
           break;
        }
    }

    public static boolean isPalindrome(int num){
        int start = 1, end = 1;

        while((int)(num / Math.pow(10, end)) > 0) end++;

        for(int i = 0; i < (end - start + 1) / 2; i++) {
            int sNum = (int) ((num / Math.pow(10, end - 1 - i)) % 10);
            int eNum = (int) ((num / Math.pow(10, i)) % 10);
            if (sNum != eNum) return false;
        }
        return true;
    }

    public static boolean isPrime(int num){
        for(int i = 2; i * i <= num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}