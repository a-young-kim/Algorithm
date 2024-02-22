import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int solution(int N, int[][] road, int K) {

        HashMap<Integer, HashMap<Integer, Integer>> hashMap = new HashMap<>();
        for(int i = 0;i < road.length; i++) {
			int a = road[i][0];
			int b = road[i][1];
			int c = road[i][2];
			
			if(!hashMap.containsKey(a)) {
				HashMap<Integer, Integer> h = new HashMap<>();
				h.put(b, c);
				hashMap.put(a, h);
			}
			else {
				HashMap<Integer, Integer> h = hashMap.get(a);
				if(!h.containsKey(b)) h.put(b, c);
				else h.put(b, Math.min(h.get(b), c));
			}
			
			if(!hashMap.containsKey(b)) {
				HashMap<Integer, Integer> h = new HashMap<>();
				h.put(a, c);
				hashMap.put(b, h);
			}
			else {
				HashMap<Integer, Integer> h = hashMap.get(b);
				if(!h.containsKey(a)) h.put(a, c);
				else h.put(a, Math.min(h.get(a), c));
			}

		}
        
        long[] answer = new long[N + 1];
		for(int i = 1; i < N + 1; i++) {
			answer[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return (int)((int)o1.weight - o2.weight);
			}
		});
		
		queue.add(new Node(1, 0));
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		answer[1] = 0;
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			if(!hashMap.containsKey(n.node)) continue;
			HashMap<Integer, Integer> hashMap2 = hashMap.get(n.node);
			for(int key: hashMap2.keySet()) {
				if(answer[n.node] + hashMap2.get(key) < answer[key]) {
					answer[key] = Math.min(answer[n.node] + hashMap2.get(key), answer[key]);
					queue.add(new Node(key, answer[key]));
				}
            }
		}
		
		int count = 0;
		for(int i = 1; i < N + 1; i++) {
			if(answer[i] <= K) count++;
		}
		
		return count;
    }
    
    public static class Node{
		int node;
		long weight;
		
		public Node(int node, long weight) {
			this.node = node;
			this.weight = weight;
		}
	}
}