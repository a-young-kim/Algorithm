
class Solution {
    static int move = Integer.MAX_VALUE;
    static char[] array;
    public void moveMin(int start, int end) {
		if(start == array.length || end == array.length) return;
		else if(start == end) {
			moveMin(start, end + 1);
		}
		else if(array[start] == 'A') {
			moveMin(start + 1, end);
		}
		else if( array[end] == 'A') {
			moveMin(start, end + 1);
		}
		else {
			move = Math.min(Math.min(start * 2 + array.length - end, start + (array.length - end) * 2), move);
			moveMin(start + 1, end);
		}
		
	}
    public int solution(String name) {
        int answer = 0;
        array = name.toCharArray();
        
        for(int i = 0 ; i < array.length; i++) {
			char c = array[i];
			answer += Math.min(c - 'A', 'Z' - c + 1);
		}
        
        moveMin(0, 0);
        // 뒤로 탐색
		for(int i = array.length - 1; i > 0; i--) {
			if(array[i] == 'A') continue;
			move = Math.min(move, i);
			break;
		}
		// 앞으로
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 'A') continue;
			move = Math.min(move, array.length - i);
			break;
		}
		if(move == Integer.MAX_VALUE) move = 0;
        return answer + move;
    }
}