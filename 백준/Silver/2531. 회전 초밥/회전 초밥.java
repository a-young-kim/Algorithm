/*
문제
1. 회전 초밥 벨트 위에 여러 가지 종류의 초밥이 접시에 담겨 있음
1.1 초밥의 종류는 번호로 표시
1.2 벨트 위에는 같은 종류의 초밥이 둘 이상 가능
2. 행사를 통해 매상을 올리려고함
2.1 연석해서 k개의 접시를 먹을 경우 할인 가격
2.2 초밥의 종류 쿠폰을 발행하고 1번 행사에 참가할 경우 해당 초밥 하나를 추가로 무로료 제공
2.2.1 해당 초밥에 벨트에 없으면 요리사가 만들어서 제공
3. 행사에 참여하여 최대한 다양한 종류의 초밥을 먹을 때
    손님이 먹을 수 있는 초밥의 최대가짓수

입력
1. 접시 수 N, 초밥 가지수 d, 연속해서 먹는 접시 수 k, 쿠폰 번호 c
2. 회전 방향을 따라갈 때 초밥의 종류

출력
최밥의 가짓수의 최대값

제약 조건
각각 30,000 이하 => 시간 복잡도 고려해야할 듯

아이디어
투포인터

풀이과정
1. int N, d, k, c 입력
2. int[] arr 입력
3. int[] visited 생성
4. int cnt = getCount(0, k);
5. for start in range(0, N):
5.1 int end = (start + k) % N;
5.2 개수 세서 출력

int getCount(int start, int end)
0. int cnt = 0;
1. for i in range(start, end + 1);
1.1 if(visited[i] == 0) cnt++;
1.2 visited[i] ++;
2. return cnt;
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        visited = new int[d + 1];
        arr = new int[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기화
        int cnt = 0;
        for(int i = 0; i < k; i++){
            int num = arr[i];
            if(visited[num] == 0) cnt++;
            visited[num]++;
        }

        if(visited[c] == 0) cnt ++;
        // 무조건 먹는 초밥
        visited[c] = N + 1;

        int answer = 0;
        for(int start = 0; start < N; start ++){
            answer = Math.max(answer, cnt);

            // 삭제될 부분
            int startNum = arr[start];
            visited[startNum]--;
            if(visited[startNum] == 0) cnt--;

            // 추가될 부분
            int end = (start + k) % N;
            int nextEndNum = arr[end];
            if(visited[nextEndNum] == 0) cnt ++;
            visited[nextEndNum] ++;

        }

        System.out.println(answer);

    }
}
