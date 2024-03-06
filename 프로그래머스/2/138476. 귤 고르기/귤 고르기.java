import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for(int i = 0; i < tangerine.length; i++) {
			if(hashMap.containsKey(tangerine[i])) {
				hashMap.replace(tangerine[i], hashMap.get(tangerine[i]) + 1);
			}
			else hashMap.put(tangerine[i],  1);
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>()        {  
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2- o1;
			}
		});
		
		for(Integer key: hashMap.keySet()) {
			queue.add(hashMap.get(key));
		}
		
		int count = 0;
		while(!queue.isEmpty()) {
			answer ++;
			count += queue.poll();
			if(count >= k) break;
		}
        return answer;
    }
}