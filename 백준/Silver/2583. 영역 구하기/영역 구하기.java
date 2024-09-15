import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
문제
1. MXN 모눈종이
2. 눈금에 맞추어 K개의 직사각형을 그린다.
3. K개의 직사각형의 내부를 제외한 나머지 부분이 분리된 영역으로 나누어지게 도니다
4. M, N, K 그리고 K개 직사각형의 좌표가 주어질때, K개의 직사각형 내부를 제외한
    나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 분리된 각 영역의 넓이가
    얼마인지 구하여 출력하시오

입력
1. M, N, K가 빈칸을 사이에 두고 주어짐
2. M, N, K 모두 100이하 자연수
3. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의
    x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 주어짐
4. 모눈 종이의 왼쪽 꼭짓점의 x,y좌표는(0, 0), 오른쪽 위의 꼭짓점의 좌표는(N,M)
5. 입력되는 K개의 직사각형들이 모눈 종이 전체를 채우는 경우는 없다

출력
1. 분리되는 영역의 개수
2. 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 두고 출력

풀이
1. M, N, K입력
2. int[M][N] map 생성
3. for i in range(0, K):
4. int x1, y1, x2, y2 입력 받기
5. for y in range(y1, y2 + 1):
6. for x in range(x1, x2 + 1):
7. map[M - y][N - x] ++;
8. boolean[M][N] visited 생성
9. for i in range(0, M):
10. for j in range(0, N):
11 if(visited[i][j]) continue;
12 if(map[i][j] != 0) continue;
13 Queue<INteger[]> queue 생성
14. queue.add({j,i}, int count = 1;
15. while(!queue.isEmpty)
16. Integer[] node = queue.poll():
17. for i in range(0, 4) => 사방 탐색
18. int nr = node[0] + dy[i]
19. int nc = node[1] + dx[i]
=> 범위 정하기
20. if(visited[nr][nc]) continue
21. if(map[nr][nc]) conintue;
22. queue.add(nr,nc)
23. visited[nr][nc] = true; count++
24 bfs 나와서 answer.add(count)
25. answer정렬 후 출력
 */

public class Main {
    static final int[] dy = {0, 0, 1, -1};
    static final int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y = y1; y < y2 ; y++){
                for(int x = x1; x < x2 ; x++){
                    map[y][x] ++;
                }
            }
        }

        boolean[][] visited = new boolean[M][N];
        List<Integer> answer = new ArrayList<>();
        for(int i = 0 ; i < M; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] != 0) continue;
                if(visited[i][j]) continue;

                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                int count = 0;
                visited[i][j] = true;
                while(!queue.isEmpty()){
                    int[] node = queue.poll();
                    count++;

                    for(int k = 0; k < 4; k++){
                        int nr = node[0] + dy[k];
                        int nc = node[1] + dx[k];

                        if(nr < 0 || nr >= M) continue;
                        if(nc < 0 || nc >= N) continue;
                        if(visited[nr][nc]) continue;
                        if(map[nr][nc] != 0) continue;

                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }

                answer.add(count);
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for(int num : answer){
            System.out.print(num + " ");
        }
    }
}