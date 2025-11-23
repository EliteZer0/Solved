import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine().trim());
        
        for (int scenario = 1; scenario <= t; scenario++) {
            int n = Integer.parseInt(br.readLine().trim());
            int k = Integer.parseInt(br.readLine().trim());
            
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            
            int m = Integer.parseInt(br.readLine().trim());
            sb.append("Scenario ").append(scenario).append(":\n");
            
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                
                if (find(u) == find(v)) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}