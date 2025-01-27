import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;


    static int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dx = {1, 0, -1, 1, -1, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        

        for(int row = 1; row <= N; row ++){
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for(int col = 1; col <= N; col ++){
                char tmp = input.charAt(col - 1);
                
                if(tmp == '#'){
                    map[row][col] = 9;
                }
                else{
                    map[row][col] = tmp - '0';
                }
            }
        }

        int count = 0;
        for(int row = 2; row <= N - 1; row ++){
            for(int col = 2; col <= N - 1; col ++){
                boolean isAvailable = true;
                
                for(int i = 0; i < 8; i++){
                    int nr = row + dy[i];
                    int nc = col + dx[i];
                    
                    if(map[nr][nc] == 0) {
                        isAvailable = false;
                        break;
                    }
                }
                
                if(isAvailable){
                    for(int i = 0; i < 8; i++){
                        int nr = row + dy[i];
                        int nc = col + dx[i];

                        map[nr][nc] -= 1;
                    }
                    
                     count ++;
                }
                
            }
            
        }

        System.out.println(count);

    }
    
}
