import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static ArrayList<Integer>[] g;
    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        parent = new int[N + 1];
        size = new int[N + 1];

        build(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine().trim());
            sb.append(size[u]).append('\n');
        }
        System.out.print(sb);
    }

    static void build(int root) {
        int[] order = new int[N];
        int idx = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(root);
        parent[root] = -1;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[idx++] = u;

            for (int v : g[u]) {
                if (v == parent[u]) continue;
                parent[v] = u;
                stack.push(v);
            }
        }

        for (int i = idx - 1; i >= 0; i--) {
            int u = order[i];
            int s = 1; // 자기 자신
            for (int v : g[u]) {
                if (v == parent[u]) continue;
                s += size[v];
            }
            size[u] = s;
        }
    }
}
