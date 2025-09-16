import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        char[][] map = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        char[][] newMap = new char[R][C];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    int seaCount = 0;
                    
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];
                        
                        if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == '.') {
                            seaCount++;
                        }
                    }

                    if (seaCount >= 3) {
                        newMap[i][j] = '.';
                    } else {
                        newMap[i][j] = 'X';
                    }
                } else {
                    newMap[i][j] = '.';
                }
            }
        }
        
        int minRow = R, maxRow = -1;
        int minCol = C, maxCol = -1;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (newMap[i][j] == 'X') {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                sb.append(newMap[i][j]);
            }
            sb.append('\n');
        }
        
        System.out.print(sb.toString());
    }
}