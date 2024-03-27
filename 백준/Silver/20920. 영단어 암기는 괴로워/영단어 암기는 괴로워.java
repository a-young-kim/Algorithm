import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


/*
 * 문제
 * 1. 단어장을 다음과 같은 우선순위를 적용하여 만들고자 한다. 
 * 	- 자주 나오는 단어일수록 앞에 배치
 * 	- 단어의 길이가 길수록 앞에 배치
 * 	- 알파벳 사전순
 * 
 * 2. M 이상의 단어들만 단어장을 만든다. 
 * 
 * 입력
 * 단어의 개수 N (100 000), 단어의 길이 M(10)
 * 외울 단어, 알파벡 소문자
 * 
 * 출력
 * 단어장 앞에서 부터 하나씩 출력
 * 
 * 풀이
 * 1. N, M입력 받음
 * 2. HashMap<String, INteger>에ㅓ key로 단어와 value 단어가 불린 횟수
 * 3. List<List<String>> 에 각 integer 값을 가지는 것 가지고 앞에 지정
 * 4. comparator를 사용
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> hashMap = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			
			if(word.length() < M) continue;
			if(hashMap.containsKey(word)) hashMap.put(word, hashMap.get(word) + 1);
			else hashMap.put(word,  1);
		}
		

		PriorityQueue<Node> pq  = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				
				if(o2.count == o1.count) {
					if(o1.word.length() == o2.word.length()) return o1.word.compareTo(o2.word);
					return o2.word.length() - o1.word.length();
				}
				return o2.count - o1.count;
			}
		});
		
		for(String word: hashMap.keySet()) {
			pq.add(new Node(hashMap.get(word), word));
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			sb.append(node.word + "\n");
		}
		System.out.println(sb);
	}
	
	public static class Node{
		int count;
		String word;
		
		public Node(int count, String word) {
			this.count = count;
			this.word = word;
		}
	}

}