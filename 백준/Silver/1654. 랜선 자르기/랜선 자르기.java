import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 캠프 때 쓸 N개의 랜선
 * 
 * 이미 K개의 랜선을 가지고 있다. 길이는 제각각
 * 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶기 때문에 K개의 랜선을 잘라서 만들어야한다. 
 * 이미 자른 랜선은 붙일 수 없다/.
 * 
 * 기존 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정, 손실도 없음
 * 자를 때는 항상 정수길이만큼자른다
 * N개 이상도 N개에 포함된다.
 * 이때 만들 수 있는 최대 랜선 길이는
 * 
 * 입력
 * 이미 가지고 있는 랜선의 수 K, 필요한 랜선의 개수 N
 * K는 만이하, N은 백만이하 정수 N은 항상 K보다 크거나 같다
 * K줄에 걸쳐 각랜선의 길이
 * 랜선의 길이는 int로 가능
 * 
 * 출력
 * 최대 길이
 * 
 * 풀이
 * 1. 제일 큰 것을 반으로 나누고
 * 
 * 다 더해서 K로 나눈 값에서 각 수에서 몇개씩 나오는지 빼고 개수가 나오지 않으면
 * 나온 나머지 중 가장 큰 값의 몫에서 하나를 더 더해서 다음 최대 길이로 두고 계산
 * 
 */
public class Main {
	
	static long[] array;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		array = new long[K];
		long max = Integer.MIN_VALUE;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  array[i]);
		}
		
		System.out.println(f(1, max, N));

	}
	
	public static long f(long start, long end, int N) {
		long result = 0;
		while(start <= end) {
			long mid = (start + end) / 2;
			int cnt = 0;
			for(int i = 0; i < array.length; i++) {
				cnt += array[i] / mid;
			}
			if(cnt >= N) {
				result = mid;
				start = mid + 1;
			}
			
			else {
				end = mid - 1;
			}
		}
		return result;
	}
}