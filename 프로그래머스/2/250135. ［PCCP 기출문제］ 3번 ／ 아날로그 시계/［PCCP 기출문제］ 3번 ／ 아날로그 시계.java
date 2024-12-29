/*
문제
1. 특정 시간 동안 알람이 울린 횟수
2. 초침이 시침 또는 분침과 겹칠 때마다 알람이 울리는 기능

아이디어
시침 -> 1초에 360도/ 60 * 60 * 12
분침 -> 1초에 360 / 60 * 60 
초침 -> 1초에 360 / 60 

소수점 3째짜리까지 세는 것 같음
*/
class Solution {
    final double DEGREE = 360;
    final double HourPerSecond = DEGREE / (60 * 60 * 12);
    final double MinutePerSecond = DEGREE / (60 * 60);
    final double SecondPerSeond = DEGREE / 60;
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int startTime = h1 * 60 * 60 + m1 * 60 + s1;
        int endTime = h2 * 60 * 60 + m2 * 60 + s2;
        
        double hour = HourPerSecond * startTime % DEGREE;
        double minute = MinutePerSecond * startTime % DEGREE;
        double second = SecondPerSeond * startTime % DEGREE;
        
        int time = (h2 - h1) * 60 * 60 + (m2 - m1) * 60 + s2 - s1;
        
        if(hour == minute || minute == second || hour == second) answer++;
        
        while(startTime < endTime){
            
            double nHour = hour + HourPerSecond;
            double nMinute = minute + MinutePerSecond;
            double nSecond = second + SecondPerSeond;
            
            if (Math.abs(nHour - DEGREE) < 1e-6) nHour = 360;
            if (Math.abs(nMinute - DEGREE) < 1e-6) nMinute = 360;
            if (Math.abs(nSecond - DEGREE) < 1e-6) nSecond = 360;
            
            if(second < minute && nSecond >= nMinute) answer++;
            if(second < hour && nSecond >= nHour) answer++;
            if(nMinute == nSecond && nSecond == nHour) answer--;
            
    
            hour = nHour % DEGREE;
            minute = nMinute % DEGREE;
            second = nSecond % DEGREE;
            
            startTime ++;
        }
        return answer;
    }
}