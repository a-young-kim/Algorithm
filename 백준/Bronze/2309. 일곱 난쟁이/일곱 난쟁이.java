import java.util.Arrays;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int[] height = new int[9];
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			height[i] = sc.nextInt();
			sum += height[i];
		}
		// 자바 배열 정렬 오름차순
		Arrays.sort(height);
		int[] removeIndex = new int[2];
		
		first:for(int i = 0; i < 9; i++) {
			for(int j = 1; j < 9; j++) {
				if(sum - height[i] - height[j] == 100) {
					removeIndex[0] = i;
					removeIndex[1] = j;
					break first;
				}
			}
			
		}
		
		for(int i = 0; i < 9; i++) {
			if(i != removeIndex[0] && i != removeIndex[1]) 
				System.out.println(height[i]);
		}
		
	}

}