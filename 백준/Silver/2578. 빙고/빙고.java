import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int[] getLocation(int num, int[][] binggo) {
		int[] answer = new int[2];
		first: for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(binggo[i][j] == num) {
					answer[0] = i;
					answer[1] = j;
					break first;
				}
			}
		}
		return answer;
	}
	
	public static int checkBinggo(int[] location, int[][] binggo) {
		int count = 0;
		
		// 세로
		int i;
		for(i = 0; i < binggo[0].length; i++) {
			if(binggo[i][location[1]] != -1) break;
		}
		
		if(i == binggo[0].length) count += 1;
		
		// 가로
		for(i = 0; i < binggo[0].length; i++) {
			if(binggo[location[0]][i] != -1) break;
		}
		
		if(i == binggo[0].length) count += 1;
		
		// 우하향
		if(location[0] == location[1]) {
			for(i = 0; i < 5; i++) {
				if(binggo[i][i] != -1) break;
			}
			if(i == binggo[0].length) count += 1;
		}
		
		// 우상향
		if(4 - location[0] == location[1]) {
			for(i = 0; i < 5; i++) {
				if(binggo[i][4 - i] != -1) break;
			}
			if(i == binggo[0].length) count += 1;
		}
		
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[][] binggo = new int[5][5];
		for(int i = 0; i < binggo.length; i++) {
			for(int j =0; j < binggo[i].length; j++) {
				binggo[i][j] = sc.nextInt();
			}
		}
		
		int[] numbers = new int[25];
		for(int i = 0; i < 25; i++) {
			numbers[i] = sc.nextInt();
		}
		
		int binggoCount = 0;
		int i;
		for(i = 0; i < numbers.length; i++) {
			int num = numbers[i];
			int[] location = getLocation(num, binggo);
			binggo[location[0]][location[1]] = -1;
			binggoCount += checkBinggo(location, binggo);
			
			if(binggoCount >= 3) break;
		}
		System.out.println(i + 1);
		sc.close();
	}
}