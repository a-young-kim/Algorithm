import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제
1. 초기에 n + 1 개의 집합
2. 합집합 연산과 두 원소가 같은 집합에 포함

입력
1. n, m 주어짐
1.1 m은 입력으로 주저지는 연산의 개수
2. 합집합은 0 a b 형태로 입력이 주어짐
2.1 a가 포함되어 있는 집합과, b가 포합되어 있는 집합을 합친다는 의미
3. 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b 형태

출력
1. 1로 시작하는 입력에 대해서 a, b가 같은 집합에 포함되어 있으면 yes 아니면 no

아이디어
유니온 파인드

풀이
1. int N, M 입력
2. for i in range(0, M):
2.1 int num, a, b <= 입력
2.2 switch(input[0])
2.2.1 case 1: 확인
2.2.2 case 0: 합치기
 */
public class Main {
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        root = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            root[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (num){
                case 1:
                    int aRoot = find(a);
                    int bRoot = find(b);
                    if(aRoot == bRoot){
                        sb.append("YES");
                    }
                    else{
                        sb.append("NO");
                    }
                    sb.append("\n");
                    break;
                case 0:
                    union(a, b);
            }
        }

        System.out.println(sb.toString());
    }

    static int find(int num){
        if(root[num] == num) return num;
        return root[num] = find(root[num]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot > bRoot){
            root[aRoot] = bRoot;
        }
        else{
            root[bRoot] = aRoot;
        }
    }
}
