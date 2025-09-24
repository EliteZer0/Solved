import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            String[] line = br.readLine().split(" ");
            int R = Integer.parseInt(line[0]);
            int C = Integer.parseInt(line[1]);
            
            char[][] grid = new char[R][C];
            for (int i = 0; i < R; i++) {
                grid[i] = br.readLine().toCharArray();
            }
            
            sb.append(isHeart(grid, R, C) ? 1 : 0).append('\n');
        }
        
        System.out.print(sb.toString());
    }
    
    static boolean isHeart(char[][] grid, int R, int C) {
        // 모든 '#' 위치 찾기
        List<int[]> creamCells = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '#') {
                    creamCells.add(new int[]{i, j});
                }
            }
        }
        
        if (creamCells.isEmpty()) return false;
        
        // 모든 '#' 셀이 연결되어 있는지 확인
        if (!isConnected(grid, R, C, creamCells)) {
            return false;
        }
        
        // 가능한 모든 하트 패턴과 비교
        for (int N = 2; N <= Math.max(R, C); N++) {
            for (int M = 1; M < N; M++) {
                if (checkHeartPattern(grid, R, C, N, M, creamCells)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    static boolean isConnected(char[][] grid, int R, int C, List<int[]> creamCells) {
        if (creamCells.isEmpty()) return false;
        
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        
        // 첫 번째 크림 셀부터 시작
        int[] start = creamCells.get(0);
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int visitedCount = 1;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && 
                    !visited[nx][ny] && grid[nx][ny] == '#') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    visitedCount++;
                }
            }
        }
        
        return visitedCount == creamCells.size();
    }
    
    static boolean checkHeartPattern(char[][] grid, int R, int C, int N, int M, List<int[]> creamCells) {
        // 네 가지 방향의 하트 패턴 확인
        // 1. 좌상단 제거 (top-left corner removed)
        // 2. 우상단 제거 (top-right corner removed)  
        // 3. 좌하단 제거 (bottom-left corner removed)
        // 4. 우하단 제거 (bottom-right corner removed)
        
        for (int startR = 0; startR <= R - N; startR++) {
            for (int startC = 0; startC <= C - N; startC++) {
                
                // 패턴 1: 좌상단 M×M 제거
                if (matchesHeartPattern(grid, startR, startC, N, M, 0, creamCells)) {
                    return true;
                }
                
                // 패턴 2: 우상단 M×M 제거
                if (matchesHeartPattern(grid, startR, startC, N, M, 1, creamCells)) {
                    return true;
                }
                
                // 패턴 3: 좌하단 M×M 제거
                if (matchesHeartPattern(grid, startR, startC, N, M, 2, creamCells)) {
                    return true;
                }
                
                // 패턴 4: 우하단 M×M 제거
                if (matchesHeartPattern(grid, startR, startC, N, M, 3, creamCells)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    static boolean matchesHeartPattern(char[][] grid, int startR, int startC, int N, int M, int cornerType, List<int[]> creamCells) {
        Set<String> expectedCells = new HashSet<>();
        
        // N×N 전체 영역에서 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean shouldBeEmpty = false;
                
                // 각 코너 타입에 따라 제거할 M×M 영역 결정
                switch (cornerType) {
                    case 0: // 좌상단 제거
                        shouldBeEmpty = (i < M && j < M);
                        break;
                    case 1: // 우상단 제거  
                        shouldBeEmpty = (i < M && j >= N - M);
                        break;
                    case 2: // 좌하단 제거
                        shouldBeEmpty = (i >= N - M && j < M);
                        break;
                    case 3: // 우하단 제거
                        shouldBeEmpty = (i >= N - M && j >= N - M);
                        break;
                }
                
                if (!shouldBeEmpty) {
                    expectedCells.add((startR + i) + "," + (startC + j));
                }
            }
        }
        
        // 실제 크림 셀들과 예상 패턴이 정확히 일치하는지 확인
        if (expectedCells.size() != creamCells.size()) {
            return false;
        }
        
        for (int[] cell : creamCells) {
            String cellKey = cell[0] + "," + cell[1];
            if (!expectedCells.contains(cellKey)) {
                return false;
            }
        }
        
        return true;
    }
}