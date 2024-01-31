import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * N * N개의 수가 표에 채워져 있음
 * x1, y1 에서부터 x2, y2까지의 합
 * 
 * 입력
 * 첫째 줄: 표의 크기N, 합을 구해야하는 횟수M
 * 두번줄부터 N번째 줄: 표가 1행부터 차례로
 * M개의 줄에는 4개의 정수 x1 x2 y1 y2
 * 표의 수는 1000보다 작거나 같은 자연수
 * 
 * 출력
 * M개의 줄에 걸쳐 x1 y1 x2 y2
 * 
 * 아이디어 
 * 사각형 합을 구함
 * index를 보다 작거나 가진 숫자의 합을 가지고 있고
 * (x,y) + (x-1, y) +(x, y-1) -(x-1, y-1)
 *i번째 부터 j번째 까지의 합
 * x2, y2 - (x2)(y1 -1) - (x1 -1)y2 + (x1-1)(y1 -1) 
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // N과 M 입력
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        // 2차원 배열 생성
        int[][] sumArray = new int[N + 1][N + 1];

        // 배열 초기화
        for (int i = 1; i < N + 1; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j < N + 1; j++) {
                sumArray[i][j] = Integer.parseInt(tokenizer.nextToken()) +
                        sumArray[i - 1][j] + sumArray[i][j - 1] - sumArray[i - 1][j - 1];
            }
        }

        // 쿼리 처리
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            // 결과 출력
            System.out.println(sumArray[x2][y2] - sumArray[x2][y1 - 1] - sumArray[x1 - 1][y2] + sumArray[x1 - 1][y1 - 1]);
        }

        // BufferedReader를 닫아줌
        bufferedReader.close();
    }
}