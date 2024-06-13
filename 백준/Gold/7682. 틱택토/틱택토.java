import java.util.*;
import java.io.*;

/*
문제
1. 두 사람이 번갈아가며 말을 놓는 게임, 3 * 3 격자판
2. 처음에는 비어 있음
3. 첫번쨰 사람은 X 두번쨰 사람은 O
4. 가로, 세로, 대각선 방향으로 3칸을 잇는 경우 게임 끝
5. 게임판이 가득 차도 게임 끝
6. 게임판의 상태가 주어질 때, 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태

입력
1. 여러개의 테스트 케이스
2. 각 줄은 9개의 문자 포함 X O . 중 하나이다.
3. .은 빈칸, 9개의 문자는 게임판에서 제일 윗 줄 왼쪽부터의 순서
4. 입력의 마지막에는 end가 주어진다.

출력
각 테스트 케이스마다 한 줄에 정답
가능한 경우 valid
불가능한 경우 invalid

풀이
1. while문 true
2. end가 입력 되면 break
3. char[3][3] 입력
4. 게임에서 발생할 수 없는 최종 상태
4.1 X 혹은 O 둘 다 하나에서만 3칸 성공
4.2 한사람이 3개 이상 만든 경우
4.3 한사람이 2개를 만들었는데 중복되는게 없는 경우
4.4 빈칸이 있는데 3개가 완성되지 않은 경우
4.5 X의 개수가 O보다 작거나 2개 이상인 경우
5. 출력
 */
public class Main {
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String input = st.nextToken();
            if(input.equals("end")) break;
            map = new char[3][3];
            boolean answer = true;

            boolean hasBlank = false;
            int first = 0, second = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0;j < 3; j++){
                    map[i][j] = input.charAt(i * 3 + j);
                    if(map[i][j] == 'X') first ++;
                    if(map[i][j] == 'O') second ++;
                    if(map[i][j] == '.') hasBlank = true;
                }
            }

            if(first < second || first > second + 1) answer = false;


            int checkFirst = isValid('X');
            int checkSecond = isValid('O');

            if(checkFirst == -1 || checkSecond == -1) answer = false;
            if(checkFirst + checkSecond > 1) answer = false;
            if(hasBlank && checkFirst + checkSecond == 0) answer = false;
            if(checkFirst == 0 && checkSecond == 1 && first != second) answer = false;
            if(checkFirst == 1 && checkSecond == 0 && first != second + 1) answer = false;

            if(answer) System.out.println("valid");
            else System.out.println("invalid");
        }
    }

    public static int isValid(char word){
        int[][] visited = new int[3][3];

        int count = 0;
        // 가로, 세로
        for(int i = 0; i < 3; i++){
            int row = 0;
            int col = 0;
            for(int j =0; j < 3; j++){
                if(map[i][j] == word) row ++;
                if(map[j][i] == word) col ++;
            }

            if(row == 3) {
                for(int j =0; j < 3; j++){
                    visited[i][j] ++;
                }
                count++;
            }
            if(col == 3) {
                for(int j =0; j < 3; j++){
                    visited[j][i] ++;
                }
                count++;
            }
        }

        // 대각선
        int h1 = 0;
        int h2 = 0;
        for(int i = 0; i < 3; i++){
            if(map[i][i] == word) h1++;
            if(map[2-i][i] == word) h2++;
        }

        if(h1 == 3){
            for(int i = 0; i < 3; i++){
                visited[i][i]++;
            }
            count++;
        }

        if(h2 == 3){
            for(int i = 0; i < 3; i++){
                visited[2-i][i]++;
            }
            count++;
        }

        int same = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(visited[i][j] >= 2) same++;
            }
        }
        //for(int i = 0; i < 3; i++) System.out.println(Arrays.toString(visited[i]));

        if(count == 1) return 1;
        if(count >= 3) return -1;
        if(count == 2 && same == 1) return 1;
        return 0;
    }

}