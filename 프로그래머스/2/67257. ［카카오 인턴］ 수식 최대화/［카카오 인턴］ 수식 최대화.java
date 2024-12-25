/*
문제
1. + - * 연산 수식이 전달되며 순서를 자유롭게 재정의하여 가장 큰 숫자 제출
2. 두 개 이상의 연산자가 동일한 우선순위를 가지도록 정의할 수 없음
3. 계산된 결과가 음수라면 절댓값으로 변환하여 제출
4. 우승 시 받은 수 있는 가장 큰 금액을 return

제한 사항
1. 3이상 100이하 문자열
2. 공백 괄호 없이 숫자와 3가지 연산자

*/

import java.util.*;

class Solution {
    String[][] order = {
        {"*", "+", "-"},
        {"*", "-", "+"},
        {"+", "*", "-"},
        {"+", "-", "*"},
        {"-", "+", "*"},
        {"-", "*", "+"}
    };

    public long solution(String expression) {
        long answer = 0;

        Deque<String> deque = new ArrayDeque<>();
        String word = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '*' || c == '+' || c == '-') {
                deque.add(word);
                deque.add(Character.toString(c));
                word = "";
            } else {
                word += c;
            }
        }
        deque.add(word);

        for (int i = 0; i < 6; i++) {
            Deque<String> tempDeque = new ArrayDeque<>(deque);
            for (int j = 0; j < 3; j++) {
                Deque<String> calcDeque = new ArrayDeque<>();
                while (!tempDeque.isEmpty()) {
                    String token = tempDeque.poll();
                    if (token.equals(order[i][j])) {
                        long first = Long.parseLong(calcDeque.pollLast());
                        long second = Long.parseLong(tempDeque.poll());
                        long result = 0;
                        switch (order[i][j]) {
                            case "+":
                                result = first + second;
                                break;
                            case "*":
                                result = first * second;
                                break;
                            case "-":
                                result = first - second;
                                break;
                        }
                        calcDeque.add(Long.toString(result));
                    } else {
                        calcDeque.add(token);
                    }
                }
                tempDeque = new ArrayDeque<>(calcDeque);
            }

            long result = Math.abs(Long.parseLong(tempDeque.poll()));
            answer = Math.max(answer, result);
        }

        return answer;
    }
}
