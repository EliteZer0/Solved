import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        
        long totalComparisons = 0;
        
        // 묶음이 하나가 될 때까지 반복
        while (pq.size() > 1) {
            // 가장 작은 두 묶음을 꺼냄
            int first = pq.poll();
            int second = pq.poll();
            
            // 두 묶음을 합치는 데 필요한 비교 횟수
            int mergedSize = first + second;
            totalComparisons += mergedSize;
            
            // 합친 결과를 다시 큐에 추가
            pq.offer(mergedSize);
        }
        
        sb.append(totalComparisons);
        System.out.print(sb.toString());
    }
}