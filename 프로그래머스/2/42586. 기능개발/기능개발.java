import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        
        Queue<Integer> qProgresses = new ArrayDeque<>();
        Queue<Integer> qSpeeds = new ArrayDeque<>();
        for(int i = 0; i < progresses.length; i++){
            qProgresses.add(progresses[i]);
            qSpeeds.add(speeds[i]);
        }
        int cnt = 0;
        int idx = 0;
        while(!qProgresses.isEmpty()){
            double p = qProgresses.poll();
            double s = qSpeeds.poll();
            
            if(p + s * cnt < 100)
                cnt = (int)Math.ceil((100.0 - p) / s);
            
            answer[idx] = cnt;
            idx++;
        }
        int[] answer2 = new int [(int)Arrays.stream(answer).distinct().count()];
        answer2[0] = 1;
        idx = 0;
        for(int i = 1; i < answer.length; i++){
            if(answer[i - 1] == answer[i]){
                answer2[idx] ++;
            }
            else{
                answer2[++idx] = 1;
            }
        }
        
        return answer2;
    }
}