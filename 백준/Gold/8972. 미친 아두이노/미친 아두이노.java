import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    // 키패드 매핑: 1=좌하, 2=하, 3=우하, 4=좌, 5=제자리, 6=우, 7=좌상, 8=상, 9=우상
    static int[] dr = {0,  1,  1,  1,  0, 0, 0, -1, -1, -1};
    static int[] dc = {0, -1,  0,  1, -1, 0, 1, -1,  0,  1};

    static int sgn(int x) {
        if (x > 0) return 1;
        if (x < 0) return -1;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        int playerR = 0, playerC = 0;
        List<int[]> robots = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'I') {
                    playerR = i;
                    playerC = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'R') {
                    robots.add(new int[]{i, j});
                    board[i][j] = '.';
                }
            }
        }

        String moves = br.readLine();

        for (int moveIdx = 0; moveIdx < moves.length(); moveIdx++) {
            int direction = moves.charAt(moveIdx) - '0';

            playerR += dr[direction];
            playerC += dc[direction];

            for (int[] robot : robots) {
                if (robot[0] == playerR && robot[1] == playerC) {
                    System.out.println("kraj " + (moveIdx + 1));
                    return;
                }
            }

            for (int[] robot : robots) {
                robot[0] += sgn(playerR - robot[0]);
                robot[1] += sgn(playerC - robot[1]);
            }

            for (int[] robot : robots) {
                if (robot[0] == playerR && robot[1] == playerC) {
                    System.out.println("kraj " + (moveIdx + 1));
                    return;
                }
            }

            int[][] cnt = new int[R][C];
            for (int[] robot : robots) cnt[robot[0]][robot[1]]++;

            List<int[]> survivors = new ArrayList<>();
            for (int[] robot : robots) {
                if (cnt[robot[0]][robot[1]] == 1) survivors.add(robot);
            }
            robots = survivors;
        }

        for (int i = 0; i < R; i++) Arrays.fill(board[i], '.');
        board[playerR][playerC] = 'I';
        for (int[] robot : robots) board[robot[0]][robot[1]] = 'R';

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) sb.append(board[i][j]);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
