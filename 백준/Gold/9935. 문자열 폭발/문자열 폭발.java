import java.util.*;
import java.io.*;

/*
문제
1. 폭발 문자열이 폭발하면 해당 문자는 문자열에서 사라지며 문자열을 합쳐짐
2. 폭발 과정
2.1 문자열이 폭발 문자열을 포함한 경우 모든 문자열이 폭발
2.2 남은 문자열을 순서대로 이어붙어 새로운 문자열 제작
2.3 사로 생긴 문자열에 폭발 문자열 존재 가능
2.4 폭발을 해당 문자열이 없을 때까지 계속
3. 모든 폭발이 끝난 후 어떤 문자열이 존재하는지
4. 남은 문자열이 없을 경우 "FRULA" 출력
5. 폭발 문자열은 같은 문자를 두 개 이상 포함 안함

입력
1. 문자열, 문자열의 길이는 백만 이하
2. 폭발 문자열 36 이하
3. 문자열을 소문자, 대문자, 숫자

출력
모든 폭발이 끝난 후 남은 문자열

풀이
1. String word 입력
2. String bomb 입력
3. stack 생성
4. for i in range(0, word.length):
5. stack에 넣기
6. stack에서 bomb개수 만큼 꺼내서 비교
7. bomb과 같으면 제거
8. 같지않으면 넣기
 */
public class Main {
    static boolean[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        char[] words = st.nextToken().toCharArray();
        st= new StringTokenizer(br.readLine());
        char[] bomb = st.nextToken().toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < words.length; i++){
            stack.add(words[i]);
            if(stack.size() < bomb.length) continue;

            Deque<Character> stack2 = new ArrayDeque<>();
            int j = bomb.length - 1;
            for(; j >= 0; j--) {
                stack2.add(stack.pollLast());
                if(bomb[j] != stack2.getLast()) break;
            }

            if( j != -1){
                while(!stack2.isEmpty()) stack.add(stack2.pollLast());
            }
        }
        if(stack.isEmpty()) System.out.println("FRULA");

        else{
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) sb.append(stack.pop());
            System.out.println(sb);
        }

    }
}