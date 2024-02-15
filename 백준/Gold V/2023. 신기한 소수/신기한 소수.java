import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리:11596
 * 시간:820
 */
/*
 * 문제
 * 연결된 모든 수가 소수인 숫자를 신기한 소수이다.
 * N자리의 숫자 중에서 어떤 수들이 신기한 소수인지 N자리 신기한 소수를 모두 찾아보자
 * 
 * 입력
 * N 8 이하
 * 
 * 출력
 * 신기한 소수를 오름차순으로 정렬하여 한 줄에 하나씩 출력
 * 
 * 해결방안
 * 2 3 5 7 
 * 한글자, 두글자의 소수를 찾고 뒤에 0부터 9까지 붙여서 또 소수 찾기
 */
public class Main {
	
	static int N;
	static int[] numArray = {1, 3, 7, 9};
	static List<Integer> set = new ArrayList<>();
	static List<Integer> answer = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		set.add(2);
		set.add(3);
		set.add(5);
		set.add(7);
		
		for(int i = 2; i < N + 1; i++) {
			for(int j = 0; j < set.size(); j++) {
				int num = set.get(j);
				for(int k = 0; k < numArray.length; k ++) {
					int num2 = num * 10 + numArray[k];
					if(f(num2)) {
						answer.add(num2);
					}
				}
			}
			set = answer;
			answer = new ArrayList<>();
		}
		
		for(int num: set) {
			System.out.println(num);
		}
	}
	
	public static boolean f(int num) {
		for(int i = 2; i < num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

}