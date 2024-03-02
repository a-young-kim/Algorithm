import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제
 * N * N 정사각형 모양 방
 * 점신을 먹기 위해 아래층으로 나려가야 함
 * 최대한 ㅃ2ㅏ른 시간 내에
 *
 * 방안 사람들을 P, 계단 입구를 S라고 할때
 * 이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간.
 * 사람들이 아래층으로 이동하는 시간은 계단 입구까지 이동 시간과 계단을 내려가는 시간이 포함
 * 1. 계단 입구까지 이동 시간
 * 	- 이동 시간 : |pr - sr| + |pc - sc|
 * 2. 계단을 내려가는 시간
 * 	- 계단을 내려가는 시간은 계단 입구에 도착 후 부터 계단을 완전히 내려갈 때까지 시간
 * - 계단 입구에 도착하면 1분 후 아래칸으로 내려갈 수 있다.
 * 	- 계단을 이미 3명이 내려가는 경우 계단 입구에 대기
 * 	- 계단 마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분
 *
 * 모든 사람들이 계단을 내려가 이동이 완료되는 시간의 최소
 *
 * 제약 사항
 * 1. 방 한변의 길이는 10이하
 * 2. 사람 수는 10 이하
 * 3. 계단의 입구는 2개
 * 4. 계단으리 길이는 10 이하 정수
 *
 * 입력 테스트 케이스 개수 T
 * 방 한변의 길이 N
 * 사람 1, 2 이상은 계단 입구 이면 그 값은 계단의 길이
 *
 * 출력
 * 이동이 완료되는 최소의 시간
 *
 * 풀이
 * 완탐 -> 사람들이 stair1 혹은 stair2 둘 중 하나를 이용할 경우
 * 조합 사용,
 *
 * 그리디 불가
 */
public class Solution {

    static int N, answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] stairs = new int[2][3];
    static int[][] map;
    static List<Person> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st
                .nextToken());
        for(int test_case = 1; test_case <= T; test_case ++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;
            map = new int[N][N];
            people = new ArrayList<>();

            int cnt = 0;
            for(int i = 0;i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] >= 2) {
                        stairs[cnt][0] = i;
                        stairs[cnt][1] = j;
                        stairs[cnt][2] = map[i][j];
                        cnt ++;
                    }
                    else if(map[i][j] == 1) {
                        people.add(new Person(people.size(), i,  j,  0,  0));
                    }
                }
            }

            Arrays.sort(stairs, (a1, a2) -> a1[2] - a2[2]);

            // 거리 초기화
            for(Person p:people) {
                p.stair1 = Math.abs(p.row - stairs[0][0]) + Math.abs(p.col - stairs[0][1]);
                p.stair2 = Math.abs(p.row - stairs[1][0]) + Math.abs(p.col - stairs[1][1]);
            }

            visited = new boolean[people.size()];
           f(0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    public static void f(int cnt, int idx){
        go(true);
        go(false);

        if(cnt == people.size() / 2) return;

        for(int i = idx; i < people.size(); i++){
            visited[i] = true;
            f(cnt + 1, i + 1);
            visited[i] = false;
        }

    }
    public static void go(boolean flag) {
        // true
        Queue<PersonInStair> stair1 = new ArrayDeque<>();
        Queue<Person> stair1Wait = new ArrayDeque<>();
        //false
        Queue<PersonInStair> stair2 = new ArrayDeque<>();
        Queue<Person> stair2Wait = new ArrayDeque<>();

        Queue<Person> people2 = new ArrayDeque<>();
        for(int i = 0;i <people.size(); i++){
            people2.add(people.get(i).clone());
        }

        int cnt = 0;
        int count = people2.size();

        while(count != 0) {

            // 사람들의 위치 확인
            int sizePeople = people2.size();
            for(int i = 0; i < sizePeople; i++) {
                Person p = people2.poll();

                p.stair1 --;
                p.stair2 --;

                if(p.stair1 == 0 && visited[p.node] == flag) {
                    p.stair1Level = cnt;
                    stair1Wait.add(p);
                    continue;
                }

                if(p.stair2 == 0 && visited[p.node] != flag) {
                    p.stair2Level = cnt;
                    stair2Wait.add(p);
                    continue;
                }

                people2.add(p);
            }

            // 1번 계단 확인
            int sizeStair1 =  stair1.size();
            for(int i = 0; i < sizeStair1; i++) {
                PersonInStair p = stair1.poll();
                p.weight--;
                if(p.weight == 0) {
                    count --;
                    continue;
                }
                stair1.add(p);
            }

            // 2번 계단 확인
            int sizeStair2 = stair2.size();
            for(int i = 0; i < sizeStair2; i++) {
                PersonInStair p = stair2.poll();
                p.weight--;
                if(p.weight == 0) {
                    count --;
                    continue;
                }
                stair2.add(p);
            }

            // 1번 계단 예비 확인
            int sizeWait1 = stair1Wait.size();
            for(int i = 0; i < sizeWait1; i++) {
                if(stair1.size() == 3) break;

                Person p = stair1Wait.poll();
                if(p.stair1Level == cnt){
                    stair1Wait.add(p);
                    continue;
                }
                stair1.add(new PersonInStair(p.node, stairs[0][2]));
            }

            // 2번 계단 예비 확인
            int sizeWait2 = stair2Wait.size();
            for(int i = 0; i < sizeWait2; i++) {
                if(stair2.size() == 3) break;

                Person p = stair2Wait.poll();
                if(p.stair2Level == cnt){
                    stair2Wait.add(p);
                    continue;
                }
                stair2.add(new PersonInStair(p.node, stairs[1][2]));
            }

            cnt ++;
        }
        answer = Math.min(answer, cnt);
    }



    public static class PersonInStair{
        int node;
        int weight;

        public PersonInStair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "node: " + node + " weight: " + weight + "\n";
        }
    }

    public static class Person implements Cloneable{
        int node;
        int row;
        int col;
        int stair1;
        int stair1Level = Integer.MAX_VALUE;
        int stair2;
        int stair2Level = Integer.MAX_VALUE;

        public Person(int node, int row, int col, int stair1, int stair2) {
            this.node = node;
            this.row = row;
            this.col = col;
            this.stair1 = stair1;
            this.stair2 = stair2;
        }

        @Override
        public Person clone() {
            try {
                Person clonedPerson = (Person) super.clone();

                clonedPerson.stair1Level = this.stair1Level;
                clonedPerson.stair2Level = this.stair2Level;

                return clonedPerson;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(); // 이 예외가 발생하면 안 됨
            }
        }

        @Override
        public String toString() {
            return "node: " + node + " row: " + row + " col: " + col + " stair1: " + stair1 + " stair1Level: " + stair1Level +
                    " stair2: " + stair2 + " stair2Level: " + stair2Level + "\n";
        }
    }

}