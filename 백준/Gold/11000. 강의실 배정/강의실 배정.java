import java.util.*;
import java.io.*;

/*
문제
1. S에서 시작해서 T에 끝나는 N개의 수업이 주어진다.
2. 최소의 강의실을 사용해서 모든 수업이 가능하게 해야한다.
3. 수업이 끝난 직후 다음 수업을 시작할 수 있다.
4
입력
N 이십만 이하
N개의 줄에 S와 T 존재

출력
강의실의 개수를 출력하라

풀이
1. N입력
2. 곂치지 않는 수업 array 만들고 시작 시간이 빠르고 종료 시작이 빠른 순으로 정렬
3. 앞에서 부터 판단
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array, (o1, o2) ->{
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        /*for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(array[i]));
        }*/


        Queue<Integer> queue = new PriorityQueue<>();
        int answer = 0;
        for(int i = 0; i < N; i++){
            boolean check = false;

            Integer node = queue.poll();
            if(node == null){
                queue.add(array[i][1]);
                answer++;
            }
            else if(node > array[i][0]){
                queue.add(array[i][1]);
                queue.add(node);
                answer++;
            }
            else{
                queue.add(array[i][1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.println(sb);
    }
}