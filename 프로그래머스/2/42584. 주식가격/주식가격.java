import java.util.Arrays;
class Solution {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[prices.length];

        for(int i = 0; i < length; i++){
            int j;
            for(j = i + 1 ; j < length; j++){
                if(prices[i] > prices[j]){
                    answer[i] = j - i;
                    break;
                }
            }
            if(j == length) answer[i] = j - i - 1;
        }
        return answer;
    }
}