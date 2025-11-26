import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] values = new int[n];
            int[] counts = new int[n];
            int totalSum = 0;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                values[i] = Integer.parseInt(st.nextToken());
                counts[i] = Integer.parseInt(st.nextToken());
                totalSum += values[i] * counts[i];
            }

            // 홀수면 불가능
            if (totalSum % 2 == 1) {
                sb.append("0\n");
                continue;
            }

            int target = totalSum / 2;

            int[] dp = new int[target + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;

            for (int i = 0; i < n; i++) {
                int value = values[i];
                int count = counts[i];

                for (int x = 0; x <= target; x++) {
                    // 이전 동전들로 x를 만들 수 있음
                    if (dp[x] >= 0) {
                        //이번 동전은 아직 안 쓴 상태, count개 여유
                        dp[x] = count;
                    }
                    // x - value를 만들 수 없거나, value가 더 큰 경우
                    else if (x < value || dp[x - value] <= 0) {
                        dp[x] = -1;
                    }
                    // x - value를 만들 수 있고, 그 상태에서 value를 아직 쓸 수 있음
                    else {
                        dp[x] = dp[x - value] - 1;
                    }
                }
            }

            if (dp[target] >= 0) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb.toString());
    }
}
