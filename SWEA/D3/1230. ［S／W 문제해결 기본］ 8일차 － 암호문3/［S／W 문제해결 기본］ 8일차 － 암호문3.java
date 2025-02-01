import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 노드 클래스 정의
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node head;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            clear();  // LinkedList 초기화

            // 원본 암호문 길이
            int n = Integer.parseInt(br.readLine());

            // 원본 암호문 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                add(Integer.parseInt(st.nextToken()));
            }

            // 명령어 개수
            int commands = Integer.parseInt(br.readLine());

            // 명령어 처리
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String command = st.nextToken();

                if (command.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        int num = Integer.parseInt(st.nextToken());
                        addIdx(x + j, num);
                    }
                } else if (command.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        remove(x);
                    }
                } else if (command.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        int num = Integer.parseInt(st.nextToken());
                        add(num);
                    }
                }
            }

            // 결과 출력
            sb.append("#").append(tc).append(" ");
            Node current = head;
            for (int i = 0; i < 10; i++) {  // 처음 10개 숫자만 출력
                if (current != null) {
                    sb.append(current.data).append(" ");
                    current = current.next;
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void add(int n) {
        Node newNode = new Node(n);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    static void addIdx(int idx, int n) {
        if (idx < 0 || idx > size) {
            return;
        }

        Node newNode = new Node(n);
        if (idx == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    static void remove(int idx) {
        if (idx < 0 || idx >= size) {
            return;
        }

        if (idx == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    static void clear() {
        head = null;
        size = 0;
    }
}