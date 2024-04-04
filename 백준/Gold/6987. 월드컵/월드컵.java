/*
 * 문제
 * 1. 6개국의 조별경기, 각 나라는 총 5번의 경기를 치른다.
 * 2. 조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무, 패가 가능한 결과인지 판별
 * 3. 네 가지의 결과가 주어질때 각각의 결과에 대하여 가능하면 1, 불가능하면 0출력
 * 
 * 입력
 * 첫째 줄부터 넷째 줄까지 각 줄마다 승 무 패 입력
 * 승무패의 수는 6이하 0이상
 * 
 * 출력
 * 가능하면 1 불가능하면 0
 * 
 * 
 */
import java.util.*;
import java.io.*;

/*
 * 승패 결정 함수
 * result
 * 
 */
public class Main {
	static int[][][] play;
    static int[] save, answer;
    static int[][] game, board;
    static int count;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        play = new int[4][6][3];

        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<6; j++) {
                for(int k=0; k<3; k++) {
                    play[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        
        game = new int[15][2];
        save = new int[2];
        f(0, 0);
        board = new int[6][3];
        
        answer = new int[4];
        
        f2(0);

        for(int i = 0; i < 4; i++) {
        	System.out.print(answer[i] + " ");
        }

    }
    
    public static void f2(int cnt) {
    	
    	if(cnt == 15) {
    		for(int k = 0; k < 4; k++) {
    			int i, j = 0;
    			first: for(i = 0; i < 6; i++) {
        			for(j = 0; j < 3; j++) {
        				if(board[i][j] != play[k][i][j]) break first;
        			}
        		}
    			
    			if(i == 6 && j == 3) answer[k] = 1;
    		}
    		
    		return;
    	}
    	for(int i = 0; i < 3; i++) {
    		makeBoard(i, cnt, 1);
    		f2(cnt + 1);
    		makeBoard(i, cnt, -1);
    	}
    }
    
    public static void makeBoard(int i, int num, int flag) {
    	switch(i) {
		case  0:{
			setBoard(game[num][0], 0, flag);
			setBoard(game[num][1], 2, flag);
			break;
		}
		case 1:
		{
			setBoard(game[num][0], 1, flag);
			setBoard(game[num][1], 1, flag);
			break;
		}
		case 2:{
			setBoard(game[num][0], 2, flag);
			setBoard(game[num][1], 0, flag);
			break;
			}
		}
    }
    
    public static void setBoard(int node, int state, int flag) {
    	board[node][state] += flag;
    }
    
    
    
    public static void f(int cnt, int idx) {
    	
    	if(cnt == 2) {
    		System.arraycopy(save,  0,  game[count++],  0,  2);
    		return;
    	}
    	for(int i = idx; i < 6; i++) {
    		save[cnt] = i;
    		f(cnt + 1, i + 1);
    	}
    }

}