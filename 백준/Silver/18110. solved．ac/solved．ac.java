import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
문제
1. 아무 의견이 없다면 문제의 난이도는 0
2. 의견이 하나 이상 있다면 문제의 난이도는 모든 사람의 난의도 의견의 30% 절사평균
2.1 절사평균 30% 위의 15% 아래 15%를 제외하고 평균 계산
2.2 반올림

입력
1. 난이도 의견의 개수 n
2. 모든 난이도의 의견은 1이상 30이하

출력
난이도

아이디어 => PQ

풀이과정
1. n 입력
2. int remove = Math.round(n * 0.15)
3. pq 생성 후 입력
4. for(int i = 0; i < remove; i ++) pq.poll();
5. int answer = 0;
6. while(pq.size() > remove) answer += pq.poll();
7. 출력 Math.round(answer / (n - 2 * remove))
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int remove = (int) Math.round(N * 0.15);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            queue.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < remove; i++) queue.poll();

        int answer = 0;
        while(queue.size() > remove){
            answer += queue.poll();
        }

        System.out.println(Math.round((double)answer / (N - 2 * remove)));
    }
}
