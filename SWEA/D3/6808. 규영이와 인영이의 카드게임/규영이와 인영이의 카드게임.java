/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
문제
규영 인영 1에서 18까지의 수가 적힌 18장 카드
각각 9장씩
한라운데 한장씩 카드, 카드에 적힌 수 비교 점수 계산
높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고
낮은 수를 낸 사람은 점 수 없음
총점이 높은 사람이 승자
총점이 같으면 무승부
규영이의 카드 순서 고정 인영이의 카드를 내는지에 따라 승패 결정
규영이가 이기는 경우와 지는 경우 수 구하기

입력
첫번쨰 줄 테스트 케이스 수 T
첫번쨰 줄 9개의 정수가 공백
각 정수는 1이상 18이하 같은 정수 없음
규영이는 정수에 주어지는 순서로 카드 제출

출력
#숫자 이기는경우 지는경우
 */

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
		public static int[] array, myArray;
    public static boolean[] visited;
    public static int win = 0, lose = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            win = 0;
            lose = 0;
            array= new int[9];
            myArray = new int[9];
            visited = new boolean[9];

            for(int i = 0; i < 9; i++){
                array[i] = sc.nextInt();
            }
            Arrays.sort(array);
            int cnt = 0;
            int index = 0;
            for(int i = 1; i < 19; i++){
                if(cnt < 9 && i == array[cnt]){
                    cnt++;
                    continue;
                }
                myArray[index] = i;
                index++;
            }
            f(0, 0, 0);
            System.out.println("#" + test_case + " " + win + " " + lose);
        }
        sc.close();
    }

    static public void f(int mySum, int friendSum, int cnt){
        if(cnt == 9){
            if(mySum < friendSum) win++;
            else if(mySum > friendSum) lose++;
            return;
        }

        for(int i = 0; i < 9; i++){
            if(visited[i]) continue;
            visited[i] = true;
            if(myArray[i] > array[cnt])
                f(mySum + myArray[i] +array[cnt], friendSum, cnt + 1);
            else if(array[cnt] > myArray[i])
                f(mySum, friendSum + myArray[i] + array[cnt], cnt + 1);
            visited[i] = false;
        }
    }
}