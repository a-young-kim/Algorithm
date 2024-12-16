/*
풀이 과정
1. int n, x, m 초기화 int[] dx dy 생성 => y가 먼저 변경되는 것이 먼저
2. class Node 생성
3. queue와 hashmap 생성
4. for i in range(0, x):
4.1 int point = routes[i][0];
4.2 queue.add(new Node(points[point][0], points[point][1], 1, 0));
5. int time = 0;
6. while(!queue.isEmpty())
6.1 Node node = queue.poll()
6.2 if(node.time != time)
6.2.1 time++
6.2.2 answer += set.size()
6.2.3 set.clear()
6.3 if(node.npoint == m) continue
6.4 int[] npointArr = points[node.point];
6.4 int nr int nc int result = Integer.MAX_VALUE
6.5 for i in range(0, 4)
6.5.1 int distance = row col 차
6.5.2 if(distance < result)
6.5.2.1 nr = node.row + dy[i], nc = node.col + dx[i]
6.6 if(result == 0) node.nPoint++;
6.7 Node nNode = new Node(nr, nc, node.nPoint, node.time + 1)
6.8 if(visited.conatins(nNode)) duplicated.add(nNode)
6.9 else visited.add(nNode)
7. 출력

class Node
int row
int col
int npoint
int time

public Node(전부)

public boolean equals(Node node)
return row와 col이 같은 때

public int hashcode
return row * 1000 + col

시간 복잡도
???
*/
import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        int n = points.length;
        int x = routes.length;
        int m = routes[0].length;
        
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        
        Queue<Node> queue = new ArrayDeque<>();
        HashMap<Node, Integer> visited = new HashMap<>();
        
        for(int i = 0; i < x; i++){
            int point = routes[i][0] - 1;
            Node node = new Node(points[point][0], points[point][1], i, 1, 0);
            queue.add(node);
            if(visited.containsKey(node)) visited.put(node, 2);
            else visited.put(node, 1);
        }
        
        int time = -1;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.time != time){
                time++;
                for(Node key : visited.keySet()){
                    if(visited.get(key) == 1) continue;
                    answer++;
                }
                visited.clear();
            }
            
            if(node.routeNum == m) continue;
            int nr = 0;
            int nc = 0;
            double result = Integer.MAX_VALUE;
            int[] npointArr = points[routes[node.robot][node.routeNum] - 1];
            
             for (int i = 0; i < 4; i++) {
                double distance = Math.abs(node.row + dy[i] - npointArr[0]) + Math.abs(node.col + dx[i] - npointArr[1]);
                
                if (distance < result) {
                    nr = node.row + dy[i];
                    nc = node.col + dx[i];
                    result = distance;
                }
            }
                
            if(result == 0) node.routeNum++;
            Node nNode = new Node(nr, nc, node.robot, node.routeNum, node.time + 1);
            queue.add(nNode);
            
            if(visited.containsKey(nNode)) visited.put(nNode, 2);
            else visited.put(nNode, 1);
        }
        return answer;
    }
    
    class Node{
        int row;
        int col;
        int robot;
        int routeNum;
        int time;
        
        public Node(int row, int col,int robot, int routeNum, int time){
            this.row = row;
            this.col = col;
            this.robot = robot;
            this.routeNum = routeNum;
            this.time = time;
        }
        @Override
        public boolean equals(Object obj){
            Node node = (Node) obj;
            return this.row == node.row && this.col == node.col;
        }
        
        @Override
        public int hashCode(){
            return this.row * 1000 + this.col;
        }
        
        public String toString(){
            return "row : " + row + " col: " + col + " time: " + time;
        }
    }
}