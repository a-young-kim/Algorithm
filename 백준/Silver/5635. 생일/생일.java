import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 생일이 주어졌을때, 가장 나이가 적은 사람과 가장 많은 사람을 구하는 프로그램 작성
 * 
 * 입력
 * 1. 학생 수 N 100 이하
 * 2. N개의 줄 각 학생의 이름과 생일 -> 이름 dd mm yyyy와 같은 형식
 * 3. 이름은 그 학생의 이름이며 최대 15글자로 이루어짐 
 * 4. 연 월 일은 0으로 시작 안함
 * 5. 이름이 같거나 생일이 같은 사람 없음
 * 
 * 출력
 * 1. 가장 나이가 적은 사람의 이름
 * 2. 가장 나이가 많은 사람의 이름
 * 
 * 풀이
 * 1. N 입력
 * 2. 사람들 class만들고 우선순위 큐를 이용해 정렬
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if(o1.year == o2.year) {
					if(o2.month == o1.month) {
						return o2.day - o1.day;
					}
					return o2.month - o1.month;
				}
				return o2.year - o1.year;
			}
		});
		
		for(int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 String name = st.nextToken();
			 int day = Integer.parseInt(st.nextToken());
			 int month = Integer.parseInt(st.nextToken());
			 int year = Integer.parseInt(st.nextToken());
			 queue.add(new Person(name, year, month, day));
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(queue.poll().name).append("\n");
		while(queue.size() != 1) {
			queue.poll();
		}
		sb.append(queue.poll().name).append("\n");
		System.out.println(sb);
	}
	
	public static class Person{
		String name;
		int year;
		int month;
		int day;
		
		public Person(String name, int year, int month, int day) {
			this.name = name;
			this.year = year;
			this.month = month;
			this.day = day;
		}
		
		@Override
		public String toString() {
			return name + " " + day + " " + month + " " + year;
		}
	}

}