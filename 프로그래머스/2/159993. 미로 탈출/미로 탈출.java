/*
문제
1. 직사각형 격자 형태 미로판
2. 각 칸은 통로와 벽으로 구성
2.1 벽으로된 칸은 이동 불가, 통로로된 칸만 이동 가능
3. 통로 중 한 칸에는 미로를 빠져나가는 문 존재
4. 문은 레버를 당겨서 열수 있다. -> 레버도 통로 중 한 칸에 있음
5. 출발 지점에서 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문으로 이동
6. 한 칸을 이동하는데 1초가 걸릴 때 최대한 빠르게 미로를 빠져나가는데 걸리는 시간

제한사항
1. 100 * 100 이하 미로

풀이과정
1. int N = maps.length; int M = maps[0].length;
2. int[] start, end = new int[2]
3. int[][] map = new int[N][M]
4. boolean[][][] visited = new boolean[2][N][M];
5. for i in range(0, N):
5.1 for j in range(0, M):
5.1.1 switch(maps[i].charAt(j))
5.1.2 case 'S' : 1
5.1.3 case 'E' : 3
5.1.4 case 'L' : 2
5.1.5 case 'O' : 0
5.1.6 case 'X' : -1
6. Queue<int[3]> queue = new ArrayDeque<>(); queue.add(start) int cnt = 1;
7. while (!queue.isEmpty())
7.1 int[] current = queue.poll()
7.2 if(cnt == 0)
7.2.1 cnt = queue.size()
7.2.2 answer ++;
7.3 for i in range(0, 4):
7.3.1 int nr = current[0] + dy[i], int nc 도
7.3.2 if(범위)
7.3.3 if(map[nr][nc] < 0) continue
7.3.4 if(visited[current[2]][nr][nc]) continue
7.3.5 if(map[nr][nc] == 2) current[2] ++;
7.3.6 queue.add(new int[]{nr, nc, current[2]})
7.3.7 visited[current[2]][nr][nc] = true;
8. 

시간 복잡도
100 * 100 * 2
*/
import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = -1;
        
        int N = maps.length;
        int M = maps[0].length();
        
        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[2][N][M];
        int row = 0, col = 0;
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                switch(maps[i].charAt(j)){
                    case 'S':
                        row = i;
                        col = j;
                        map[i][j] = 1;
                        break;
                    case 'E':
                        map[i][j] = 3;
                        break;
                    case 'L':
                        map[i][j] = 2;
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                }
            }
        }
            
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col, 0});
        visited[0][row][col] = true;
        int cnt = 1;
        
       int time = 0;
       first: while(!queue.isEmpty()){;
            if(cnt == 0){
                time++;
                cnt = queue.size();
            }
           
            int[] current = queue.poll();
            for(int i = 0;i < 4; i++){
                int nr = current[0] + dy[i];
                int nc = current[1] + dx[i];
                int n = current[2];
                
                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(map[nr][nc] < 0) continue;
                if(visited[n][nr][nc]) continue;
                if(map[nr][nc] == 2) n++;
                if(n == 1 && map[nr][nc] == 3) {
                    answer = time + 1;
                    break first;
                }
                
                if(current[2] != n) visited[n - 1][nr][nc] = true;
                visited[n][nr][nc] = true;
                queue.add(new int[]{nr, nc, n});
            }
            cnt --;
        }
        return answer;
    }
}