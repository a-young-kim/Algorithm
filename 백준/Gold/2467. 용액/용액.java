import java.util.*;
import java.io.*;

/*
 * 문제
 * 1. 산성 용액은 1 ~ 10억, 염기성 용약은 -1부터 -10억 의 특성을 가지고 있다. 
 * 2. 두 용액을 혼합한 용약의 특성값은 혼합에에 사용된 용약의 특성의 합으로 정의
 * 3. 같은 양의 두 용약을 혼합하여 특성값이 0에 가장 가까운 용약을 만들고자 한다. 
 * 4. 같은 종류의 용액만들오 0에 가장 가까운 혼함 용약 만드는 경우도 가능
 * 5. 각각 특성값이 정렬된 순서로 주어졌을 때 0에 가장 가까운 용액을 만들어내는 프로그램 작성
 * 
 * 입력
 * 1. 전체 용액 수 N 10만 이하
 * 2. 용액의 특성값  N개의 정수가 오름차순 입력
 * 
 * 출력
 * 두 용액의 특성값을 오름차눗 출력
 * 
 * 아이디어 
 * 절대값 순으로 정렬하여 근처의 값들을 더한다.
 * 
 * 풀이
 * 1. N입력
 * 2. List<Integer> 생성
 * 3. 입력 받는다. 
 * 4. Collection.sorts() 절대값순으로 정렬
 * 5. 좌우의 합을 더하여 절대값이 가장 작은 값 출력
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Integer[] array = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Math.abs(o1) - Math.abs(o2);
			}
		});
		
		int answer = Integer.MAX_VALUE;
		int idx = 0;
		for(int i = 1 ; i < N; i++) {
			
			if(answer >  Math.abs(array[i] + array[i - 1])) {
				answer =  Math.abs(array[i] + array[i - 1]);
				idx = i;
			}
		}
		/*System.out.println(Arrays.toString(array));
		System.out.println(answer);*/
		if(array[idx] < array[idx - 1]) System.out.println(array[idx] + " " + array[idx - 1]);
		else System.out.println(array[idx - 1] + " " + array[idx]);

	}

}