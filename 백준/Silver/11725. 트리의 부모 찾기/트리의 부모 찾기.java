/*
문제
1. 루트가 없는 트리가 주어진다
2. 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램

입력
1. 노드의 개수 N 십만 이하
2. N - 1개의 줄에 트리 상에서 연결된 두 정점 주어짐

출력
첫째 줄부터 N - 1 개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력

풀이 => 최악의 경우 N 제곱 10만 제곱
1. int N 입력
2. int[] arr = new int[N + 1] 생성
3. arr[i] = i로 초기화
4. Queue<Integer[]> queue 생성
5. for i in range(0, N)
5.1 queue.add(new int[2]{a, b); 입력
6. while(!queue.isEmpty)
6.1. int[] node = queue.poll()
6.2. if(arr[node[0]] == 1) arr[node[1]] = 1
6.3. else if(arr[node[1]] == 1) arr[node[0]] = 1
6.4 else if(arr[node[0]] == i && arr[node[i]] == i) continue
6.5 else
6.5.1 if(arr[node[0]] == i) arr[node[1]] = i
6.5.2 else arr[node[0]] = i
7. for i in range(2, N)
7.1 System.out.print(arr[i])

풀이 => 최악의 경우 N 제곱
1. int N 입력
2. int[] arr = new int[N + 1] 생성
3. arr[i] = i로 초기화
4. List<List<>> list 생성
5. for i in range(0, N + 1)
5.1 list.add(new ArrayList<>()) 생성
6. for i in range(0, N):
6.1. int a , b 입력
6.2. list.get(a).add(b)
6.3 list.get(b).add(a)
7. boolean[] visited = new boolean[N + 1] 생성
8. Queue<Integer> queue 생성
9. queue.add(1)
10. while(!queue.isEmpty())
10.1 int node = queue.poll()
10.2 if(visited[node]) continue
10.3 List<> subList = list.get(node)
10.4 for subNode in subList
10.4.1 arr[subNode] = node
10.4.2 queue.add(subNode)
11. 출력

 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i < N + 1; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        boolean[] visited = new boolean[N + 1];

        while(!queue.isEmpty()){
            Integer node = queue.poll();

            if(visited[node]) continue;
            visited[node] = true;

            List<Integer> subList = list.get(node);
            for(Integer subNode : subList){
                if(visited[subNode]) continue;
                arr[subNode]  = node;
                queue.add(subNode);
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < N + 1; i++){
            sb.append(arr[i]);
            if(i != N) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
