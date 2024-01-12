import java.util.Scanner;

public class Main {
	public static void setMap(int col, int row, int size, int[][] map) {
		for(int i = col ; i < col + size; i++) {
			for(int j = row ; j < row + size; j++) {
				map[i][j] = 1;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int size = 10;
		int[][] location = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			location[i][1] = sc.nextInt();
			location[i][0] = 100 - size - sc.nextInt();
		}
		
		int[][] map = new int[100][100];
		for(int i = 0; i < location.length; i++) {
			int col = location[i][0];
			int row = location[i][1];
			
			setMap(col, row, size, map);
		}
		
		int answer = 0;
		for(int i = 0 ; i < map.length; i++) {
			for(int j = 0 ; j <  map.length; j++) {
				if(map[i][j] == 1) answer += 1;
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}