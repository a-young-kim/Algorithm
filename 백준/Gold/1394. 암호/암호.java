/*
문제
1. 암호를 알아내려고 한다.
2. 암호에 어떤 문자들이 쓰이는지 알아내었고, 하나씩 대입해보려고 한다.
3. 대입 순서는 메모한 문자 집합의 순서
4. 한글자부터 암호가 풀릴 때까지 모두 대입한다.

입력
1. 암호로 사용할 수 있는 문자
1.1 사용할 수 있는 문자의 종류는 최대 100가지
1.2 공백 불가
1.3 대소문자 구분
2. 컴퓨터의 암호
2.1 암호 길이 최대 백만

출력
1. 주어진 암호를 몇번의 시도로 풀 수 있는지 출력
1.1 횟수를 900528로 나눈 나머지 출력

시간복잡도
100

풀이과정
1. String words 입력, String password 입력
3. int answer = 0;
4. for i in range(1, password.length) => i자리 일때의 수 구하기
4.1 int answer += Math.pow(words.length, i)
5. hashmap 생성
6. for i in range(0, words.length)
6.1 map.put(words.charAt(i), i + 1)
7. for i in range(password.length - 1, 0)
7.1 int num = hashmap.get(password.charAt(i))
7.2 num = num * Math.pow(words.length, password.length - i)
7.3 answer += num
7. answer % 900528 출력

def mod(int A, int B, int C)
1. if(B == 1) return A % 900528
2. int k = mod(A, B / 2)
3. if(B % 2 == 0) return k * k % 900528 * A % 900528
4. else return k * k % 900528
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int SEED = 900528;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String words = br.readLine();
        String password = br.readLine();

        long answer = 0;
        for(int i = 1; i < password.length(); i++){
            answer += mod(words.length(), i);
        }

        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < words.length(); i++){
            hashMap.put(words.charAt(i), i + 1);
        }

        for(int i = 0; i < password.length(); i++){
            long num = hashMap.get(password.charAt(i));

            if(i != password.length() - 1){
                num = (num - 1) * mod(words.length(), password.length() - i - 1);
            }
            answer += num;
        }
        System.out.println(answer % SEED);
    }

    public static long mod(int A, int B){
        if(B == 0) return 1;
        if(B == 1) return A % SEED;

        long k = mod(A, B / 2);
        if(B % 2 == 1) return k * k % SEED * A % SEED;
        else return k * k % SEED;
    }
}
/*
abcdefghijklmnopqrstuvwxyz
ayfcr
 */