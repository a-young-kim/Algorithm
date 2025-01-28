/*
문제
1. r, c 행렬 존재
2. 직사각형 모양의 범위를 여러번 선택해 테두리 부분에 있는 숫자를 시계방향으로 회전
3. 회전들을 배열에 적용한 뒤, 그 회전ㅇ 의해 숫자가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return

제한사항
1. r, c는 100이하
2. 가로 방향으로 숫자가 1부터 하나씩 증가하며 적힘
3. 회전의 개수는 만이하

아이디어
queue 사용

*/

import java.util.*;
class Solution {
    
    Queue<Integer> queue;
    int rows, columns;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        this.rows = rows;
        this.columns = columns;
        int N = queries.length;
        int[] answer = new int[N];
        
        map = new int[rows + 1][columns + 1];
        
        for(int i = 1; i < rows + 1; i++){
            for(int j = 1; j < columns + 1; j++){
                map[i][j] = (i-1) * columns + j;
            }
        }
        
        queue = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            setQueue(queries[i]);
            answer[i] = setMap(queries[i]);
        }
        return answer;
    }
    
    public int setMap(int[] query){
        int result = Integer.MAX_VALUE;
        int flag = 0;
        int row = query[0];
        int col = query[1]; 
        
        while(!queue.isEmpty()){
            int num = queue.poll();
            
            int nr = row + dy[flag];
            int nc = col + dx[flag];
            
            if(nr < query[0] || nr > query[2]) flag ++;
            if(nc < query[1] || nc > query[3]) flag ++;
            
            if(flag == 4) break;
            
            row = row + dy[flag];
            col = col + dx[flag];
            
            map[row][col] = num;
            result = Math.min(result, num);
        }
        
        return result;
    }
    
    public void setQueue(int[] query){
        int flag = 0;
        int row = query[0];
        int col = query[1];
        
        while(true){
            queue.add(map[row][col]);
            
            int nr = row + dy[flag];
            int nc = col + dx[flag];
            
            if(nr < query[0] || nr > query[2]) flag ++;
            if(nc < query[1] || nc > query[3]) flag ++;
            
            if(flag == 4) break;
            
            row = row + dy[flag];
            col = col + dx[flag];
        }
    }
    
    public void printMap(){
        for(int i = 1; i < rows + 1; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}