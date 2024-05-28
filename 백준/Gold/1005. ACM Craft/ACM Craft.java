import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
문제
1. 특정 건물을 가장 빨리 지을 때 걸리는 최소 시간
2. 부모들이 모두 다 건물을 지어야 자식도 건물을 지을 수 있다.

입력
1. 테스트개수 T
2. 건물 개수 N, 건설 순서 규칠 K
3. 각 건물당 건설에 걸리는 시간
4. 건설 순서 X Y, X를 지은 다음에 Y지어야함
5. 승리하기 위해 건설해야할 건물 번호

출력
최소 시간

풀이
1. T입력
2. N K 입력
3. int[N + 1] building 생성 후 입력
4. List<List> 생성, int[N + 1] counts 생성
5. for문 시작
    5.1 X, Y 입력
    5.2 list.get(X).set(Y)입력
    5.3 int[Y] ++
6. int[N + 1] time 생성
7. dfs 시작

dfs(int my, int parent)
1. counts[X]가 0인 것 부터 확인
2. 해당 값이 0면 time을 building[my] + time[parent]함
3. list for문 돌려서 자식을 세팅


 */
public class Main {
    static boolean[] visited;
    static List<List<Integer>> edge, reverseEdge;
    static int[] builing, time, counts;
    static int N, K, W;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 1; test_case < T + 1; test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            builing = new int[N + 1];
            time = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N + 1; i++){
                builing[i] = Integer.parseInt(st.nextToken());
            }

            edge = new ArrayList<>();
            reverseEdge = new ArrayList<>();
            for(int i = 0; i < N + 1; i++) {
                edge.add(new ArrayList<>());
                reverseEdge.add(new ArrayList<>());
            }

            counts = new int[N + 1];
            for(int i = 0;i < K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                edge.get(X).add(Y);
                reverseEdge.get(Y).add(X);
                counts[Y] ++;
            }

            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1];
            for(int i = 1; i < N + 1; i++){
                if(counts[i] != 0) continue;
                dfs(i);
            }

            System.out.println(time[W]);
        }
    }

    public static void dfs(int my){

        if(visited[my]) return;
        visited[my] = true;
        List<Integer> parentEdge = reverseEdge.get(my);
        if(parentEdge.isEmpty()) time[my] = builing[my];
        for(Integer parent: parentEdge){
            time[my] = Math.max(builing[my] + time[parent], time[my]);
        }

        List<Integer> myEdge = edge.get(my);
        for(Integer child: myEdge){
            counts[child]--;
            if(counts[child] == 0) dfs(child);
        }

    }
}