import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {

            int N = Integer.parseInt(br.readLine());
            int bit = 0b0000000000;

            int answer = 0;
            while(bit != 0b1111111111){
                answer++;
                int num = answer * N;

                while(num != 0) {
                    int digit = num % 10;
                    // 해당 위치의 bit를 키다.
                    bit = bit | (1 << digit);
                    num = num / 10;

                }
            }

            System.out.println("#" + test_case + " " + answer * N);
        }
    }
}
