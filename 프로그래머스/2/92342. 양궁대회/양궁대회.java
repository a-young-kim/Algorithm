/*
문제
1. 양궁 대회 다음과 같은 규칙
2. 어피치가 n발을 다 쏜 후 라이언이 n발을 쏜다
3. 점수를 계산한다.
4. k점을 더 많은 화살을 맞힌 선수가 k점을 가져간다. 
4.1 단, 같은 경우 어피치가 가져간다.
4.2 하나의 화살도 맞히지 못한 경우 아무도 가져가지 않는다
5. 최종 점수가 더 높은 선수를 우승자로 결정
5.1 최종 점수가 같은 경우 어피치를 우승자로 결정

입력
1. 화살의 개수 n
2. 어피치가 맞힌 과녁 점수 개수 배열

출력
1. 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞추어야 하는지 return
1.1 정수 배열 원소의 총합은 n
1.2 가장 큰 점수 차이로 우승할 수 있는 방법이 여러가지인 경우 가장 낮은 점수를 더 맞힌 경우 return
1.3 10점부터 0점까지 순서대로
2. 우승할 수 없으면 -1 return

제한 사항
1. n은 10 이하

풀이 과정
1. int[] arr = new int[11] 생성
2. info보다 1씩 큰 값을 입력
3. int[] answer = new int[11];
4. f(0, 0)

f(int idx, int num)
0. if(true) return;
1. if(num > 10) return;
2. if(isWin)
2.1 return true;
3. for i in range(idx, -1, -1)
3.1 f(i, num + arr[i])
*/
import java.util.*;
class Solution {
    final int[] info2 = new int[11];
    final int[] save = new int[11];
    int[] answer = new int[11];
    int[] info;
    int n, diff;
    boolean flag = false;
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        for(int i = 0; i < 11; i++){
            info2[i] = info[i] + 1;
        }
        
        f(10, 0);
        
        if(diff == 0) answer = new int[]{-1};
        else{
            int num = Arrays.stream(answer).sum();
            if(num < n) answer[10] += n - num;
        }
        return answer;
    }
    
    public void f(int idx, int num){
        
        if(num > n) return;
        
        int diff2 = getDiff();
        if(diff2 > diff) {
            diff = diff2;
            answer = Arrays.copyOf(save, 11);
        }
        
        for(int i = idx; i >= 0; i--){
            save[i] = info2[i];
            f(i - 1, num + info2[i]);
            save[i] = 0;
        }
        
    }
    
    public int getDiff(){
        int result = 0;
        for(int i = 0; i < 11; i++){
            if(save[i] + info[i] == 0) continue;
            else if(save[i] > info[i]) result += 10 - i;
            else result -= 10 - i;
        }

        return result;
    }
    
}