import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. N * M 크기 이차원 격자 각 칸은 땅이나 바다
 * 2. 연결된 땅은 상하좌우로 붙어있는 덩어리
 * 3. 다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수
 * 4. 모든 섬을 연결하려고 한다.
 * 5. 다리의 양끝은 섬과 인접한 바다 위에 있어야하고, 한 다리의 방향이 중간에 바뀌면 안된다.
 * 6. 다리는 2이상이어야 한다.
 * 7. 다리의 방향은 가로 또는 세로
 * 8. 다리가 교차하는 경우가 있다. -> 이 경우에는 각각 계산한다.
 * 9. 나라의 정보가 주어졌을 때 모든 섬을 연결하는 다리 길이의 최솟값을 구해라
 *
 * 입력
 * 1. 세로 크기 N, 가로 M
 * 2. 지도의 정보
 * 3. 각 줄은 M개의 수 0은 바다 1은 땅
 *
 * 출력
 * 다리 길이의 최솟값, 연결이 불가능하면 -1 출력
 *
 * 아이디어
 * 각 섬 간의 거리 구하기 -> 1 이상인 경우 List<List<>> 에 저장 -> 작은 순으로 sort() 하기
 * 조합 귀하기
 *
 * 풀이
 * 1. N, M 입력
 * 2. int[][] map 배열 입력
 * 3. bfs 탐색을 하여 각 섬 구분
 * 4. class Node 만들기 row, col, 현재 방향, 시작 노드
 * 5. a섬에서 b섬으로 가는 최소 값 구하기,
 *
 */
public class Main {

    static int N,  M, answer;
    static int[][] map;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] nodes;
    static int[] save;
    static List<Node> nodesList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) map[i][j] = -1;
            }
        }

        int cnt = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != -1) continue;
                map[i][j] = cnt;
                bfs(cnt, new Node(i, j));
                cnt++;
            }
        }

        nodes = new int[cnt][cnt];
        for(int i = 0; i < cnt; i++) Arrays.fill(nodes[i], Integer.MAX_VALUE);
        for(int i = 0 ;i < cnt; i++) nodes[i][i] = 0;

        nodesList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j< M; j++) {
                if(map[i][j] == 0) continue;
                setNodes(i, j);

            }
        }
        save = new int[cnt];
        for(int i = 1; i < cnt; i++) save[i] = i;

        for(int i = 1; i < cnt; i++){
            for(int j = i + 1; j < cnt; j++){
                if(nodes[i][j] == Integer.MAX_VALUE) continue;
                nodesList.add(new Node(i, j, nodes[i][j]));
            }
        }

        Collections.sort(nodesList, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                // TODO Auto-generated method stub
                return o1.weight - o2.weight;
            }
        });

        answer = 0;
        for(Node node: nodesList) {
            if(union(node.row, node.col)) {
                answer += node.weight;
            }
        }

        first: for(int i = 1; i < cnt; i++) {
            for(int j = i + 1; j < cnt; j++) {
                if( find(save[i]) != find(save[j])) {
                    answer = -1;
                    break first;
                }
            }
        }
        System.out.println(answer);
    }

    public static int find(int num) {
        if(save[num] == num) return num;
        return save[num] = find(save[num]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        save[aRoot] = bRoot;
        return true;
    }


    public static void setNodes(int row, int col) {

        int myNum = map[row][col];

        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 101; j++) {
                int nr = row + dy[i] * j;
                int nc = col + dx[i] * j;

                if(nr < 0 || nr >= N) break;
                if(nc < 0 || nc >= M) break;
                int nNum = map[nr][nc];

                if(nNum == myNum) break;
                if(nNum == 0) continue;

                if(j < 3) break;
                nodes[nNum][myNum] = Math.min(nodes[nNum][myNum], j - 1);
                nodes[myNum][nNum] = nodes[nNum][myNum];
                break;
            }
        }
    }


    public static void bfs(int cnt, Node startNode) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(startNode);

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nr = node.row + dy[i];
                int nc = node.col + dx[i];

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(map[nr][nc] != -1) continue;

                map[nr][nc] = cnt;
                queue.add(new Node(nr, nc));
            }
        }
    }

    public static class Node{
        int row;
        int col;
        int weight;

        public Node(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }




        public Node(int row, int col, int level) {
            super();
            this.row = row;
            this.col = col;
            this.weight = level;
        }




        @Override
        public String toString() {
            return "Node [row=" + row + ", col=" + col + ", weight=" + weight + "]\n";
        }

    }
}