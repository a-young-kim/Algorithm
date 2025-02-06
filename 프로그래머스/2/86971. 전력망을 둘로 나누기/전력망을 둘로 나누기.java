/*
문제
1. n개의 송신탐 트리 형태
2. 전선들 중 하나를 끊어서 현재의 전력 망 네트워크를 2개로 분할
3. 두 전력망이 갖게 되는 송전탐의 개수를 최대한 비슷하게 맞추고자 합니다. 
4. 두 전력망이 가지고 있는 송전탑 개수 차이를 return

제한사항
1. n은 100 이하

아이디어
연결 중 하나 선택해서 제거 후 개수 세기
유니온 파인드

시간 복잡도
O(n * n * n) ?

*/
import java.util.*;
class Solution {
    int[][] wires;
    int[] root;
    int answer = Integer.MAX_VALUE, n;
    HashMap<Integer, Integer> map;
    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;
        
        root = new int[n + 1];
        map = new HashMap<>();
        for(int i = 0; i < n - 1; i++){
            setRoot();
            
            for(int j = 0; j < n - 1; j++){
                if(j == i) continue;
                int[] points = wires[j];
                union(points[0], points[1]);
            }
            
            answer = Math.min(answer, check());
        }
        return answer;
    }
    
    public int check(){
        map.clear();
        
        for(int i = 1; i <= n; i++){
            int num = find(root[i]);
            if(map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        
        int result = 0;
        for(int key : map.keySet()){
            if(result == 0) result = map.get(key);
            else result -= map.get(key);
        }
        
        return Math.abs(result);
    }
    
    public int find(int num){
        if(num == root[num]) return num;
        return root[num] = find(root[num]);
    }
    
    public void union(int A, int B){
        int aRoot = find(A);
        int bRoot = find(B);
        
        if(aRoot > bRoot) root[aRoot] = bRoot;
        else root[bRoot] = aRoot;
    }
    
    public void setRoot(){
        for(int i = 0; i <= n; i++){
            root[i] = i;
        }
    }
}