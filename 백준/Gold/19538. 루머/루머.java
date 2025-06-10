import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 사람 수

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) break;
                adj.get(i).add(num);
            }
        }

        int m = Integer.parseInt(br.readLine()); // 최초 유포자 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] time = new int[n + 1];
        Arrays.fill(time, -1);

        int[] rumorCount = new int[n + 1]; // 주변 루머 믿는 사람 수
        Queue<Integer> q = new ArrayDeque<>();

        while (st.hasMoreTokens()) {
            int person = Integer.parseInt(st.nextToken());
            time[person] = 0;
            q.add(person);
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neighbor : adj.get(current)) {
                if (time[neighbor] != -1) continue;
                rumorCount[neighbor]++;

                int total = adj.get(neighbor).size();
                if (rumorCount[neighbor] >= (total + 1) / 2) {
                    time[neighbor] = time[current] + 1;
                    q.add(neighbor);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(time[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}