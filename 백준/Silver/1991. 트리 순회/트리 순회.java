/*
문제
1. 이진 트리를 입력받아 전위 순회, 중위 순회, 후위 순회 결과 출력

입력
1. 이진 트리의 노드 개수 N
2. N개의 줄에 걸쳐 노드와 왼쪽 노드, 오른쪽 노드
2.1 노드의 이름은 A부터 알파벳 대문자
2.2 항상 A가 루트 노드
2.3 자식 노드가 없는 경우 .으로 표현

출력
1. 전위 순회
2. 중위 순회
3. 후위 순회

제약 사항
N은 1이상 26 이하

풀이 과정
1. N입력
2. class Node 생성
3. Node[] nodeArr = new Node[N] 생성
4. for i in range(0, N):
4.1 char root, char left, char right 입력
4.2 nodeArr[root - 'A'] = nodeArr[root - 'A'] == null ?
    new Node(root) : nodeArr[root - 'A'];
4.3 if(left != '.')
4.3.1 nodeArr[left - 'A'] = nodeArr[left - 'A'] == null ?
    new Node(left) : nodeArr[left - 'A']
4.4 if(right != '.')
4.4.1 nodeArr[right - 'A'] = nodeArr[right - 'A'] == null ?
    new Node(right) : nodeArr[right - 'A']
4.5 nodeArr[root - 'A'].left = nodeArr[left - 'A'];
4.6 nodeArr[root - 'B'].right = nodeArr[right - 'A'];
5. boolean[] visited = new boolean[N] 생성 StringBuilder sb 생성
6. preorder(nodeArr[0]) 실행 sb.append("\n")
7. inorder(nodeArr[0]) 실행 sb.append("\n")
8. postorder(nodeArr[0]) 실행
9. sb 출력

preorder(Node node) => 시간 복잡도 NlogN
1. sb.append(node.name)
2. if(node.left != null) preorder(node.left)
3. if(node.right != null) preorder(node.right)

inorder(Node node) => 시간 복잡도 NlogN
1. if(node.left != null) inorder(node.left)
2. sb.append(node.name)
3. if(node.right != null) inorder(node.right)

postorder(Node node) => 시간 복잡도 NlogN
1. if(node.left != null) postorder(node.left)
2. if(node.right != null) postorder(node.right)
3. sb.append(node.name)


class Node:
    char name;
    Node left;
    Node right;

시간 복잡도 총 NlogN ????
 */
import java.util.*;
import java.io.*;
public class Main{

    public static Node[] nodeArr;
    public static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer N = Integer.parseInt(st.nextToken());

        nodeArr = new Node[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            nodeArr[root - 'A'] = nodeArr[root - 'A'] == null ? new Node(root) : nodeArr[root - 'A'];
            if(left != '.') {
                nodeArr[left - 'A'] = nodeArr[left - 'A'] == null ? new Node(left) : nodeArr[left - 'A'];
                nodeArr[root - 'A'].left = nodeArr[left - 'A'];
            }
            if(right != '.') {
                nodeArr[right - 'A'] = nodeArr[right - 'A'] == null ? new Node(right) : nodeArr[right - 'A'];
                nodeArr[root - 'A'].right = nodeArr[right - 'A'];
            }
        }

        sb = new StringBuilder();

        preorder(nodeArr[0]);
        sb.append("\n");
        inorder(nodeArr[0]);
        sb.append("\n");
        postorder(nodeArr[0]);

        System.out.println(sb);
    }

    public static void preorder(Node node){
        sb.append(node.name);
        if(node.left != null) preorder(node.left);
        if(node.right != null) preorder(node.right);
    }

    public static void inorder(Node node){
        if(node.left != null) inorder(node.left);
        sb.append(node.name);
        if(node.right != null) inorder(node.right);
    }

    public static void postorder(Node node){
        if(node.left != null) postorder(node.left);
        if(node.right != null) postorder(node.right);
        sb.append(node.name);
    }

    public static class Node{
        char name;
        Node left;
        Node right;

        public Node(char name){
            this.name = name;
        }
    }
}