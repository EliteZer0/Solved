import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] grid;
    private static boolean[][] visited;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        grid = new int[N][M];
        visited = new boolean[N][M];
        
        // 격자 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int peakCount = 0;
        
        // 모든 격자를 확인하여 산봉우리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (isPeak(i, j)) {
                        peakCount++;
                    }
                }
            }
        }
        
        sb.append(peakCount);
        System.out.println(sb.toString());
    }
    
    private static boolean isPeak(int startX, int startY) {
        int height = grid[startX][startY];
        List<int[]> component = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        
        // BFS로 같은 높이의 연결된 영역 찾기
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        component.add(new int[]{startX, startY});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            // 8방향 확인
            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if (isValid(nx, ny) && !visited[nx][ny] && grid[nx][ny] == height) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    component.add(new int[]{nx, ny});
                }
            }
        }
        
        // 이 연결된 영역이 산봉우리인지 확인
        // 모든 인접한 격자가 현재 높이보다 낮아야 함
        Set<String> componentSet = new HashSet<>();
        for (int[] cell : component) {
            componentSet.add(cell[0] + "," + cell[1]);
        }
        
        for (int[] cell : component) {
            int x = cell[0];
            int y = cell[1];
            
            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if (isValid(nx, ny)) {
                    // 같은 component에 속하지 않는 인접 격자가 현재 높이 이상이면 산봉우리가 아님
                    if (!componentSet.contains(nx + "," + ny) && grid[nx][ny] >= height) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}