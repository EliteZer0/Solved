import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, SIZE;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        SIZE = 1 << N;

        map = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] levels = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            levels[i] = Integer.parseInt(st.nextToken());
        }

        for (int l : levels) {
            rotate(1 << l); // 2^l 크기 블록 회전
            meltIce();
        }

        int totalIce = countTotalIce();
        int maxChunk = findLargestIceChunk();

        sb.append(totalIce).append("\n").append(maxChunk);
        System.out.println(sb.toString());
    }

    static void rotate(int size) {
        int[][] newMap = new int[SIZE][SIZE];
        for (int r = 0; r < SIZE; r += size) {
            for (int c = 0; c < SIZE; c += size) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        newMap[r + j][c + size - 1 - i] = map[r + i][c + j];
                    }
                }
            }
        }
        map = newMap;
    }

    static void meltIce() {
        List<int[]> toMelt = new ArrayList<>();

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (map[r][c] == 0) continue;
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && map[nr][nc] > 0) {
                        count++;
                    }
                }
                if (count < 3) {
                    toMelt.add(new int[]{r, c});
                }
            }
        }

        for (int[] pos : toMelt) {
            map[pos[0]][pos[1]]--;
        }
    }

    static int countTotalIce() {
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                total += map[i][j];
            }
        }
        return total;
    }

    static int findLargestIceChunk() {
        boolean[][] visited = new boolean[SIZE][SIZE];
        int maxSize = 0;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (!visited[r][c] && map[r][c] > 0) {
                    int size = 1;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = cur[0] + dr[d];
                            int nc = cur[1] + dc[d];
                            if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && !visited[nr][nc] && map[nr][nc] > 0) {
                                visited[nr][nc] = true;
                                queue.offer(new int[]{nr, nc});
                                size++;
                            }
                        }
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        return maxSize;
    }
}