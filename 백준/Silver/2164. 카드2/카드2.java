import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 큐 초기화 (1부터 n까지의 카드)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        
        // 카드가 1장 남을 때까지 반복
        while (queue.size() > 1) {
            // 1. 맨 위 카드를 버린다
            queue.poll();
            
            // 2. 맨 위 카드를 맨 아래로 옮긴다
            if (!queue.isEmpty()) {
                int card = queue.poll();
                queue.offer(card);
            }
        }
        
        // 마지막 남은 카드 출력
        System.out.println(queue.peek());
    }
}