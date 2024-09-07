import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
문제
1. 길이가 N인 정수 배열 A와 B
2. S = A*B
3. S를 가장 작게 만들기 위핸 A의 수 재배열, B는 재배열하면 안됨
4. S의 최솟값 출력

입력
1. N, 50보다 작거나 같은 자연수
2. A의 있는 N개의 수, 100보다 작거나 같은 음이 아닌 정수
3. B에 있는 수, 100보다 작거나 같은 음이 아닌 정수

출력
S의 최솟값

아이디어
조합, 백트레킹

풀이 -> 50! 시초
1. int N 입력
2. int[] A입력
3. int[] B입력
4. boolean[] visited = new boolean[N];
5. 순열 f 시작 -.> 50! 시초

def f(int index, int num)
0. if(num > answer) return;
1. if(index == N) answer = Math.min(answer, num) return;
2. for(int i = 0; i < N; i++)
3. if(visited[i]) continue;
4. visited[i] = true;
5. f(index + 1, num + B[index] * A[i];
6. visited[i] = false;

풀이2
1. int N 입력
2. int[] A입력
3. int[] B입력
4. B를 큰 수로 A를 작은 수로 정렬하여 곱하여 출력 -> PQ 사용


*/
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> A = new PriorityQueue<>(
                new Comparator<Integer>(){
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                }
        );

        PriorityQueue<Integer> B = new PriorityQueue<>(
                new Comparator<Integer>(){
                    @Override
                    public int compare(Integer o1, Integer o2){
                        return -1 * o1 + o2;
                    }
                }
        );

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            A.add(num);
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            B.add(num);
        }

        int answer = 0;
        while(!A.isEmpty()){
            int a = A.poll();
            int b = B.poll();
            answer += a * b;
        }
        System.out.println(answer);
    }
}