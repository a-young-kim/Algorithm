
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] visited = new int[10];

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int end = 2; // end는 포함 안함
        visited[arr[1]] = 1;
        int cnt = 1;
        for(int start = 1; start < N + 1; start++){
            while(end < N + 1){
                // end 포함 여부 확인
                if(visited[arr[end]] == 0) {
                    if(cnt == 2) break;
                    cnt ++;
                }
                visited[arr[end]] ++;
                end ++;
            }

            // end 포함 안 함
            answer = Math.max(answer, end - start);


            visited[arr[start]]--;
            if(visited[arr[start]] == 0) cnt--;
        }

        System.out.println(answer);
    }
}