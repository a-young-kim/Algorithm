import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

/*
문제
1. 가로 세로의 칸 수가 같은 정사각형의 구역 내부만 움직임
2. 출발점은 (0, 0)
3. 이동가능한 방향을 오른쪽과 아래
4. 가장 오른쪽 가장 아래칸에 도달하는 순간 게임 종료
5. 한번에 이동할 수 있는 카 수는 현재 밟고 있는 칸에 쓰여 있는 수
6. 게임을 종료할 수 있는지 여부 출력

입력
1. 게임 구역 크기 N
2. map 정보
2.1 가장 오른쪽 가장 아래에는 -1
2.2 나머지 칸에는 0 이상 100이하

출력
도달할 수 있으면 HaruHaru
없으면 Hing

제약 조건
N은 2이상 64이하

아이디어
bfs

시간 복잡도
O(N * N)

풀이 과정
1. N입력
2. int[N][N] map 생성 후 입력 dx dy 생성
4. Queue<Integer> queue 생성
5. queue.add(new int[]{0, 0, map[0][0]})
6. while(queue.isEmpty)
6.1 int[] node = queue.poll();
6.2 for i in range(0, 2):
6.2.1 int nr = dy[i] + node[0];
6.2.2 int nc = dx[i] + node[1];
6.2.3 if(범위 조건)
6.2.5 if(map[nr][nc] == -1)
6.2.5.1 sout(HaruHaru) return;
6.2.6 int nn = node[2] - 1
6.2.7. if(nn != 0) queue.add(new int[]{nr, nc, nn};
7. sout(Hing)

 */
import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int[] node = queue.poll();

            int num = map[node[0]][node[1]];
            for(int i = 0; i < 2; i++){
                int nr = node[0] + dy[i] * num;
                int nc = node[1] + dx[i] * num;

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= N) continue;
                if(visited[nr][nc]) continue;

                if(map[nr][nc] == -1){
                    System.out.println("HaruHaru");
                    return;
                }

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        System.out.println("Hing");
    }
}
