class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
      for(int i = 0; i < numbers.length; i++) {
			long origin = numbers[i];
			
			if(origin % 2 == 0) {
				answer[i] = numbers[i] | 1;
			}
			else {
				long count = 0;
				while(true) {
					if((origin & 1) == 0) {
						answer[i] = numbers[i] | (1L << count);
						answer[i] = answer[i] ^ (1L << (count - 1));
						break;
					}
					
					count++;
					origin = origin >> 1;
				}
			}
			
		}
			
        return answer;
    }
}