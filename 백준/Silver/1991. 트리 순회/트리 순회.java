
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static Node[] nodes;
	public static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		nodes = new Node[26];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			Node rootNode;
			if(nodes[root - 'A'] == null) {
				rootNode = new Node(null, root, null, null);
				nodes[root - 'A'] = rootNode;
			}
			else rootNode = nodes[root - 'A'];
			
			Node leftNode = null;
			if(left != '.') {
				if(nodes[left - 'A'] == null) {
					leftNode = new Node(rootNode, left, null, null);
					nodes[left - 'A'] = leftNode;
				}
				else {
					leftNode = nodes[left -'A'];
					leftNode.parent = rootNode;
				}
			}
			
			Node rightNode = null;
			if(right != '.') {
				if(nodes[right - 'A'] == null) {
					rightNode = new Node(rootNode, right, null, null);
					nodes[right - 'A'] = rightNode;
				}
				else {
					rightNode = nodes[right -'A'];	
					rightNode.parent = rootNode;
				}
			}
			
			if(leftNode != null) rootNode.leftChild = leftNode;
			if(rightNode != null) rootNode.rightChild = rightNode;
		}
		
		
		//전위 순회
		preorder(nodes[0]);
		
		System.out.println();
		visited = new boolean[26];
		//중위 순회
		inorder(nodes[0]);
		
		System.out.println();
		visited = new boolean[26];
		//후위순회
		postorder(nodes[0]);
	}
	
	public static void inorder(Node node) {
		if(node == null) return;
	
		inorder(node.leftChild);
		System.out.print(node.price);
		inorder(node.rightChild);
	}
	
	public static void postorder(Node node) {
		if(node == null) return;
		
		postorder(node.leftChild);
		postorder(node.rightChild);
		System.out.print(node.price);
	}
	
	public static void preorder(Node node) {
		
		if(node == null) return;
		System.out.print(node.price);
		preorder(node.leftChild);
		preorder(node.rightChild);
	}
	
	public static class Node{
		public Node parent;
		public char price;
		public Node leftChild;
		public Node rightChild;
		
		
		public Node(Node parent, char price, Node leftChild, Node rightChild) {
			super();
			this.parent = parent;
			this.price = price;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		

		@Override
		public String toString() {
			if(parent == null) return "Node [price= " + price +"]\n";
			return "Node [parent.price=" + parent.price + ", price=" + price +"]\n" ;
		}

	}
}
