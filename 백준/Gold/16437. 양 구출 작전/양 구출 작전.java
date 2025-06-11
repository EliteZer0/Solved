import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] tree;
    static char[] types;
    static long[] values;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        types = new char[N + 1];
        values = new long[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        types[1] = 'S'; // root
        values[1] = 0;

        for (int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char type = st.nextToken().charAt(0);
            long value = Long.parseLong(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());

            types[i] = type;
            values[i] = value;
            tree[parent].add(i);
        }

        System.out.println(dfs(1));
    }

    static long dfs(int current) {
        long sum = 0;
        for (int child : tree[current]) {
            sum += dfs(child);
        }

        if (types[current] == 'S') {
            return sum + values[current];
        } else {
            return Math.max(0, sum - values[current]);
        }
    }
}