/*
문제
1. 자물쇠는 N * N 크기의 정사각형 형태
2. 열쇠는 M * M 크기의 정사각형 형태
3. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분이 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠 열림
4. 자물쇠 영역을 벗어난 부분은 영향을 주지 않지만 그 외에는 정확이 일치 해야함
5. 자물쇠의 모든 홈을 채워 비워있는 곳이 없어야 자물쇠를 열 수 있음
6. 자물쇠를 열수 있으면 true 없으면 false를 return

제한사항
1. M과 N은 3이상 20이하
2. M <= N
3. 0은 홈 1은 돌기 부분을 나타냄

풀이과정
1. int N, M 입력
2. int blankCntByLock = 0; // 자물쇠의 홈 개수
3. for i in range(0, N):
3.1 for j in range(0, N):
3.1.1 if(lock[i][j] == 0) blankCntByLock++;
4. List<int[]> coorByKey 생성
5. for i in range(0, M):
5.1 for j in range(0, M)
5.1.1 if(key[i][j] == 1) coorByKey.add(new int[]{i, j})
6. for i in range(0, 4):
6.1 for row in range(N - 1, -1)
6.1.1 for col in range(( N - 1, -1)
6.1.1.1 for j in range(0, coorByKey.size())
6.1.1.1.1 int nr = coorByKey.get(i)[0] - row nc도
6.1.1.1.2 if(범위)
6.1.1.1.3 if(rock[nr][nc] == 1) break;
6.1.1.1.4 if(rock[nr][nc] == 0) cnt ++;
6.1.2. if(cnt == 0) return true;
6.1.3 turnLock(lock) -> 90도 돌리기

def turnLock(int[][] lock)
1. int[][] newLock = new int[N][N];
2. int[] dy = {0, 1, 0, -1}
3. int[] dx = {1, 0, -1, 0}
4. newLock.fill(-1)
5. Queue<int> queue 생성
6. for col in range(N, 0)
6.1 for row in range(0, N):
6.1.1 queue.add(lock[row][col])
7. for row in range(0, N)
7.1 for col in range(0, N)
7.1.1 newLock[row][col] = queue.poll();
8. return newLock
*/
import java.util.*;
class Solution {
    int N, M;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        N = lock.length;
        M = key.length;
        
        int blockCntByLock = 0;
        for(int row = 0; row < N; row ++){
            for(int col = 0; col < N; col ++){
                if(lock[row][col] == 0) blockCntByLock ++;
            }
        }
        
        for(int i = 0; i < 4; i++){            
            int r = N;
            int c = N;
            
            while(r >= -1 * N){
                if(c < -1 * N) {
                    c = N;
                    r --;
                }
                int cnt = blockCntByLock;
                
                first : for(int row = 0; row < M; row ++){
                    for(int col = 0; col < M; col ++){
                        int nr = row + r;
                        int nc = col + c;
                        
                        if(nr < 0 || nr >= N) continue;
                        if(nc < 0 || nc >= N) continue;
                        
                    
                        if(key[row][col] + lock[nr][nc] == 2) break first;
                        else if(key[row][col] == 1 && lock[nr][nc] == 0) cnt --;
                    }
                }
                if(cnt == 0) return true;
                c --;
            }
            
            key = turn(key);
        }
        return answer;
    }
    
    public int[][] turn(int[][] arr){
        int[][] newArr = new int[M][M];
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int col = 0; col < M; col ++){
            for(int row = M - 1; row >= 0; row--){
                queue.add(arr[row][col]);
            }
        }
        
        for(int row = 0; row < M; row ++){
            for(int col = 0; col < M; col++){
                newArr[row][col] = queue.poll();
            }
        }
        return newArr;
    }
}