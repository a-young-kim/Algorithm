/*
문제
1. 차량별로 주차 요금 계산
1.1. 입차한 후 출차된 내역이 없다면 23.59에 출차
1.2 누적 주차 시간이 기본 시간 이하라면 기본 요금
1.3 기본 시간을 초과하면 단위 시간마다 단위 요금 청그
1.3.1 나누어 떨어지지 않으면올림 
2. 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 return
2.1 차량 번호는 0에서 9로 구성된 4인 문자열

아이디어
입력 시간을 담은 배열 
출력 시간을 담은 배열

풀이과정
1. int[] inputTime = new int[10000]; 생성 => -1로 초기화
2. int[] outputTime = new int[10000]; 생성 => 24 * 60으로 초기화
3. for i in range(0, records.length) 문 돌면서 inputTime, outputTime 입력
4. for i in range(0, 10000) 돌면서 계산 
*/
import java.util.*;
class Solution {
    public static int MAX_NUM = 10000;
    public static int MAX_TIME = 24 * 60 - 1;
    
    public int[] solution(int[] fees, String[] records) {
        
        int[] inputsTime = new int[MAX_NUM];
        int[] outputsTime = new int[MAX_NUM];
        int[] result = new int[MAX_NUM];
    
        Arrays.fill(inputsTime, -1); 
        Arrays.fill(outputsTime, MAX_TIME);
        
        Set<Integer> cars = new HashSet<>();
        for(int i = 0; i < records.length; i++){
            String[] record = records[i].split(" ");
            
            int carNum = Integer.parseInt(record[1]);
            int minute = convertMinute(record[0]);
            
            switch(record[2]){
                case "IN":
                    cars.add(carNum);
                    inputsTime[carNum] = minute;
                    break;
                
                case "OUT":
                    outputsTime[carNum] = minute;
                    result[carNum] += outputsTime[carNum] - inputsTime[carNum];
                    
                    inputsTime[carNum] = -1; 
                    outputsTime[carNum] = MAX_TIME;
                    break;
            }
        }
        
        int[] answer = new int[cars.size()];
        int cnt = 0;
        for(int i = 0; i < MAX_NUM; i++){
            if(!cars.contains(i)) continue;
            if(inputsTime[i] != -1){
                result[i] += outputsTime[i] - inputsTime[i];
            }

            answer[cnt] += calFee(fees, result[i]);
            cnt++;
        }
        
        return answer;
    }
    public int calFee(int[] fees, int time){
        
        if(time < fees[0]) {
            return fees[1];
        }
        
        int remainTime = time - fees[0];
        if(remainTime % fees[2] == 0){
            return fees[1] + remainTime / fees[2] * fees[3];
        }
        
        return fees[1] + (remainTime / fees[2] + 1) * fees[3];
    }
    
    public int convertMinute(String time){
        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        
        return hour * 60 + minute;
    }
}