import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int max = Math.max(N, M);
		int min = Math.min(N,  M);
		
		int gcb = 100;
		if(max % min == 0) gcb = min;
		else {
			int r = gcb;
			while(r != 0) {
				r = max % min;
				max = min;
				min = r;
			}
			gcb = max;
		}
		System.out.println(gcb + " " + (N * M / gcb));
	}

}