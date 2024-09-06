import java.util.*;
import java.io.*;

/*
문제
1. 정육면체 모양의 상자 일렬
2. 앞에 있는 상자의 크기가 뒤의 상자의 크기보다 작으면
    앞의 상자를 뒤의 상자에 넣을 수 있음
3. 상자의 크기가 주어질 때 한 번에 넣을 수 있는 최대 상자 개수를 출력

입력
1. 상자의 개수 n, 1이상 1000이하
2. 각 상자의 크기가 순서대로

출력
한줄에 넣을 수 있는 최대의 상자 개수 출력

아이디어
해당 위치에 들어올 수 있는 최소 값을 찾아 넣기(?)

풀이
1. n 입력
2. int[] arr 입력
3. int[] dp 생성
4. int end = 0, dp[0] = arr[0]
5. for i in range(1, n):
5.1 이분탐색 f(0, end, arr[i])
6. for i in range(0, n):
6.1 if(dp[i] == 0) break;
7. answer = i;


f(int start, int end, int num)
1. int mid = (start + end) / 2;
2. while(start != end):
3. if(dp[mid] > num) f(start, mid - 1, num)
4. else if(dp[mid] < num) f(mid + 1, start, num)
5. else mid ++; break;
6. if(mid == 0)
6.1 if(dp[0] > num) return 0
6.2 else return 1
7. if(mid == end)
7.1 if(dp[end] <= num) return end + 1;

*/
public class Main {
    static int[] arr, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        dp[0] = arr[0];
        int end = 0;
        for(int i = 1; i < N; i++){
            int num = f(0, end, arr[i]);
            dp[num] = arr[i];
            end = Math.max(end, num);
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            if(dp[i] == 0) break;
            answer++;
       }
        System.out.println(answer);
    }

    public static int f(int start, int end, int num){

        int mid = 0;
        while(start <= end){
            mid = (start + end) / 2;
            if(dp[mid] > num){
                end = mid - 1;
            }
            else if(dp[mid] < num){
                start = mid + 1;
            }
            else{
                break;
            }
        }

        if(mid == start && dp[mid] < num) mid ++;
        else if(mid == end && dp[mid] < num) mid ++;
        return mid;
    }
}