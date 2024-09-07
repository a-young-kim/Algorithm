import java.util.*;
import java.io.*;

/*
문제
1. 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이
2. 100,000 십만 이하의 양의 정수로 이루어진 길이가 N인 수열
3. 같은 정수를 K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 프로그램

입력
1. 정수 N 200,000 이만 이하와 K 100 이하
2. 100,000 이하

출력
조건을 만족하는 최장 연속 부분 수열의 길이 출력

아이디어
최장 연속 부분 수열을 구하고
같은 정수의 개수를 세서 K이상이면 뺴준다

풀이
1. N, K 입력
2. int[] arr 입력
3. int start, end = 0으로 초기화
4. int[] count = new int[200001]
5. int answer = 0; count[arr[start]] ++;
6. while(start <= end && end < N)
7. answer = Math.max(answer, end - start)
8. if(start == end) end ++
9. else if(count[arr[end]] > K)
9.1 count[arr[start]]--;
9.2 start++;
10. else count[arr[end]] ++; end ++
 */
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[200001];
        int answer = 1, start = 0, end = 0;
        count[arr[start]]++;

        while(start <= end && end < N){
            if(start == end) {
                end++;
            }
            else if(count[arr[end]] == K){
                count[arr[start]]--;
                answer = Math.max(answer, end - start);
                start++;
            }
            else{
                count[arr[end]]++;
                answer = Math.max(answer, end - start);
                end++;
            }
        }
        answer = Math.max(answer, end - start);
        System.out.println(answer);
    }
}