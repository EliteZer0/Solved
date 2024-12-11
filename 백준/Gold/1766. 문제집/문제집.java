import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int ranks = Integer.parseInt(st.nextToken());
        
        int[] edgeCount =new int[N+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < ranks; i++) {
        	st = new StringTokenizer(br.readLine());
        	int first = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            
            graph.get(first).add(next);
            edgeCount[next]++;
		}

        // 쉬운 문제부터 풀어야하므로 pq 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i < edgeCount.length; i++) {
            if (edgeCount[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for (int next : graph.get(cur)) {

                edgeCount[next]--;
                if (edgeCount[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        
        System.out.println(sb.toString());
    }
}