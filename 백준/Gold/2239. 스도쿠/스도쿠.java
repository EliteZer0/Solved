import java.io.*;

public class Main {
    static int[][] board = new int[9][9];
    static int[] rowMask = new int[9];
    static int[] colMask = new int[9];
    static int[] boxMask = new int[9];

    static final int FULL = (1 << 10) - 2;

    static int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    static void solve(int pos) throws IOException {
        if (pos == 81) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) sb.append(board[r][c]);
                sb.append('\n');
            }
            System.out.print(sb);
            System.exit(0);
        }

        int r = pos / 9;
        int c = pos % 9;

        if (board[r][c] != 0) {
            solve(pos + 1);
            return;
        }

        int b = boxIndex(r, c);
        int used = rowMask[r] | colMask[c] | boxMask[b];
        int possible = (~used) & FULL;

        for (int d = 1; d <= 9; d++) {
            int bit = 1 << d;
            if ((possible & bit) == 0) continue;

            board[r][c] = d;
            rowMask[r] |= bit;
            colMask[c] |= bit;
            boxMask[b] |= bit;

            solve(pos + 1);

            board[r][c] = 0;
            rowMask[r] ^= bit;
            colMask[c] ^= bit;
            boxMask[b] ^= bit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = 0; r < 9; r++) {
            String line = br.readLine().trim();
            for (int c = 0; c < 9; c++) {
                int v = line.charAt(c) - '0';
                board[r][c] = v;
                if (v != 0) {
                    int bit = 1 << v;
                    int b = boxIndex(r, c);
                    rowMask[r] |= bit;
                    colMask[c] |= bit;
                    boxMask[b] |= bit;
                }
            }
        }

        solve(0);
    }
}
