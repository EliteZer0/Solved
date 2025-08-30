import java.io.*;
import java.util.*;

public class Main {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static final int ROWS = 12;
    static final int COLS = 6;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        char[][] field = new char[ROWS][COLS];
        
        // 필드 입력
        for (int i = 0; i < ROWS; i++) {
            String line = br.readLine();
            for (int j = 0; j < COLS; j++) {
                field[i][j] = line.charAt(j);
            }
        }
        
        int chainCount = 0;
        
        // 연쇄 시뮬레이션
        while (true) {
            // 터트릴 뿌요 그룹 찾기
            boolean[][] toRemove = new boolean[ROWS][COLS];
            boolean hasChain = false;
            
            boolean[][] visited = new boolean[ROWS][COLS];
            
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                if (field[i][j] != '.' && !visited[i][j]) {
                        List<int[]> group = new ArrayList<>();
                        if (bfs(field, visited, i, j, field[i][j], group)) {
                            // 4개 이상이면 제거 대상에 추가
                            hasChain = true;
                            for (int[] pos : group) {
                                toRemove[pos[0]][pos[1]] = true;
                            }
                        }
                    }
                }
            }
            
            // 더 이상 터질 그룹이 없으면 종료
            if (!hasChain) {
                break;
            }
            
            // 연쇄 증가
            chainCount++;
            
            // 뿌요 제거
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (toRemove[i][j]) {
                        field[i][j] = '.';
                    }
                }
            }
            
            // 중력 적용
            applyGravity(field);
        }
        
        sb.append(chainCount);
        System.out.println(sb.toString());
    }
    
    // BFS로 연결된 같은 색 뿌요 그룹 찾기
    static boolean bfs(char[][] field, boolean[][] visited, int startR, int startC, char color, List<int[]> group) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        group.add(new int[]{startR, startC});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                
                if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && 
                    !visited[nr][nc] && field[nr][nc] == color) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    group.add(new int[]{nr, nc});
                }
            }
        }
        
        return group.size() >= 4;
    }
    
    // 중력 적용 - 뿌요들을 아래로 떨어뜨리기
    static void applyGravity(char[][] field) {
        for (int j = 0; j < COLS; j++) {
            int bottom = ROWS - 1;
            
            // 아래에서부터 올라가면서 뿌요를 채움
            for (int i = ROWS - 1; i >= 0; i--) {
                if (field[i][j] != '.') {
                    field[bottom][j] = field[i][j];
                    if (bottom != i) {
                        field[i][j] = '.';
                    }
                    bottom--;
                }
            }
        }
    }
}