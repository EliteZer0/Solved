import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 최대 힙 (중간값 이하)
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());

        // 최소 힙 (중간값 초과)
        PriorityQueue<Integer> right = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();

            // 일단 왼쪽 최대 힙에 삽입
            left.offer(num);

            // 최대 힙의 top > 최소 힙의 top이면 교환 (중간값 조건 유지)
            if (!right.isEmpty() && left.peek() > right.peek()) {
                right.offer(left.poll());
                left.offer(right.poll());
            }

            // left의 크기 > right보다 1 이상 크면 right로 이동
            if (left.size() > right.size() + 1) {
                right.offer(left.poll());
            }

            // 중간값 출력
            sb.append(left.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
