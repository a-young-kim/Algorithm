/*
문제
1. n개의 도시
2. 한 도시에서 출발하여 다른 도시에 도착하는 m개의 버스
3. 각 버스는 한 번 사용할 때 필요한 비용 존재
4. 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하라

입력
1. 도시의 개수 n
2. 버스의 개수 m
3. m개의 시작도시 a, 도착도시 b, 한번 타는데 필요한 비용 c
3.1 시작도시와 도착도시가 같은 경우는 없다
3.2 비용은 십만이하의 자연수
3.3 시작 도시와 도착 도시를 연결하는 노선은 여러개 일수도

출력
n개의 줄을 출력
i도시에서 j도시로 가는데 필요한 최소 비용

제약조건
n은 2이상 100이하
m은 십만 이하

풀이과정
1. n입력
2. m입력
3. int[n + 1][n + 1] map 생성
4. for i in range(0, m)
4.1 map[i].fill(Integer.max_value)
4.2 map[i][i] = 0
4.3 int a b c 입력
4.4 map[a][b] = c
5. for start in range(1, n + 1):
5.1 for end in range(1, n + 1):
5.2 for middle in range(1, n + 1):
5.3 map[start][end] = Math.min(map[start][middle] + map[middle][end], map[start][end])
6. for i in range(1, n + 1)
6.1 if(map[start][end] != Integer.max) print(map[start][end)
6.2 else print(0)
 => 나중에 정의된 값들 경우에는 적용이 안될 것 같은데 ....
 => 시간 복잡도 n3 즉 100 * 100 * 100 십만
 => 거처가는 정점을 제일 위에 두는 것

풀이과정2
1. n입력
2. m입력
3. int[n + 1][n + 1] map 생성 PQ queue 생성 c가 작은 순으로 정렬
4. for i in range(0, m)
4.1 map[i].fill(Integer.max_value)
4.2 map[i][i] = 0
4.3 int a b c 입력
4.4 map[a][b] = c
4.5 queue.add(new int[]{a, b, c})
5. while(!queue.isEmpty())
5.1 int[] node = queue.poll()
5.2 for end in range(1, n + 1):
5.2.1 int min = Math.min(map[node[0]][end], node[2] + map[node[1]][end])
5.2.2 if(min != map[node[0]][end])
5.2.2.1 map[node[0]][end] = min
5.2.2.2 queue.add(new int[node[0], end, min))
6. for i in range(1, n + 1)
6.1 if(map[start][end] != Integer.max) print(map[start][end)
6.2 else print(0)
=> 시간 복잡도 n2

 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];

        for(int i = 0; i < N + 1; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(map[start][end] > weight) map[start][end] = weight;
        }

        for(int middle = 1; middle < N + 1; middle ++){
            for(int start = 1; start < N + 1; start ++){
                for(int end = 1; end < N + 1; end ++){
                    if(map[start][middle] == Integer.MAX_VALUE) continue;
                    if(map[middle][end] == Integer.MAX_VALUE) continue;

                    map[start][end] = Math.min(map[start][middle] + map[middle][end], map[start][end]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int start = 1; start < N + 1; start ++){
            for(int end = 1; end < N + 1; end ++){
                if(map[start][end] == Integer.MAX_VALUE) sb.append(0);
                else sb.append(map[start][end]);

                sb.append(" ");
            }
            if(start != N) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
