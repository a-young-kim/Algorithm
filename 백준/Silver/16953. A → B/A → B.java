import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
문제
1. 정수 A를 B로 바꾸려고 한다.
2. 가능한 연산은 다음과 같다
2.1 2를 곱한다.
2.2 1을 수의 가장 오른쪽에 추가한다.
3. A를 B로 바꾸는데 필요한 연산의 최소 값을 구해보자

입력
1. A B 입력 -> 각각 1000000000 백억

출력
필요한 연산의 최솟값에 1을 더한 값 출력
만들 수 없는 경우 -1 출력

풀이과정 --> long 타입으로
1. A B 입력
2. boolean[B + 1] visited 생성
3. queue 생성
4. queue.add(A) int cnt = 1;
5. while(queue.isEmpty())
5.1 if(cnt == 0) cnt = queue.size()
5.2 int num = queue.poll()
5.3 if(num == B)
5.4 answer = cnt + 1; break
5.5 if(num * 2 <= B && !visited[num * 2]) queue.add(num * 2)
5.6 if(num * 10 + 1 <= B !visited[num * 10 + 1]) queue.add(num * 10 + 1)
6. answer 출력
 */
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());


        Queue<Long> queue = new ArrayDeque<>();
        queue.add(A);

        HashSet<Long> set = new HashSet<>();
        set.add(A);
        long cnt = 1, level = 1;
        long answer = -1;
        while(!queue.isEmpty()){
            if(cnt == 0) {
                cnt = queue.size();
                level++;
            }


            long num = queue.poll();
            if(num == B) {
                answer = level;
                break;
            }

            long first = num * 2;
            long second = num * 10 + 1;
            if(first <= B && !set.contains(first)) {
                set.add(first);
                queue.add(first);
            }
            if(second <= B && !set.contains(second)) {
                set.add(second);
                queue.add(second);
            }

            cnt--;
        }
        System.out.println(answer);
    }
}
