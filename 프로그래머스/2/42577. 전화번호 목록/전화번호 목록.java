import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < phone_book.length; i++) {
			hashMap.put(phone_book[i], 1);
		}

		
		first: for(int i = 0; i < phone_book.length; i++) {
			for(int j = 1; j < phone_book[i].length(); j++) {
				if(hashMap.containsKey(phone_book[i].substring(0, j))) {
					answer = false;
					break first;
				}
			}
		}
        return answer;
    }
}