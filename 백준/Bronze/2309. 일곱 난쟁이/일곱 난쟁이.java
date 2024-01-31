import java.util.Arrays;
import java.util.Scanner;

/*
 * 문제
 * 난쟁이가 7명이 아닌 9명인 문제
 * 난쟁이의 키의 합이 100
 * 
 * 입력 
 * 아홉 줄에 걸쳐 난쟁이의 키, 키는 100을 넙지 않는다
 * 
 * 출력
 * 정답이 여러개인 경우에넌 아무거나 오름차순으로 출력
 * 
 * 구현 방법
 * 입력을 배열로 받아서 재귀
 * 파라미터로 index 번호와 현재 더한 것의 개수 출력
 * 합이 100보다 크거나 개수가 7이면 return
 * 재귀로 돌릴 때 array cnt에 값 입력
 */
public class Main {
	
	static int[] height = new int[9];
	static int[] notInclude = new int[2];

	public static int find(int sum, int index, int cnt) {
		if(cnt == 2 && sum == 100) return sum;
		if(cnt == 2) return sum;
		
		for(int i = index ; i < 9; i++) {
			notInclude[cnt] = height[i];
			if(find(sum - height[i], index + 1, cnt + 1) == 100) {
				return 100;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int sum = 0;
	
		for(int i = 0; i < 9; i++) {
			height[i] = sc.nextInt();
			sum += height[i];
		}
		
		find(sum, 0, 0);
		Arrays.sort(height);
		for(int i = 0; i < 9; i ++) {
			if(height[i] == notInclude[0]) continue;
			if(height[i] == notInclude[1]) continue;
			
			System.out.println(height[i]);
		}
		sc.close();
	}
}