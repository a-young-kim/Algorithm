/*
문제
1. N개의 집과 M개의 길, 양방향, 길의 weight 존재
2. 두 집 사이의 경로가 항상 존재
3. 마을을 두 개의 마을로 분리
4. 분리된 마을 안에 있는 임의의 두 집 사이의 경로가 항상 존재
5. 분리된 마을 사이의 길들은 없애기
6. 마을 안에서도 임의의 두 집 사이의 경로가 항상 존재하면 더 없애기
7. 길의 유지비의 합을 최소

입력
1. N M
2. M 줄에 걸쳐서 A B C, A번 집과 B번 집을 연결하는 길의 유지비가 C라는 뜻

제약 조건
N은 십만 이하 M은 백만이하

풀이과정
1. int N, M 입력
2. PQ<Node> queue 생성
3. for i in range(0, M):
3.1 queue.add(입력 넣기)
4. int answer = 0;
5. int[] arr = new int[N + 1]
6. for i in range(0, N) union[i] = i;
7. while(!queue.isEmpty())
7.1 Node node = queue.poll();
7.2 if(find[node.A] == find[node.B]) continue;
7.3 union(node.A, node.B)
7.4 answer += node.weight;
7.5 if(check()) return;

def find(int num)
if(num == arr[num]) return num;
return find(arr[num])

def union(int A, int B)
int rootA = find(A);
int rootB = find(B);

if(rooA < rootB) arr[B] = rootA
else arr[A] = rootB

def check()
int cnt = 0;
Set<int> set
for i in range(1, N + 1)
set.add(i)

return set.size() == 2
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M, cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return o1.weight - o2.weight;
            }
        });

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            Node node = new Node();
            node.A = Integer.parseInt(st.nextToken());
            node.B = Integer.parseInt(st.nextToken());
            node.weight = Integer.parseInt(st.nextToken());
            queue.add(node);
        }

        arr = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            arr[i] = i;
        }

        long answer = 0;
        int cnt = N;

        while(!queue.isEmpty() && cnt > 2){
            Node node = queue.poll();

            if(find(node.A) == find(node.B)) continue;
            union(node.A, node.B);
            answer += node.weight;
            cnt --;
        }

        System.out.println(answer);
    }

    public static void union(int A, int B){
        int rootA = find(A);
        int rootB = find(B);

        if(rootA < rootB) arr[rootB] = rootA;
        else arr[rootA] = rootB;
    }

    public static int find(int num){
        if(arr[num] == num) return num;
        return arr[num] = find(arr[num]);
    }

    public static class Node{
        int A, B, weight;

        @Override
        public String toString() {
            return "A: " + A + " B: " + B +" weight: " + weight;
        }
    }
}
