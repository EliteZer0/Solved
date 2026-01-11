import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int K;
    static int[] arr;
    static List<Integer>[] level;

    static void dfs(int l, int r, int depth) {
        if(l>r) return;
        int mid = (l + r) / 2;
        level[depth].add(arr[mid]);
        // 왼쪽 자식 노드
        dfs(l, mid - 1, depth + 1);
        // 오른쪽 자식 노드
        dfs(mid + 1, r, depth + 1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        K = Integer.parseInt(br.readLine());
        int n = (1 << K) - 1;

        arr = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        level = new ArrayList[K];
        for (int i = 0; i < K; i++) level[i] = new ArrayList<>();

        dfs(0, n - 1, 0);

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < level[i].size(); j++) {
                if (j > 0) sb.append(' ');
                sb.append(level[i].get(j));
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}