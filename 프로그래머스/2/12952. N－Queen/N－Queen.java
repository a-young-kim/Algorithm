
class Solution {
    int answer, n;
    int[][] visited;
    public int solution(int n) {
        answer = 0;
        
        visited = new int[n][n];
        
        this.n = n;
        f(0);
        
        return answer;
    }
    
    public void f(int idx){
        if(n == idx) {
            answer ++;
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(visited[idx][i] != 0) continue;
            visited[idx][i] ++;
            f2(idx, i, 1);
            f(idx + 1);
            f2(idx, i, -1);
            visited[idx][i] --;
        } 
    }
    
    public void f2(int row, int col, int flag){
        for(int i = 1; i < n; i++){
            // 가로
            if(col - i >= 0) visited[row][col - i] += flag;
            if(col + i < n) visited[row][col + i] += flag;
            
            // 세로
            if(row - i >= 0) visited[row - i][col] += flag;
            if(row + i < n) visited[row + i][col] += flag;
            
            // 대각선
            if(row + i < n && col + i < n) visited[row + i][col + i] += flag;
            if(row - i >= 0 && col - i >= 0) visited[row - i][col - i] += flag;
            if(row - i >= 0 && col + i < n) visited[row - i][col + i] += flag;
            if(row + i < n && col - i >= 0) visited[row + i][col - i] += flag;
        }
    }
}