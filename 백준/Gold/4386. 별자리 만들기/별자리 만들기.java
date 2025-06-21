import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        double[][] stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        List<Edge> edges = new ArrayList<>();
        // 모든 별쌍 거리 계산
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = stars[i][0] - stars[j][0];
                double dy = stars[i][1] - stars[j][1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges);
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        double result = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.cost;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.2f", result));
        System.out.println(sb.toString());
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }
}
