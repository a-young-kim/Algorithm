

import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long[][] arr = new long[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
		}
		
		long sum = 0;
		for(int i = 0; i < N - 1; i++) {
			sum += arr[i][0] * arr[i + 1][1] - arr[i][1] * arr[i + 1][0];
		}
		
		sum += arr[N - 1][0] * arr[0][1] - arr[N - 1][1] * arr[0][0];

		double answer = Math.abs(sum) / 2.0;
		System.out.printf("%.1f", answer);
	}

}
