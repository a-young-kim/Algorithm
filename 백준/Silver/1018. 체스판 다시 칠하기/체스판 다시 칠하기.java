/*
문제
1. M * N 개 크기의 보드
2. 어떤 보드는 검은색 나머지는 흰색
3. 8 * 8 체스판으로 만들려고 한다
4. 체스판은 검은색 흰색이 번갈아 칠해져 있다
5. 지민이가 다시 칠해야 하는 정사각형의 최소 개수 구하라

입력
1. N과 M은 50 이하
2. B는 검은색 W은 흰색

아이디어
bfs

시간 복잡도
7 * 7 * 64 * 2 정도

배열을 계속 복사하면 메모리 초과 =>
    visited 배열로 처리

풀이
1. N M 입력
2. int[][] map 생성 0은 흰색 1은 검은색
3. for row in range(0, N - 8)
3.1 for col in range(0, M - 8)
3.1.1 check(row, col, 1, 0) // 흰색으로 시작
3.1.2 check(row, col, 0, 0) // 검은색으로 시작
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        queue = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String inputs = st.nextToken();
            for(int j = 0; j < M; j++){
                char input = inputs.charAt(j);

                if(input == 'B') map[i][j] = 1;
            }
        }

        for(int row = 0; row <= N - 8; row ++){
            for(int col = 0; col <= M - 8; col ++){

                visited = new boolean[N][M];
                // 흰색
                int count = setStartPoint(row, col, 0);
                count += countSquare(row, col);
                answer = Math.min(answer, count);

                visited = new boolean[N][M];
                // 검은색
                count = setStartPoint(row, col, 1);
                count += countSquare(row, col);
                answer = Math.min(answer, count);
            }
        }

        System.out.println(answer);
    }

    public static int countSquare(int row, int col){
        int result = 0;

        while(!queue.isEmpty()){
            int[] point = queue.poll();

            result += setQueue(row, col, point[0], point[1], point[2]);

            if(result > answer){
                queue.clear();
                result = answer + 1;
            }
        }

        return result;
    }

    public static int setQueue(int startRow, int startCol, int row, int col, int flag){
        int result = 0;
        for(int i = 0; i < 2; i++){
            int nr = row + dy[i];
            int nc = col + dx[i];

            if(nr - startRow == 8) continue;
            if(nc - startCol == 8) continue;

            if(visited[nr][nc]) continue;
            if(map[nr][nc] == flag){
                int num = flag == 0 ? 1 : 0;
                queue.add(new int[]{nr, nc, num});
                result ++;
            }
            else {
                queue.add(new int[]{nr, nc, map[nr][nc]});
            }

            visited[nr][nc] = true;
        }

        return result;
    }

    public static int setStartPoint(int row, int col, int flag){
        int result = 0;

        if(map[row][col] != flag){
            result ++;
        }

        visited[row][col] = true;
        result += setQueue(row, col, row, col, flag);
        return result;
    }
}
