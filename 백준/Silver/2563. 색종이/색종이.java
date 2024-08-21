import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] paper = new int[100][100];
        int[][] coordinates = new int[n][2];
        int x = 0;
        int y = 0;
        int size = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                coordinates[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            x = coordinates[i][0];
            y = coordinates[i][1];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (paper[99 - y - j][x + k] == 0) {
                        paper[99 - y - j][x + k] = 1;
                        size++;
                    }
                }
            }
        }

        System.out.println(size);
    }
}