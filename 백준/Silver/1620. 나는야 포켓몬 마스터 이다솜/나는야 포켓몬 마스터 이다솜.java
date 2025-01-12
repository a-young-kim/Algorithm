import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
문제
1. 포겟몬의 번호를 보고 포켓몬 이름을 외우기

입력
1. 도감 포캣몬 개수 N, 내가 맞추어야 하는 문제 M
2. 포켓몬 이름은 전부 영어, 첫글자 대문자
3. 알파벳으로 들어오면 포켓몬 번호 숫자로만 들어오면 포켓몬 번호
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N + 1];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = st.nextToken();
            map.put(arr[i], i);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String problem = st.nextToken();
            if(problem.charAt(0) >= 'A' && problem.charAt(0) <= 'Z'){
                System.out.println(map.get(problem));
            }
            else System.out.println(arr[Integer.parseInt(problem)]);
        }
    }
}
