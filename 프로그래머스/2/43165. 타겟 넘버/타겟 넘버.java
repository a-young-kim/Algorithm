
class Solution {
    int[] numbers;
    int target;
    int answer;
    
    public int solution(int[] numbers, int target) {

        this.numbers = numbers;
        this.target = target;
        
        find(0, 0);
        return answer;
    }
    
    public void find(int idx, int num){
    
        if(idx == numbers.length) {
            if(num == target) answer++;
            return;
        }

        find(idx + 1, num + numbers[idx]);
        find(idx + 1, num - numbers[idx]);

    }
}