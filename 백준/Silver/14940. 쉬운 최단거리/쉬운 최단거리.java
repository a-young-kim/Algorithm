import java.util.*;
import java.io.*;

/*
문제
1. 모든 지점에 대해서 목표 지점까지의 거리
2. 가로 세로로만 움직임 가능

입력
1. 지도 n, m 세로 가로 각각 1000이하
2. n개의 줄에 m개의 숫자
2.1 0을 갈수 없음 1을 갈 수 있음 2는 목표지점
2.2 2는 단 한개

출력
각 지점에서 목표지점까지의 거리를 출력
원래 갈 수 없는 땅인 위치는 0 => 벽
원래 갈 수 있는 땅인 부분 중에 도달할 수 없으면 -1

풀이
1. n과 m 입력
2. int[][] map = int[n][m] 생성
3. map 입력 받기, queue생성
3.1 도착지 부분은 queue에 저장
4. bfs 시작
5. result 출력

bfs
1. queue poll
2. 상하좌우 탐색 후
3. result의 값이 Integer.max가 아니면 continue, 맞으면 부모의 값 + 1넣기

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] result = new int[n][m];
        for(int i = 0 ;i < n; i++) Arrays.fill(result[i], -1);

        Queue<Node> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < m; j++){
                int node = Integer.parseInt(st.nextToken());
                if(node == 2)  {
                    queue.add(new Node(i, j));
                    result[i][j] = 0;
                }
                else if(node == 0) result[i][j] = 0;
                map[i][j] = node;
            }
        }


        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = node.row + dy[i];
                int nc = node.col + dx[i];
                int level = result[node.row][node.col] + 1;

                if(nr < 0 || nr >= n) continue;
                if(nc < 0 || nc >= m) continue;

                if(result[nr][nc] != -1) continue;
                else{
                    result[nr][nc] = level;
                    queue.add(new Node(nr, nc));
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j  = 0; j < m; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Node{
        int row, col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

}