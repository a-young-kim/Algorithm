import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	
	public static int getMinDistance(int[] start, int[] end) {
		
		int answer = 0;
		
		// 동서로 위치한 경우
		if((start[0] == 0 && end[0] == M)|| (start[0] == M && end[0] == 0)){
			answer = Math.min(M + start[1] + end[1], M + 2 * N - start[1] - end[1]);
		}
		
		// 남북으로 위치한 경우
		else if((start[1] == 0 && end[1] == N)|| (start[1] == N && end[1] == 0)) {
			answer = Math.min(N+ start[0] + end[0], 2 * M + N - start[0] - end[0]);
		}
		
		else {
			answer = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
		}
		
		return answer;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 가로
		M = sc.nextInt(); // 세로
		
		int storeCount = sc.nextInt();
		int[][] delta = { // 열, 행
				{0, -1}, // 북
				{M, -1}, // 남
				{-1, 0}, // 서
				{-1, N} // 동 
		};
		
		int[][] location = new int[storeCount + 1][2];
		// 마지막에 start 위치 저장 => 좌표 구함
		for(int i = 0; i < storeCount + 1; i++) {
			int first = sc.nextInt() - 1;
			int second = sc.nextInt();
			
			if(delta[first][0] == -1) { // 세로가 -1이면 서 동
				location[i][0] = second;
				location[i][1] = delta[first][1];
			}
			else {
				location[i][1] = second;
				location[i][0] = delta[first][0];
			}
		}
		
		int answer = 0;
		for(int i = 0; i < storeCount; i++) {
			answer += getMinDistance(location[location.length - 1], location[i]);
		}
		System.out.println(answer);
		sc.close();
		
	}

}