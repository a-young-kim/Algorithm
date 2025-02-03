/*
문제
1. N개의 회의실
2. 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
3. 회의는 중간에 중단 불가
4. 끝나는 시간과 시작하는 시간은 동사 가능

입력
1. 회의 수 N 십만이하
2. 회의의 정보
2.1 시작 시간과 끝나는 시간은 int로 가능

출력
최대 사용할 수 있는 회의의 최대 개수

아이디어
그리디 -> 끝 - 시작 끝이 작은 순으로 정렬

풀이과정
1. int N입력
2. int[][] nodes = new int[N][2]; 입력
3. Array.sort(nodes)
4. boolean[] visited = new boolean[N];
5. for node in nodes;
5.1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for(int i = 0 ;i < N; i++){
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int end = 0;
        for(int[] node : arr){
            if(node[0] < end) continue;

            end = node[1];
            answer++;
        }

        System.out.println(answer);
    }
}
