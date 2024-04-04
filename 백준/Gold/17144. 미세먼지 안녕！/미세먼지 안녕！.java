/*
 * 문제
 * 1. R * C 격자
 * 2. (r, c) 칸에 있는 미세먼지의 양을 실시간 모니터링 시스템
 * 3. 공기청정기는 항상 1번열에 존재
 * 4. 공기청정기가 없는 칸에 미세먼지 존재
 * 5. 1초 동알 발생하는 일
 * 5.1 미세먼지 확산 동시에
 * 5.2 미세먼지 4방향 확산
 * 5.3 공기청정기 존재시 확산 안함
 * 5.4 확산되는 양은 Arc / 5
 * 5.5 확산 후 남응 양은 Arc - 확산된양
 * 6. 공기청정기 작동
 * 6.1 공기청정기의 바람 발생
 * 6.2 위쪽 공기청정기는 반시계방향으로 순환, 아래쪽 공기청정기의 바람은 시계방향 순환
 * 6.3 바람이 불면 미세먼지는 모두 한칸 이동, 
 * 6.4 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고 공기청정기로 들어간 미세먼지는 모두 정화
 * 7. 방의 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아 있는 미세먼지의 양을 구하자
 * 
 * 입력
 * 1. R, C, T 주어짐
 * 2. map 정보가 주어짐 -1은 공기청정기가 존재하는 곳
 * 
 * 아이디어 
 * bfs 후 공기청정기 작동
 * 
 * 풀이
 * 1. R, C, T입력
 * 2. int[][] map
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<Node> queue;
    static int[] wind = new int[2];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map= new int[R][C];
        
        int cnt = 0;
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) {
                	wind[cnt++] = i;
                }
            }
        }
        
        

        for(int i = 0; i < T; i++) {
        	queue = new ArrayDeque<>();
        	makeQueue();
        	
        	int[][] map2 = new int[R][C];
        	map2[wind[0]][0] = -1;
        	map2[wind[1]][0] = -1;
        	     
        	while(!queue.isEmpty()) {
        		Node node = queue.poll();
        		
        	
        		cnt = 0;
        		for(int j = 0; j < 4; j++) {
        			int nr = node.row + dy[j];
        			int nc = node.col + dx[j];
        			int nw = map[node.row][node.col] / 5;
        			
        			if(nr < 0 || nr >= R) continue;
        			if(nc < 0 || nc >= C) continue;
        			if(map[nr][nc] == -1) continue;
        			
        			map2[nr][nc] += nw;
        			cnt++;
        		}
        		map2[node.row][node.col] += map[node.row][node.col] - map[node.row][node.col] / 5 * cnt;
        	}
        	
        	map = map2;
        	doWind();
        }
        
        int answer = 0;
        for(int i = 0; i < R; i++) {
        	for(int j = 0;j < C; j++) {
        		if(map[i][j] == -1) continue;
        		answer += map[i][j];
        	}
        }
        System.out.println(answer);
    }
    
    public static void print() {
    	for(int i = 0; i < R; i++) System.out.println(Arrays.toString(map[i]));
    	System.out.println();
    }
    
    public static void doWind() {
    	
    	
		int num = wind[0];
		
		for(int i = num - 1 ; i > 0; i--) {
    		map[i][0] = map[i - 1][0];
    	}
    	
    	for(int i = 1; i < C; i++) {
    		map[0][i - 1] = map[0][i];
    	}
    	
    	for(int i = 1; i < num + 1; i++) {
    		map[i - 1][C - 1] = map[i][C - 1];
    	}
    	
    	for(int i = C - 1; i > 1; i--) {
    		map[num][i] = map[num][i - 1];
    	}
    	map[num][1] = 0;
    	
    	num = wind[1];
    	for(int i = num + 1; i < R - 1; i++) {
    		map[i][0] = map[i + 1][0];
    	}
    	for(int i = 0; i < C - 1; i++) {
    		map[R - 1][i] = map[R - 1][i + 1];
    	}
    	for(int i = R - 1; i > num; i--) {
    		map[i][C - 1] = map[i - 1][C - 1];
    	}
    	for(int i = C - 1; i > 1; i--) {
    		map[num][i] = map[num][i - 1];
    	}
    	map[num][1] = 0;
    	
    }
    
    public static void makeQueue() {
    	for(int row = 0; row < R; row ++) {
    		for(int col = 0; col < C; col ++) {
    			  if(map[row][col] > 0) queue.add(new Node(row, col));
    		}
    	}
    }
    
    public static class Node{
    	int row;
    	int col;
    	
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}
    	
		
    	
    	
    }

}