import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 문제
 * 1. 구슬을 쏘아 벽돌을 깨트리는 게임 -> 제일 위에서
 * 2. 구슬을 N번 사용, 벽돌은 W * H 배열
 * 	- 0은 빈공간, 나머지는 벽돌
 * 3. 게임의 규칙
 * 	- 구슬은 좌우로 움직이면 맨 위의 벽돌만 깨트릴 수 있음
 *  - 벽돌은 숫자 1 ~ 9로 표현되며 구슬이 명중한 벽돌은 상하좌우로(숫자 - 1)만큼 같이 제거(본인 제외)
 *  - 제거되는 벽돌은 동시에 제거
 *  - 빈 공간이 있을 경우 벽돌을 밑으로 떨어짐
 *  - 다른 벽돌로 인해 본인도 제거될 경우 해당 벽돌도 숫자 -1 만큼 주변 벽돌 제거
 * 4. N개의 구슬을 떨어트려 최대한 많은 벽들을 제거할 때, 남은 벽돌의 개수는?
 *
 * 제약사항
 * 1. 구슬 N 4이하
 * 2. W 12 이하, H 15이하
 *
 * 아이디어
 *
 * 시간 복잡도
 * 12 ^ 4 * 27 * 15 * 12 => 지울수 있는 벽돌이 있는지 없는지 확인해서 break;
 *
 * 풀이
 * 1. 테스트 케이스 T 입력
 * 2. N, W, H 입력
 * 3. int[][] map 생성
 * 4. map에 입력 -> block의 개수 저장
 * 5. 중복 조합 구하는 함수 f(int cnt) 제작
 * 6. List<List<Node>>에 저장 되돌리기 위해
 *
 *
 * f(int cnt, int idx, int block)
 * 1. cnt가 4이면 남은 블록 answer에 넣기
 * 2. for i는 W까지 -> for j는 0부터 H 까지 0이 아닌 것을 찾을 때 까지 선택
 * 3. 구슬 떨어트린 후 block제거 한수 removeBlock(new Node(row, col), int cnt)
 * 4.  downBlock() 함수 실행
 * 5. f(cnt + 1, block - remove block)
 * 6. restoreBlock(cnt)
 *
 * resotoreBlock(int cnt);
 * 	1. list.get(cnt)에서 지운 node 원상 복구 해주기
 *  2. 원상 복구 된 위치 중 block이 있으면 row을 저장
 *  3. list 완료 후 아래에서 부터 넣어주기
 *
 *
 * removeBlocK(Node node, int cnt)
 * 1. int cnt => 지울 block 수
 * 2. bfs 탐색
 *
 * downBlock
 * 1. for문 i가 W까지
 * 2. int blankIdx = -1, endIdx = 0;
 * 3. while문 실행 -> endIdx < H 까지
 * 		map[i][endIdx] = 0이고 blankIdx가 -1이면 blankIdx = endIdx;
 * 		map[i]endIdx] != 0이고 blankIdx가 -1이면 endIdx ++;
 * 		map[i][endIdx] != 0이고 blockIdx -1이 아니면 map[i][blockIdx] = map[i][endIdx]
 * 		-> blockIdx  = -1, endIdx = blockIdx + 1
 *
 * bfs
 * 1. node poll()
 * 2. int level = map[row][col]
 * 3. 	for i 0부터 level 까지  0이 아닌 값 queue<Node> 에 삽입 -> lsit.get(cnt) 노드에 저장
 * 4.
 *
 *
 */
import java.util.*;

public class Solution {

    static int N, W, H;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][][] map;
    static int blockNum;
    static int answer ;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[N + 1][H][W];
            answer = Integer.MAX_VALUE;
            visited = new boolean[W];

            blockNum = 0;
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j< W; j++) {
                    map[0][i][j] = Integer.parseInt(st.nextToken());
                    if(map[0][i][j] != 0) blockNum++;
                }
            }

            f(0, blockNum);
            System.out.println("#" + test_case + " " + answer);
        }

    }

    public static void print(int cnt) {
        System.out.println();
        for(int i = 0; i < H; i++)System.out.println(Arrays.toString(map[cnt][i]));
        System.out.println();
    }

    public static void f(int cnt, int bNum) {


        if(bNum == 0 || answer == 0) {
            answer = 0;
            return;
        }
        answer = Math.min(answer, bNum);
        if(cnt == N) return;

        for(int col = 0; col < W; col++) {
            for(int row = 0; row < H; row++) {

                if(map[cnt][row][col] == 0) continue;

                int remove = removeBlock(cnt + 1, col, row, bNum);
                downBlock(cnt + 1);

                f(cnt + 1, bNum - remove);
                break;
            }
        }
    }

    public static void downBlock(int cnt) {
        for(int i = 0; i < W; i++) {
            Deque<Integer> deque = new ArrayDeque<Integer>();
            for(int j = 0; j < H; j++) {
                if(map[cnt][j][i] == 0) continue;
                deque.add(map[cnt][j][i]);
                map[cnt][j][i] = 0;
            }

            int j = H - 1;
            while(!deque.isEmpty()) {
                map[cnt][j][i] = deque.pollLast();
                j--;
            }
        }
    }
    public static int removeBlock(int cnt, int col, int row, int leftBlock) {

        int count = 0;
        Queue<Node> queue = new ArrayDeque<>();

        for(int i = 0; i < H; i++) {
            System.arraycopy(map[cnt - 1][i], 0, map[cnt][i], 0, W);
        }

        queue.add(new Node(row, col, map[cnt][row][col]));
        while(!queue.isEmpty()) {

            Node node = queue.poll();

            for(int i = 0 ; i < node.weight; i++) {
                for(int j = 0; j < 4; j++) {
                    int nr = node.row + dy[j] * i;
                    int nc = node.col + dx[j] * i;

                    if(nr < 0 || nr >= H) continue;
                    if(nc < 0 || nc >= W) continue;
                    if(map[cnt][nr][nc] == 0) continue;

                    Node newNode = new Node(nr, nc, map[cnt][nr][nc]);
                    map[cnt][nr][nc] = 0;
                    queue.add(newNode);
                    count++;

                    if(leftBlock - count == 0) return count ;
                }
            }
        }

        return count;
    }

    public static class Node{
        int row;
        int col;
        int weight;


        public Node(int row, int col, int weight) {
            super();
            this.row = row;
            this.col = col;
            this.weight = weight;
        }



        @Override
        public String toString() {
            return "Node [row=" + row + ", col=" + col + ", weight=" + weight + "]";
        }

    }

}