/*
문제
1. 모든 지점에 대해서 목표지점까지의 거리
2. 가로와 세로만 움직임 가능

입력
1. 지도 크기 가로 n 세로 m
2. 0은 갈 수 없는 땅 1은 갈 수 있는 땅 2는 목표 땅

출력
각 지점에서 목표 지점까지의 거리를 출력
원래 갈 수 없는 땅인 위치는 0 갈 수 있는 땅 중 도달할 수 없는 위치는 -1

아이디어 bfs

풀이과정
1. int n, m 입력
2. int[][] answer, int[][] map 생성, queue 생성
2.1 answer은 -1로 초기화
2.2. map 입력 하면서 2인 것은 queue에 넣기, 0인 것은 answer에도
3. while(queue에 있을 때)
3.1 Node node = queue.poll()
3.2 for i in range(0, 4)
3.2.1 int nr, nr
3.2.2. 범위 체크
3.2.3 갈수 없는 곳인지 방문한적있는지 체크
3.2.4 answer[nr][nc] = node.wieght + 1;
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] answer = new int[N][M];
        int[][] map = new int[N][M];
        Queue<Node> queue = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            Arrays.fill(answer[i], -1);
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num == 0) answer[i][j] = 0;
                if(num == 2) {
                    answer[i][j] = 0;
                    queue.add(new Node(i, j, 0));
                }
                map[i][j] = num;
            }
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = node.row + dy[i];
                int nc = node.col + dx[i];
                int nd = node.distance + 1;

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(answer[nr][nc] >= 0) continue;
                answer[nr][nc] = nd;
                queue.add(new Node(nr, nc, nd));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(answer[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static class Node{
        int row, col, distance;

        public Node(int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
