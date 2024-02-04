import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 게임 프로토 타입
 * 전차는 하나 적이나 아군으로 만들어진 전차 없음
 * 사용자의 입력에 따라 다양한 동작
 * 
 * 문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

포탄은 벽과 충돌하거나 맵밖으로 나감, 포탄이 벽에 부딪치면 벽은 파괴 후 평지
강철로 만들어진 벽은 아무일도 없으
모든 입력 처리 후 맵 상태 구함

입력
테스트 케이스T
두 정수 H(높이), W(넓이)
H 줄에 길이가 W인 문자열
사용자가 넣을 입력의 개수의 정수 N
길이가 N인 문자열

출력
#테스트케이스 게임맵

해결 방법
큐로 받아서while문 돌

 */
public class Solution {

    static int H, W;
    static char[][] array;
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {

            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            array = new char[H][W];
            int r = 0, c = 0;
            String look = null;

            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                for(int j = 0; j < W; j++) {
                    array[i][j] = s.toCharArray()[j];
                    if(array[i][j] == '^') look = "U";
                    else if(array[i][j] == 'v') look = "D";
                    else if(array[i][j] == '<') look = "L";
                    else if(array[i][j] == '>') look = "R";
                    else continue;
                    r = i;
                    c = j;
                }
            }

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            Queue<String> q = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            char[] chars = st.nextToken().toCharArray();
            for(char ch: chars) {
                q.offer(String.valueOf(ch));
            }

            while(q.size() != 0) {
                String s = q.poll();
                switch(s) {
                    case "U":
                        if (r != 0 && array[r - 1][c] == '.') {
                            array[r][c] = '.';
                            r--;
                        }
                        array[r][c] = '^';
                        break;
                    case "D":
                        if (r != H - 1 && array[r + 1][c] == '.') {
                            array[r][c] = '.';
                            r++;
                        }
                        array[r][c] = 'v';
                        break;
                    case "L":
                        if (c != 0 && array[r][c - 1] == '.') {
                            array[r][c] = '.';
                            c--;
                        }
                        array[r][c] = '<';
                        break;
                    case "R":
                        if (c != W - 1 && array[r][c + 1] == '.') {
                            array[r][c] = '.';
                            c++;
                        }
                        array[r][c] = '>';
                        break;
                    case "S":
                        shoot(r, c, look);
                        break;
                }

                if(!s.equals("S")) look = s;
            }

            System.out.printf("#" + test_case +" ");
            for(char[] a : array){
                for(char b: a){
                    System.out.print(b);
                }
                System.out.println();
            }
        }
    }

    public static void shoot(int row, int col, String look) {
        switch(look) {
            case "U":
                for(int i = row - 1; i >= 0 ; i--) {
                    if(changeArray(i, col)) break;
                }
                break;
            case "D":
                for(int i = row  + 1; i < H ; i++) {
                    if(changeArray(i, col)) break;
                }
                break;
            case "L":
                for(int i = col - 1; i >= 0; i--) {
                    if(changeArray(row, i)) break;
                }
                break;
            case "R":
                for(int i = col + 1; i < W; i++) {
                    if(changeArray(row, i)) break;
                }
                break;
        }
    }

    public static boolean changeArray(int row, int col){
        if(array[row][col]  == '#')   return true;
        else if(array[row][col] == '*') {
            array[row][col] = '.';
            return true;
        }
        return false;
    }
}