import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
/*
문제
1.  D, S, L, R을 이용한 계산기
1.1 레지스터에는 0 이상 만 이하 십진 수 저장
2. D, S, L, R 계산 방법이 있다
3. 두 정수 사이에 A와 B에 대해서 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램

입력
1. T개 케이스

아이디어
bfs
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 0; test_case < T; test_case ++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            Node[] answer = new Node[10001];
            answer[A] = new Node(null, A,'k');

            Queue<Node> queue = new ArrayDeque<>();
            queue.add(answer[A]);

            first : while(!queue.isEmpty()){
                Node node = queue.poll();

                for(int i = 0; i < 4; i++){
                    int next = cal(node.num, i);
                    char nextWord = addChar(i);

                    if(next > 10000 || next < 0) continue;
                    if(answer[next] != null) continue;

                    answer[next] = new Node(node, next, nextWord);
                    if(next == B){
                        sb.append(getAnswer(answer, B));
                        sb.append("\n");
                        break first;
                    }
                    queue.add(answer[next]);
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static String getAnswer(Node[] answer, int result){

        Node node = answer[result];
        StringBuilder sb = new StringBuilder();

        while(node != null && node.parent != null){
            sb.append(node.alphabet);
            node = node.parent;
        }
        return sb.reverse().toString();
    }

    public static int cal(int num, int idx){

        switch (idx){
            case 0:
                return num * 2 % 10000;
            case 1:
                return num == 0 ? 9999 : num - 1;
            case 2:
                return num % 1000 * 10 + num / 1000;
            default:
                return num % 10 * 1000 + num / 10;

        }
    }

    public static Character addChar(int num){
        switch (num){
            case 0:
                return 'D';
            case 1:
                return 'S';
            case 2:
                return 'L';
            default:
                return 'R';
        }
    }

    public static class Node{
        Node parent;
        int num;
        char alphabet;

        public Node(Node parent, int num, char alphabet){
            this.parent = parent;
            this.num = num;
            this.alphabet = alphabet;
        }

        public String toString(){
            return " " + this.alphabet;
        }
    }
}
