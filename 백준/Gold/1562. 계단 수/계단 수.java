import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][][] dp = new long[N + 1][10][1 << 10];

        // 초기화: 첫 자리는 1~9만 가능 (0으로 시작 불가)

        for (int i = 1; i <= 9; i++) {

            dp[1][i][1 << i] = 1;

        }

        // dp 채우기

        for (int len = 2; len <= N; len++) {

            for (int last = 0; last <= 9; last++) {

                for (int bit = 0; bit < (1 << 10); bit++) {

                    if (last > 0) {

                        int prev = last - 1;

                        int newBit = bit | (1 << last);

                        dp[len][last][newBit] = (dp[len][last][newBit] + dp[len - 1][prev][bit]) % MOD;

                    }

                    if (last < 9) {

                        int prev = last + 1;

                        int newBit = bit | (1 << last);

                        dp[len][last][newBit] = (dp[len][last][newBit] + dp[len - 1][prev][bit]) % MOD;

                    }

                }

            }

        }

        long answer = 0;

        int fullBit = (1 << 10) - 1; // 0b1111111111

        for (int i = 0; i <= 9; i++) {

            answer = (answer + dp[N][i][fullBit]) % MOD;

        }

        System.out.println(answer);

    }

}