import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.xml.sax.InputSource;

/*
 * 문제
 * 과일 하나를 먹으면 길이가 1만큼 늘어남
 * i번째 과일의 높이 h
 * 자신의 길이보다 작어나 같은 높이에 있는 과일을 먹을 수 있다. 
 * 처음 길이가 L일 때 과일들을 먹어 늘릴 수 있는 최대 길이
 * 
 * 입력
 * 과일의 개수 N, 초기 길이 정수 L
 * 과일 높이
 * 
 * 출력
 * 최대 길이
 * 
 * 풀이
 * 작은순으로 정렬해서 먹는다.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);
		
		for(int i = 0; i < N; i++) {
			if(array[i] <= L) L++;
			else break;
		}
		System.out.println(L);
	}

}