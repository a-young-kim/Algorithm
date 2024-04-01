import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import java.util.*;

/*
 * 문제
 * 1. N * N의 부지에 최대한 긴 등산로
 * 2. 숫자가 표시된 지도로 주어지며 숫자는 지형의 높이
 * 3. 등산로를 만드는 규칙
 * 	3.1 가장 높은 봉우리에서 시작
 * 	3.2 높은 지역에서 낮은 지형으로 가로 또는 세로 방향으로 연결
 *  3.3 한 곳을 정하여 최대 K 깊이 만큼 지형을 깎는 공사 가능
 *  4. 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력
 *
 *  주의 사항
 *  1. 가장 높은 봉우리가 여러개라면 모두 가능
 *  2. 지도의 한 변의 길이는 8이하
 *  3. K는 5이하
 *  4. 지형의 높이는 20 이하
 *  5. 가장 높은 봉우리는 최대 5개
 *  6. 지형은 정수 단위로만 깎을 수 있음
 *  7. 지형의 높이가 1보다 작은 것도 가능
 *
 * 	아이디어
 * 	bfs
 *
 *  풀이
 *  1. 테스트 케이스 입력
 *  2. N, K 입력
 *  3. int[N][N] map 생성
 *  4. class Node 생성 int row, int col, int delete(삭제한 크기)
 *  5. map 입력 => 입력시 가장 높은 높이 값 저장
 *  6. int[][][] visited = new int[K + 1][N][N] 생성
 *  7. Queue<Node> 생셩
 *  8. for문을 돌면서 가장 높은 높이의 정보를 queue에 저장 new Node(row, col, 0); -> visited에서 해당 값 위치 1로 초기화
 *  9. bfs 시작
 *  	9.1 queue.poll()
 *  	9.2 for문으로 사방 탐색
 *  	9.3 사방 탐색 시 주변 값이 현재값보다 작을 때
 * 			9.3.1 visited[node.delete][nr][nc] >=  map[ node.delete][row][col] + 1 continue;
 *  		9.3.2 queue.add(new Node(nr, nc, node.delete))
 *  		9.3.3 visited[ node.delete][nr][nc] = map[ node.delete][row][col] + 1
 *  				-> visited값과 answer의 max 값을 answer에 저장
 *  	9.4 delete == 0이고 현재값보다 k 이하로 클 때
 *  		9.3.1 visited[ node.delete + 1][nr][nc] >=  map[ node.delete][row][col] + 1 continue;
 *  		9.3.2 queue.add(new Node(nr, nc, node.delete + 1))
 *  		9.3.3 visited[ node.delete + 1][nr][nc] = map[ node.delete][row][col] + 1
 *  				-> visited값과 answer의 max 값을 answer에 저장
 * 10. answer 출력
 *
 * 시간 복잡도
 * N * N * K * 20
 */
public class Solution {

    static int N, K;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        int T = Integer.parseInt(st.nextToken());

        for(int tc= 0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            answer = 0;

            map = new int[N][N];
            int H = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    H = Math.max(H,  map[i][j]);
                }
            }

            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(H != map[i][j]) continue;
                    boolean[][] v = new boolean[N][N];
                    v[i][j] = true;
                    bfs(new Node(i, j,false, H, v));
                }
            }

            System.out.println("#" + (tc + 1) + " " +  answer);

        }

    }
    public static void print(int[][][] visited) {
        for(int i = 0 ;i < 2; i++) {
            for(int j = 0; j < N; j++) {
                System.out.println(Arrays.toString(visited[i][j]));
            }
            System.out.println();
        }
    }

    public static void print2(boolean[][] visited) {
        for(int i = 0 ;i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) System.out.print("■");
                else System.out.print("□");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void bfs(Node startNode) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(startNode);

        int[][][] visited = new int[2][N][N];
        visited[0][startNode.row][startNode.col] = 1;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            //print2(node.visited);
            //System.out.println();
            for(int i = 0; i < 4; i++) {
                int nr = node.row + dy[i];
                int nc = node.col + dx[i];

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= N) continue;
                if(node.visited[nr][nc]) continue;

                if(map[nr][nc] < node.price) {
                    int idx = node.done ? 1:0;

                    if(visited[idx][nr][nc] > visited[idx][node.row][node.col] + 1) continue;

                    visited[idx][nr][nc] = visited[idx][node.row][node.col] + 1;
                    answer = Math.max(answer, visited[idx][nr][nc]);

                    boolean[][] v = new boolean[N][N];
                    for(int j = 0; j < N; j++) System.arraycopy(node.visited[j], 0, v[j], 0, N);
                    v[nr][nc] = true;

                    queue.add(new Node(nr, nc, node.done,  map[nr][nc], v));
                }

                else if(map[nr][nc] - K < node.price) {
                    if(node.done) continue;
                    int idx = 0;

                    if(visited[idx + 1][nr][nc] > visited[idx][node.row][node.col] + 1) continue;
                    visited[idx + 1][nr][nc] = visited[idx][node.row][node.col] + 1;
                    answer = Math.max(answer, visited[idx + 1][nr][nc]);

                    boolean[][] v = new boolean[N][N];
                    for(int j = 0; j < N; j++) System.arraycopy(node.visited[j], 0, v[j], 0, N);
                    v[nr][nc] = true;

                    queue.add(new Node(nr, nc, true,  node.price - 1, v));

                }

            }
        }

    }

    public static class Node{
        int row;
        int col;
        boolean done;
        int price;
        boolean[][] visited;


        public Node(int row, int col, boolean done, int price, boolean[][] visited) {
            super();
            this.row = row;
            this.col = col;
            this.done = done;
            this.price = price;
            this.visited = visited;
        }


        @Override
        public String toString() {
            return "Node [row=" + row + ", col=" + col + ", done=" + done + ", price=" + price
                    + "]";
        }

    }

}