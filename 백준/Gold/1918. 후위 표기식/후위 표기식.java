/*
스택 자료구조를 사용해서 풀어야하는 문제이다
- 스택에는 연산자만 사용하고 피연산자는 바로바로 출력해야한다
- 연산자 우선순위를 지정하여 stack에 넣기 전에, 현재 연선자의 우선순위보다
    큰 연산자가 stack의 맨 위에 있다면 없을 때까지 pop을 한다
- ) 일 경우 (가 나올 때까지 stack 안의 연산자를 pop한다
- 피연산자는 따로 스택에 넣지 않고 그때그때 그냥 sb에서 append해준다
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String formula = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < formula.length(); i++){
            char now = formula.charAt(i);

            switch (now){
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority(stack.peekLast()) >= priority(now)){
                        sb.append(stack.pollLast());
                    }
                    stack.add(now);
                    break;
                case '(':
                    stack.add(now);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peekLast() != '('){
                        sb.append(stack.pollLast());
                    }
                    stack.pollLast();
                    break;
                default:
                    sb.append(now);
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        System.out.println(sb.toString());
    }
    public static int priority(char operator){
        if(operator == '(' || operator == ')') return 0;
        else if(operator == '+' || operator == '-') return 1;
        else if(operator == '*' || operator == '/') return 2;
        return -1;
    }
}