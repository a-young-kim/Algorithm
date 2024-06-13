/*
문제
1. 과제는 한 시각되면 시작
2. 새로운 과제를 시작할 시간에 진행 중인 것 멈추고 시작
3. 진행 중 과제 완료 후 멈춘 과제 진행
3.1 새로 시작하는 과제가 있다면 새로 시작
4. 멈춘 과제가 여러개인 경우 최근에 멈춘것 시작

풀이
1. 시간 순으로 정렬 -> 모든 시간을 분으로 만들기
2. stack 생성
3. 다음 시작 시간과 현재 과제 시작 시간 + 완료 값을 비교
4. 비교해서 다음 시작 시간이 짧으면 완료 값 - 이후 시작 값 하여 stack 저장
*/

import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        
        int N = plans.length;
        String[] answer = new String[N];
        Integer[] answerIdx = new Integer[N];
        
        Arrays.sort(plans, (p1, p2) -> p1[1].compareTo(p2[1]));
        
        
        Integer[][] myPlans = new Integer[N][3];
        
        for(int i = 0; i < plans.length; i++){
            myPlans[i][0] = i;
            myPlans[i][1] = setMinute(plans[i][1]);
            myPlans[i][2] = Integer.parseInt(plans[i][2]);
        }

        ArrayDeque<Integer[]> stack = new ArrayDeque<>();
        
        int count = 0;
        for(int i = 0; i < N - 1; i++){
            int before = myPlans[i][1] + myPlans[i][2];
            int leftTime = myPlans[i + 1][1] - before;

            if(leftTime >= 0){
                answerIdx[count ++] = i;
                
                while(!stack.isEmpty()){
                    if(leftTime <= 0) break;
                    Integer[] node = stack.getLast();
                    stack.removeLast();
                    if(leftTime - node[1] >= 0){
                        answerIdx[count ++] = node[0];
                        leftTime = leftTime - node[1];

                    }
                    else{
                        node[1] -= leftTime;
                        stack.add(node);
                        leftTime = 0;
                    }
                }
            }
            else{
                stack.add(new Integer[]{i, myPlans[i][2] - (myPlans[i + 1][1] - myPlans[i][1])});                
            }
            
        }
        answerIdx[count ++] = N - 1;
        
        while(!stack.isEmpty()){
            answerIdx[count++] = stack.getLast()[0];
            stack.removeLast();
        }
        
        for(int i = 0; i < N; i++){
            answer[i] = plans[answerIdx[i]][0];
        }
        return answer;
    }
    
    public int setMinute(String time){
        String[] times = time.split(":");
        
        String hour = times[0];
        String minute = times[1];
        
        return Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }
}