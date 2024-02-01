import java.util.Arrays;
import java.util.Scanner;

/*
 * 문제
 * DNA 문자열 -> A C G T
 * 임의의 DNA 문자열을 만들고 만들어지 DNA 문자열의 부분 문자열을 비밀번호로 사용
 * 부분 문자열에 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 사용 가능
 * 사용 가능한 비닐번호의 종류를 구하라 위치가 다르면 부분 문자열이 같다고 하더라도 다른 문자 취급
 * 
 * 입력
 * 첫째 줄: 임의로 만든 문자열 길이 S 비밀 번호로 사용할 부분 문자열 길이 P
 * 두번째 줄: 민호가 만든 임의의 문자열
 * 세번째 줄: 부분문자열에 포함되어야할 DNA의 최소 개수 가 공백으로 
 * 
 * 출력 
 * 종류 수 출력
 * 
 * 문제 해결
 * 각 문자열 개수 배열에 저장
 * 
 *1. 해당 문자열을 포함한 배열의 첫번째 index와
 * 마지막 index를 찾고 해당 배열의 크기에 찾아야할 부분 문자열 크기 뺴주기
 * 그 다음에 start index를 시작 인덱스로 주고 다시 찾기
 * 
 */
public class Main {
	
	static int S;
	static int P;
	static char[] arr;
	static int[][] numArr;
	
	static int[] numMin = new int[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.nextInt();
		P = sc.nextInt();
		arr = sc.next().toCharArray();
		numArr = new int[4][S];
		
		for(int i = 0;i <4; i++) {
			numMin[i] = sc.nextInt(); //A C G T
		}
		
		for(int i = 0; i < arr.length; i++) {
			switch(arr[i]) {
			case 'A':
			{	
				numArr[0][i] = 1;
				break;
			}
			case 'C':
			{
				numArr[1][i] = 1;
				break;
			}
			case 'G':
			{
				numArr[2][i] = 1;
				break;
			}
			case 'T':
			{
			numArr[3][i] = 1;
			break;
			}
			}
		}
		
		for(int i = 1; i < arr.length; i++) {
			for(int j = 0; j < 4; j++) {
				numArr[j][i] += numArr[j][i - 1];
			}
		}
		
		sc.close();
		int answer = 0;
		for(int i = 0; i < S; i ++) {
			if(i + P - 1 == S) break;
			int j;
			for(j = 0; j < 4; j ++) {
				if(i == 0) {
					if(numArr[j][i + P - 1] < numMin[j]) break;
				}
				else if(numArr[j][i + P - 1] - numArr[j][i - 1] < numMin[j]) break;
			}
			if(j == 4) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}