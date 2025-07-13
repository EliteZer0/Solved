import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        if (n == 1) {
            System.out.println(score[0]);
            return;
        }

        dp[0] = score[0];
        dp[1] = score[0] + score[1];

        if (n >= 3) {
            dp[2] = Math.max(score[0] + score[2], score[1] + score[2]);
        }

        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + score[i], dp[i - 3] + score[i - 1] + score[i]);
        }

        System.out.println(dp[n - 1]);
    }
}
