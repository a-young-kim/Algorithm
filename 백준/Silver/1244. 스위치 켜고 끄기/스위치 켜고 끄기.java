import java.util.Scanner;

/*
 * 문제
 * 스위치 켜고 끄기
 * 1부터 연속적으로 번호가 붙어있는 스위치 
 * 1켜짐, 0꺼짐
 * 학생에서 1이상 스위치개수 이하의 자연수 나눔
 * 학생들은 성별과 받은 수에 따라 조작
 * 남학생: 스위치 번호가 자신의 받은 수 배수이면 스위치 상태 변경
 * 여학생: 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서(01수10)
 * 		가장 많은 스위치 부분을 찾아서 그 부분 모두 변경
 * 
 * 입력
 * 첫째줄 스위치 개수 100이하의 양의 정수
 * 둘째줄 각 스위치의 상태  1켜짐, 0꺼짐 스위치 사이의 빈칸 존재
 * 셋째줄 학생 수 100이하의 정수
 * 넷째줄부터 마지막줄 한학생의 성별, 학생이 받은 수 => 남학생 1 여학생 2 스위치 개수 이하인 양의 정수
 * 
 * 출력
 * 스위치 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한줄에 20개씩 출력
 * 
 * 주의
 * 스위치의 번호는 1부터
 */
/* 풀의 방법
 * int N => 스위치 개수
 * int[N + 1] array => 스위치의 상태가 담긴 array, 번호가 1번부터 시작하므로 +1
 * int M => 학생 수
 * int[M][2] array2 => 학생 정보
 * 
 * 여학생일 때 array2[][1]의 위치인 array index에 접근하여 재귀를 이용하여
 * 		 array좌우 값 비교 후 같으면 다시 1빼고 재귀 0이 있으면 종료
 * 남학생일 때 array2[][1]의 위치의 array index에 접근하여 for문을 돌려 값만큼 index 증가하여 변경
 * 
 * 
 */
public class Main {
	
	static int N, M;
	static int[] switchArray;
	static int[][] studentsArray;
	
	public static void girl(int index, int size) {
		
		if(index - size <= 0 || index + size > N) return;
		if(switchArray[index - size] != switchArray[index + size]) return;
		
		switchArray[index - size] = (switchArray[index - size] == 1 ? 0: 1);
		switchArray[index + size] = (switchArray[index + size] == 1 ? 0: 1);
		girl(index, size + 1);
	}
	
	public static void boy(int index) {
		
		for(int i = index; i < N + 1; i = i + index) {
			switchArray[i] = (switchArray[i] == 1 ? 0: 1);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		switchArray = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			switchArray[i] = sc.nextInt();
		}
		
		M = sc.nextInt();
		studentsArray = new int[M][2];
		for(int i = 0; i < M; i++) {
			studentsArray[i][0] = sc.nextInt();
			studentsArray[i][1] = sc.nextInt();
		}
		sc.close();
		
		for(int i = 0; i < M ; i++) {
			int index = studentsArray[i][1];
			switch(studentsArray[i][0]){
			//남학생
			case 1:{
				boy(index);
				 break;
			}
			// 여학생
			case 2:{
				girl(index, 1);
				switchArray[index] = switchArray[index] == 1 ? 0: 1;
				break;
			}
			}
		}
		
		for(int i = 1; i < N + 1; i++) {
			if(i % 20 != 0) System.out.print(switchArray[i] + " ");
			else System.out.println(switchArray[i]);
		}
	}
}