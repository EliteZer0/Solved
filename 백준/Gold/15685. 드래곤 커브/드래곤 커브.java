import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int direction = Integer.parseInt(tokenizer.nextToken());
            int generation = Integer.parseInt(tokenizer.nextToken());

            drawDragonCurve(x, y, direction, generation);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void drawDragonCurve(int x, int y, int direction, int generation) {
        List<Integer> dirList = new ArrayList<>();
        dirList.add(direction);

        for (int i = 1; i <= generation; i++) {
            for (int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (Integer dir : dirList) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }
}
