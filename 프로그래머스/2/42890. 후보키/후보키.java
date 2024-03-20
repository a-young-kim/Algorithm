import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    List<List<String>> relationData = new ArrayList<>();
    public int solution(String[][] relation) {
       int row = relation.length, col = relation[0].length;
        for(int i = 0; i < col + 2; i++) {
			relationData.add(new ArrayList<>());
		}
        f(0, col, "");
        
        List<String> answer = new ArrayList<>();
		
		for(int i = 1; i < relationData.size(); i++) {
			List<String> list = relationData.get(i);
			
			first: for(String spareKey: list) {
				// 해당 key가 최소성르 만족시키는지 확인
				for(String key: answer) {
					String key2 = "(.*)";
					for(char c: key.toCharArray()) key2 += c + "(.*)";
					if(spareKey.matches(key2)) continue first;
				}

				Set<String> set = new HashSet<>();
				for(int j = 0; j < row; j++) {
					String word = "";
					for(char c: spareKey.toCharArray()) {
						word += relation[j][(int) c - '0'];
					}
					set.add(word);
				}
				if(set.size() == row) {
					answer.add(spareKey);
				}
			}
		}
		return answer.size();
        
    }
    
    public  void f(int cnt, int num, String word) {
        relationData.get(word.length()).add(word);
        for(int i = cnt; i < num; i++) {
            f(i + 1, num, word + i);
        }
	}
}