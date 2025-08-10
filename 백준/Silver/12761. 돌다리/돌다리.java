import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001];
        
        queue.offer(N);
        visited[N] = 1;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                if (current == M) {
                    System.out.println(steps);
                    return;
                }
                
                int[] nextValues = {
                    current - 1,
                    current + 1,
                    current + A,
                    current - A,
                    current + B,
                    current - B,
                    current * A,
                    current * B
                };
                
                for (int next : nextValues) {
                    if (next < 0 || next > 100000) continue;
                    if (visited[next] == 1) continue;
                    visited[next] = 1;
                    queue.offer(next);
                }
            }
            steps++;
        }
    }
}