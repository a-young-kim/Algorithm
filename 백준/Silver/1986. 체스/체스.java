/*
문제
1. n*m 체스 판에 퀸 나이트 폰의 위치가 주어질 때 안전한 칸의 개수 출력
1.1 퀸은 가로 세로 대각선 이동 가능하며 장애물이 있다면 이동 불가
1.2 나이트는 2*3 직사각형을 그렸을 때 반대쪽 꼭지점으로 이동
1.2.1 중간 장애물이 있어도 이동 가능
1.3 폰은 상대팀의 말을 잡을 수 없음
2. 한 칸에는 하나의 말만 놓인다

입력
1. 체스판 크기 n m
2. 퀸의 개수와 퀸의 위치
3. 나이트의 개수와 나이트의 위치
4. 폰의 개수와 위치

제약 조건
1. 체스판의 크기는 가로 세로 각각 100이하 1이상
2. 나이트 퀸 폰의 개수는 각각 100이하 정수

출력
안전한 체스판의 개수

풀이과정
1. n m 입력
2. int[n][m] map 생성
3. int[] qx qy 선언 퀸이 이동할 위치
4. int[] kx ky 선언 나이트가 이동할 위치
5. q 입력
6. for i in range(0, q)
6.1 int r c 입력
6.2 map[r - 1][c - 1] = 1 => 퀸
7. k 입력
8. for  i in range(0, k)
8.1 int r c 입력
8.2 map[r - 1][c - 1] = 2 => 나이트
9 p 입력
10. for i in range(0, p)
10.1 int r c 입력
10.2 map[r - 1][c - 1] = 3 => 폰
11. for r in range(0, n)
11.1 for c in range(0, m)
11.1.1 if(map[r][c] == 1)
11.1.1.1 moveQueen(r, c)
11.1.2 else if(map[r][c] == 2)
11.1.2.1 moveKnight(r, c)
12. int answer = 0;
13. for r in range(0, n)
13.1 for c in range(0, m)
13.1.1 if(map[r][c] == 0) answer++;
14. answer 출력

moveQueen(int row, int col)
0. for i in range(0, 8)
1. int cnt = 1
2. while(true)
2.1 int nr = row + qy[i]
2.2 int nc = col + dx[i]
2.3 if(map을 벗어나면) break;
2.4 if(map[nr][nc] != 0) break;
2.5 map[nr][nc] = -1
2.6 cnt ++;

moveKnight(int row, int col)
1. for i in range(0, 8)
1.1 int nr = row + qy[i]
1.2 int nc = col + dx[i]
1.3 if(map을 벗어나면) contiue
1.4 map[nr][nc] = -1

시간 본잡도
 100 * 100 * 100 * 8
 */
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] qx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] qy = {1, 0, -1, 1, -1, 1, 0, -1};
    static int[] kx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] ky = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < q; i++){
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < k; i++){
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = 2;
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        for(int i = 0; i < p; i++){
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = 3;
        }

        for(int row = 0; row < n; row ++){
            for(int col = 0; col < m; col ++){
                if(map[row][col] == 1) moveQueen(row, col);
                else if(map[row][col] == 2) moveKnight(row, col);
            }
        }

        int answer = 0;
        for(int row = 0; row < n; row ++){
            for(int col = 0; col < m; col ++){
                if(map[row][col] == 0) answer++;
            }
        }
        System.out.println(answer);
    }

    public static void moveQueen(int row, int col){
        for(int i = 0; i < 8; i++){
            int cnt = 1;
            while(true){
                int nr = row + qy[i] * cnt;
                int nc = col + qx[i] * cnt;

                if(nr < 0 || nr >= n) break;
                if(nc < 0 || nc >= m) break;
                if(map[nr][nc] > 0) break;
                map[nr][nc] = -1;
                cnt++;
            }
        }
    }

    public static void moveKnight(int row, int col){
        for(int i = 0; i < 8; i++){
            int nr = row + ky[i];
            int nc = col + kx[i];

            if(nr < 0 || nr >= n) continue;
            if(nc < 0 || nc >= m) continue;
            if(map[nr][nc] != 0) continue;
            map[nr][nc] = -1;
        }
    }
}
