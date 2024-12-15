/*
문제
1. 세로길이 n 가로길이 m 격자 모양 땅에 석유
2. 시추관을 수직으로 단 하나만 뚫을 수 있을 때 가장 많은 석유를 뽑을 수 있는 위치
3. 시추관은 열 하나를 관통하며 열과 열 사이에는 불가
4. 시추관이 석유 덩어리의 일부를 지나면 해당 덩어리에 속한 모든 석유 뽑을 수 있음

제한 사항
1. n과 m은 1이상 500이하
2. 0이면 빈 땅 1이면 석유

아이디어 
bfs

풀이 과정
1. HashMap<Integer, Integer> map 생성 int[] dx int[] dy 생성 queue 생성
2. boolean[][] visited = new int[n][m] 생성 int cnt = 1 // 2번부터 번호 매기기
3. for i in range(0, n)
3.1 for j in range(0, m)
3.1.1 if(visited[i][j]) continue
3.1.2 if(land[i][j] !== 1) continue
3.1.3 queue.add(new int[]{i, j})
3.1.4 visited[i][j] = true 
    land[i][j] = ++cnt; // 2번부터 번호 들어감
    int sum = 0;
3.1.5 while(!queue.isEmpty)
3.1.5.1 node = queue.poll() sum ++
3.1.5.2 for i in range(0, 4)
3.1.5.2.1 int nr int nc 
3.1.5.2.2 범위 조건
3.1.5.2.3 if(land[nr][nc] != 1) continue
3.1.5.2.4 방문 확인
3.1.5.2.5 queue에 넣기
3.1.5.2.6 land[nr][nc] = cnt
3.1.6 map.put(cnt, sum)
4. for col in range(0, m)
4.1 boolean[] check = new boolean[cnt];
4.2 for row in range(0, n)
4.2.1 if(land[row][col] == 0) continue;
4.2.2 check[land[row][col]] = true
4.3 int result = 0
4.4 for i in range(0, cnt)
4.4.1 if(!check[i]) continue
4.4.2 result += map.get(check[i]);
4.5 answer = Math.max(answer, result)


시간복잡도
O(n * m)
*/
import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        
        int n = land.length;
        int m = land[0].length;
        
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        
        HashMap<Integer, Integer> oils = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        int name = 1; // 2부터 저장 
        for(int row = 0; row < n; row ++){
            for(int col = 0; col < m; col ++){
                if(visited[row][col]) continue;
                if(land[row][col] != 1) continue;
                
                visited[row][col] = true;
                land[row][col] = ++name;
                queue.add(new int[]{row, col});
                int sum = 1;
                while(!queue.isEmpty()){
                    int[] node = queue.poll();
                    
                    for(int i = 0; i < 4; i++){
                        int nr = node[0] + dy[i];
                        int nc = node[1] + dx[i];
                        
                        if(nr < 0 || nr >= n) continue;
                        if(nc < 0 || nc >= m) continue;
                        if(visited[nr][nc]) continue;
                        if(land[nr][nc] != 1) continue;
                        
                        visited[nr][nc] = true;
                        land[nr][nc] = name;
                        queue.add(new int[]{nr, nc});
                        sum ++;
                    }
                }
                oils.put(name, sum);
            }
        }
        name ++;
        for(int col = 0; col < m; col++){
            boolean[] check = new boolean[name];
            for(int row = 0; row < n; row++){
                if(land[row][col] == 0) continue;
                check[land[row][col]] = true;
            }
            
            int result = 0;
            for(int i = 2; i < name; i++){
                if(!check[i]) continue;
                result += oils.get(i);
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
}