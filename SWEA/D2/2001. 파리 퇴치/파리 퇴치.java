
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			 int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] array = new int[N + 1][N + 1];
            int[][] sumArray = new int[N + 1][N + 1];

            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    array[i][j] = sc.nextInt();
                }
            }
            for(int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    sumArray[i][j] = sumArray[i][j - 1] + sumArray[i - 1][j] - sumArray[i - 1][j - 1] + array[i][j];
                }
            }

            int answer = Integer.MIN_VALUE;
            for(int i = 1; i < N + 1;i++){
                if(i + M == N + 2) break;
               for(int j = 1; j < N + 1; j ++){
                   if(j + M == N + 2) break;
                   answer = Math.max(answer, sumArray[i + M - 1][j + M - 1] + sumArray[i - 1][j - 1] - sumArray[i + M - 1][j - 1] - sumArray[i - 1][j + M - 1]);
               }
            }

            System.out.println("#" + test_case + " " + answer);

		}
	}
}