/*
문제
1. 둥글게 앉아서 수. 둥글게 앉아서 숫자를 말하는 게임
2. 규칙은 다음과 같음
2.1 0부터 시작하여 차례로 말함 0부터 9까지
2.2 10 이상의 숫자부터 한자리씩 끝어서 말함
3. 코딩 동아리의 인원은 이진수로 이 게임을 진행
4. 난이도를 높이기 위해 이진법에서 십육진법까지 모든 진법으로 게임 진행

입력
1. 진법 n, 미리 구할 숫자 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p

출력
튜브가 말해야하는 숫자 t개를 공백 없이 차례대로 나태낸 문자열
단 10 ~ 15는 대문자 A ~ F로 출력

풀이과정
1. while(answer.length < t), int cnt = 0
1.1. String number = change(n)
1.2 for i in range(0, number.length)
1.3 char c = number.charAt(i);
1.4 cnt ++;
1.5 if(cnt % t == 0) answer += c;
2. return answer

change(int n)
1. String number = ""
2. while(n < t)
2.1 int next = n % t
2.2. if(next >= 10) 이면 A B C D E F로
3. if(next >= 10) 이면 A B C D E F로

*/
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        
        int cnt = 1;
        int num = 0;
        while(answer.length() < t){
            String number = change(num, n);
            for(int i = 0; i < number.length(); i++){
                if(answer.length() == t) break;
                
                char c = number.charAt(i);
                
                if(cnt == p) {
                    answer.append(c);
                }
                
                cnt ++;
                if(cnt == m + 1) cnt = 1;
            }
            num ++;
        }
        return answer.toString();
    }
    
    public String change(int a, int b){
        StringBuilder number = new StringBuilder();
        
        int num = a;
        while(num >= b){
            int c = num % b;
            
            if(c >= 10){
                if(c == 10) number.append("A");
                else if(c == 11) number.append("B");
                else if(c == 12) number.append("C");
                else if(c == 13) number.append("D");
                else if(c == 14) number.append("E");
                else number.append("F");
            }
            else number.append(c);
            
            num = num / b;
        }
        
       if(num >= 10){
            if(num == 10) number.append("A");
            else if(num == 11) number.append("B");
            else if(num == 12) number.append("C");
            else if(num == 13) number.append("D");
            else if(num == 14) number.append("E");
            else number.append("F");
        }
        else number.append(num);
        
        return number.reverse().toString();
        
    }
}