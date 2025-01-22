/*
문제
1. (x, y) -> (r, c) 로 탈출
2. 탈출 조건 
2.1 (x, y) => (r, c) 이동 처리가 총 K
2.2 같은 격자 여러번 방문 가능
2.3 문자열이 사전 순으로 가장 빠른 경로
2.4 아래 => 왼쪽 => 오른쪽 => 위쪽 순
3. 미로를 탈출하기 위한 경로 return

제한사항
가로 세로 50 이하

아이디어
bfs => 순서만 맞추면 될 듯
방문 처리를 확인하는게 의미가 있을까.... 
하려면 해당 위치에 몇번을 걸려서 도착했는지 확인
*/
import java.util.*;
class Solution {
    
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};
    char[] dc = {'d', 'l', 'r', 'u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y, "", 0));
        
        int[][] visited = new int[n + 1][m + 1];
        visited[x][y] = 0;
        
        int level = queue.size();
        
        while(!queue.isEmpty()){
            if(level == 0){
                level = queue.size();
                k--;
            }
            
            if(k == 0) break;
            
            Point point = queue.poll();
            for(int i = 0; i < 4; i++){
                int nr = point.row + dy[i];
                int nc = point.col + dx[i];
                int np = point.level + 1;
                
                if(nr <= 0 || nr > n) continue;
                if(nc <= 0 || nc > m) continue;
                if(visited[nr][nc] >= np) continue;
                visited[nr][nc] ++;
                queue.add(new Point(nr, nc, point.result + dc[i], np));
            }
            
            level--;
        }
        
        if(k == 0){
            while(!queue.isEmpty()){
                Point point = queue.poll();
                
                if(point.row == r && point.col == c){
                    answer = point.result;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    class Point{
        int row, col;
        int level;
        String result;
        
        public Point(int row, int col, String result, int level){
            this.row = row;
            this.col = col;
            this.result = result;
            this.level = level;
        }
        
        public String toString(){
            return "row : " + row + " col : " + col + " result : " + result + " level : " + level;
        }
    }
}