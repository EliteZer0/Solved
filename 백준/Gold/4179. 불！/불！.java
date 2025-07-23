import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] maze;
    static int[][] fireTime;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        maze = new char[R][C];
        fireTime = new int[R][C];
        visited = new boolean[R][C];
        
        Queue<int[]> fireQueue = new ArrayDeque<>();
        int startX = -1, startY = -1;
        
        // 미로 입력 및 초기화
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = line.charAt(j);
                fireTime[i][j] = Integer.MAX_VALUE;
                
                if (maze[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j, 0});
                    fireTime[i][j] = 0;
                } else if (maze[i][j] == 'J') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        // 불의 확산 시간 계산
        while (!fireQueue.isEmpty()) {
            int[] curr = fireQueue.poll();
            int x = curr[0], y = curr[1], time = curr[2];
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && 
                    maze[nx][ny] != '#' && fireTime[nx][ny] > time + 1) {
                    fireTime[nx][ny] = time + 1;
                    fireQueue.offer(new int[]{nx, ny, time + 1});
                }
            }
        }
        
        // 지훈이의 탈출 경로 탐색
        Queue<int[]> jihunQueue = new ArrayDeque<>();
        jihunQueue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        
        while (!jihunQueue.isEmpty()) {
            int[] curr = jihunQueue.poll();
            int x = curr[0], y = curr[1], time = curr[2];
            
            // 가장자리에 도달했는지 확인 (탈출 조건)
            if (x == 0 || x == R - 1 || y == 0 || y == C - 1) {
                System.out.println(time + 1);
                return;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && 
                    !visited[nx][ny] && maze[nx][ny] != '#' && 
                    fireTime[nx][ny] > time + 1) {
                    
                    visited[nx][ny] = true;
                    jihunQueue.offer(new int[]{nx, ny, time + 1});
                }
            }
        }
        
        System.out.println("IMPOSSIBLE");
    }
}