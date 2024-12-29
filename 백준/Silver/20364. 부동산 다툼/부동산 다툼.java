import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
문제
1. 이진 트리 모양의 땅에 오리
2. 루트는 1
3. 어떤 땅의 번호가 K라면 왼쪽 땅은 2K 오른쪽 땅은 2K + 1
4. 오리들끼리 싸움이 나서 해결하려고 한다
5. 오리를 한 줄로 대기시킨다 맨 처음 오리들은 1번 땅에 있다
6. 오리들이 서있는 순서대로 원하는 땅을 가지도록 한다
7. 한 오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 있다면 땅을 가지지 못함
8. 각 오리가 원하는 땅을 가질 수 있는지 가질 수 없다면 처음 마주치는 점유되 땅 번호

입력
1. 땅 개수 N 오리 수 Q
2. 각 오리가 원하는 땅번호

출력
원하는 땅에 갈 수 있다면 0을 없다면 처음 마주치는 점유된 땅 번호

제약 조건
N 은 2의 20 승 -> 시간복잡도 고려
Q는 이만 이하

풀이 과정
1. long N Q 입력
2. long[] end = new long[Q] 선언 후 입력
3. long[] answer = new long[Q]
4. HashMap<Long, Int> visited 생성 -> 배열을 쓰면 너무 커질까봐
5. Deque<Long> nodes 생성
6. for i in range(0, Q)
6.1 long endNum = end[i]
6.2 while(endNum > 0)
6.2.1 nodes.add(endNum)
6.2.2 if(endNum % 2 == 0) endNum = endNum / 2
6.2.3 else endNum = (endNum - 1) / 2
6.4.while(!nodes.isEmpty())
6.4.1 long node = nodes.pollLast()
6.4.2 if(!visited.containKeys(node)) continue
6.4.3 answer[i] = node break
7. for i in range(0, Q) answer[i] 출력
 */
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long[] end = new long[Q];
        long[] answer = new long[Q];
        HashMap<Long, Integer> visited = new HashMap<>();
        Deque<Long> nodes = new ArrayDeque<>();

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            end[i] = Long.parseLong(st.nextToken());
        }

        for(int i = 0; i < Q; i++){
            long endNum = end[i];

            while(endNum > 0){
                nodes.add(endNum);

                if(endNum % 2 == 1) endNum--;

                endNum = endNum / 2;
            }

            boolean isReach = true;
            while(!nodes.isEmpty()){
                long node = nodes.pollLast();

                if(!visited.containsKey(node)) continue;
                answer[i] = node;
                isReach = false;
                break;
            }
            if(isReach) visited.put(end[i], 1);
            nodes.clear();
        }

        for(int i = 0; i < Q; i++){
            System.out.println(answer[i]);
        }
    }
}
