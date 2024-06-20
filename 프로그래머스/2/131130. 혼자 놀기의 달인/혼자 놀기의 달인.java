/*
문제
1. 100장 이하의 카드
2. 준비된 상자에 카드를 넣고 무작위로 일렬로 나열
3. 일렬로 나열되면 상자가 나열된 숫자에 따라 1번부터 순차적으로 증가 번호
4. 임의의 상자를 하나 선택하여 숫자 확인 후 해당 번호의 상자 연다
5. 열 상자가 없을 때까지 이를 반복
6. 상자를 연 개수가 점수
7. 위를 한번더 반복
8. 두 점수를 합치기

풀이
1. int[카드 개수] visitedA 생성
2. for i in range(0, 카드 개수)
2.1 if(visitedA[i]) contintue
2.2 num = i, countA = 0
2.3 while(!visitedA[num])
2.4 visitedA[num] = true
2.5 num = cards[num] - 1
2.6 countA ++
4. for j in range(0, 카드 개수)
4.1 if(visitedA[j]) continue;
4.2 int[카드 개수] visitedB 생성,  countB = 0; num = j
4.3 while(!visitedB[num] && !visitedA[num])
4.4 visitedB[num] = true
4.5 num = cards[num] - 1
4.6 countB ++;
5. answer = Math.max(answer, countA * countB)
6. 출력

*/
class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        int cardsCount = cards.length;
        
        boolean[] visitedA = new boolean[cardsCount];
        for(int i = 0; i < cardsCount; i++){
            if(visitedA[i]) continue;
            int num = i, countA = 0;
            while(!visitedA[num]){
                visitedA[num] = true;
                num = cards[num] - 1;
                countA ++;
            }
            boolean[] visitedB= new boolean[cardsCount];
            for(int j = 0; j < cardsCount;j++){
                if(visitedA[j] || visitedB[j]) continue;
                num = j;
                int countB = 0;
                
                while(!visitedB[num]){
                    visitedB[num] = true;
                    num = cards[num] - 1;
                    countB++;
                }
                answer = Math.max(answer, countA * countB);
            }
        }
        return answer;
    }
}