import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제
 * 정확하게 N 만큼 배달
 * 3키로그램 봉지와 5키로 그램봉지
 * 최대한 적은 봉지를 들고가려고 할때
 * 봉지 몇개를 가져가야하는지
 * 
 * 입력 
 * N 3이상 5000이하
 * 출력
 * 봉지의 최소 개수 정확히 만들수 없다면 -1
 * 
 * 문제
 * while문 반복
 * 5로 나누었을 때 몫의 최대값을 구하고 
 * 몫에서 1씩 빼고 그 나머지가 3으로 나누어 떨어질 경우 답으로 출력
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int five = N / 5;
		int three = N % 5 / 3;
		while(five >= 0) {
			if(five * 5 + three * 3 == N) break;
			
			five--;
			three = (N - five * 5) / 3;
		}
		
		if(five >=0 && five * 5 + three * 3 == N) {
			System.out.println(five +  three);
		}
		else{
			System.out.println(-1);
		}

	}

}