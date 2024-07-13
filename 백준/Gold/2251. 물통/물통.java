import java.util.*;
import java.io.*;
/*
문제
1. A, B, C 각각 200리터 이하 세 개의 물통
2. 앞 두 물통은 비어 있고 세번째 물통은 가득 차 있다.
3. 어떤 물통에 들어 있는 물을 다른 물통에 쏟아 부을 수 있는데,
    이때 한 물통이 비거나 가득 찰 때까지 물 부을 수 있음 -> 손실 없음
4. 첫번째 물통이 비어 있을 때세번째 물통에 담길 수 있는 물의 양은?

입력
1. 세 정수 A B C
2. 공백을 구분해서 답 출력 각 용량은 오름차순

풀이
1. a, b, c 입력
2. boolean [a][b][c] visited 생성
3. 중복 순열 f 함수

f(int a, int b, int c)
1. if(a < 0 || b < 0 || c < 0) return, 범위 버서나는 것도
2. if(visited[a][b][c]) return
3. c -> a 넣기
4. c -> b 넣기
5. a -> b 넣기
6. b -> a 넣기
7. a -> c 넣기
7. b -> c 넣기
 */
public class Main {
    static boolean[][][] visited;
    static int a, b, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visited = new boolean[a + 1][b + 1][c + 1];

        f(0, 0, c);

        boolean[] answer = new boolean[c + 1];
        for(int i = 0; i < b + 1; i++){
            for(int j = 0; j < c + 1; j++){
                if(!visited[0][i][j]) continue;
                answer[j] = true;
            }
        }

   
        for(int i = 0; i < c + 1; i++){
            if(!answer[i]) continue;
            System.out.print(i + " ");
        }
    }

    public static void f(int myA, int myB, int myC){
        if(myA < 0 || myA > a) return;
        if(myB < 0 || myB > b) return;
        if(myC < 0 || myC > c) return;

        if(visited[myA][myB][myC]) return;
        visited[myA][myB][myC] = true;

        // a -> b
        f(0, myB + myA, myC);
        f(myA - (b - myB), b, myC);
        // a -> c
        f(0, myB , myC + myA);
        f(myA - (c - myC), myB, c);
        // b -> c
        f(myA, myB - (c - myC) , c);
        f(myA, 0 , myC + myB);
        // b -> a
        f(myA + myB, 0, myC);
        f(a, myB - (a - myA) , myC );
        // c -> a
        f(myA + myC, myB, 0);
        f(a, myB, myC - (a - myA));
        // c -> b
        f(myA , myB + myC, 0);
        f(myA, b, myC - (b - myB));
    }
}