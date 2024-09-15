import java.util.*;

class Solution {
    int[][] fatigueMap = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int minFatigue = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        int[] mineralCount = new int[Math.min(minerals.length, picks[0] * 5 + picks[1] * 5 + picks[2] * 5)];
        
        // 광물을 숫자로 변환하여 배열에 저장 (다이아몬드: 0, 철: 1, 돌: 2)
        for (int i = 0; i < mineralCount.length; i++) {
            if (minerals[i].equals("diamond")) mineralCount[i] = 0;
            else if (minerals[i].equals("iron")) mineralCount[i] = 1;
            else mineralCount[i] = 2;
        }
        
        dfs(picks, mineralCount, 0, 0);
        return minFatigue;
    }
    
    private void dfs(int[] picks, int[] minerals, int start, int currentFatigue) {
        // 탐색 종료 조건
        if (currentFatigue >= minFatigue) return;
        if (start >= minerals.length || Arrays.stream(picks).sum() == 0) {
            minFatigue = Math.min(minFatigue, currentFatigue);
            return;
        }
        
        // 다이아몬드, 철, 돌 곡괭이를 사용할 경우 탐색
        for (int pick = 0; pick < 3; pick++) {
            if (picks[pick] > 0) {
                picks[pick]--;
                int fatigue = 0;
                
                // 현재 곡괭이로 최대 5개 광물을 캔다
                for (int i = 0; i < 5 && start + i < minerals.length; i++) {
                    fatigue += fatigueMap[pick][minerals[start + i]];
                }
                
                // 다음 5개로 이동
                dfs(picks, minerals, start + 5, currentFatigue + fatigue);
                picks[pick]++;
            }
        }
    }
}
