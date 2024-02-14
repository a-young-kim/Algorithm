import java.util.HashMap;
/*
 * 문제
 * 선행 스킬이란 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬
 * 순서에 없는 스킬은 순서에 상관ㄱ없이 배울 수 있다. 
 * 선행 시킬 순서와 유저들이 만든 스킬 트리를 담은 배열이 주어질때 가능한 스클 트리의 개수
 * 
 * 제한 조건
 * 스킬을 알파벳 대문자로 표기
 * 모든 문자열을 알파벳 대문자
 * 스킬 순서와 스킬 트리는 문자열로 표기
 * 선행 스킬 순서의 길이는 1 이상 26이하, 중복해서 주어지지 않음
 * 스킬 드리의 길이는 1이상 20이하
 * 
 * 풀이
 * 일단 스킬을 hashmap에 저장
 * key는 스킬의 이름 value는 순서
 * 스킬 트리의 for문을 돌고 skill 순서의 skill을 배웠으면 cnt 증가
 * cnt가 같거나 hashmap에 없으면 배울 수 있읍
 */
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Integer, Integer> hashmap = new HashMap<>();
		for(int i = 0; i < skill.length(); i++) {
			int c = skill.charAt(i);
			hashmap.put(c, i);
		}
		
		for(int i = 0; i < skill_trees.length; i++) {
			int cnt = 0;
			String s = skill_trees[i];
            int j;
			for(j = 0; j < s.length(); j++) {
				int c = s.charAt(j);
				if(hashmap.containsKey(c)) {
					if(cnt != hashmap.get(c)) break;
					cnt++;
				}
			}
            if(j == s.length()) answer++;
		}
        return answer;
    }
}