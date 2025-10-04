import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int days = 0;
        
        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            moved = true;
                        }
                    }
                }
            }
            
            if (!moved) break;
            days++;
        }
        
        sb.append(days);
        System.out.println(sb);
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc= {0, 0, -1, 1};
    private static boolean bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> union = new ArrayList<>();
        
        queue.offer(new int[]{r, c});
        union.add(new int[]{r, c});
        visited[r][c] = true;
        
        int sum = A[r][c];
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                
                int difference = Math.abs(A[cr][cc] - A[nr][nc]);
                if (difference >= L && difference <= R) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    union.add(new int[]{nr, nc});
                    sum += A[nr][nc];
                }
            }
        }
        
        if (union.size() == 1) {
            return false;
        }
        
        int newPopulation = sum / union.size();
        for (int[] pos : union) {
            A[pos[0]][pos[1]] = newPopulation;
        }
        
        return true;
    }
}