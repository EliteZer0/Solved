import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 최대 힙 (중간값 이하)
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        // 최소 힙 (중간값 초과)
        PriorityQueue<Integer> right = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            // 일단 최대 힙에 넣기
            left.offer(num);

            // 최대 힙의 최대값이 최소 힙의 최소값보다 크면 swap
            if (!right.isEmpty() && left.peek() > right.peek()) {
                right.offer(left.poll());
                left.offer(right.poll());
            }

            // left가 right보다 두 개 이상 크면 균형 맞추기
            if (left.size() > right.size() + 1) {
                right.offer(left.poll());
            }

            // 중간값은 항상 left의 top
            sb.append(left.peek()).append("\n");
        }

        // 결과 출력
        System.out.print(sb.toString());
    }
}
