import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] cnt = new int[31];
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine().trim());
            cnt[x]++;
        }

        int k = (int) Math.round(n * 0.15);
        int remove = k;
        for (int v = 1; v <= 30 && remove > 0; v++) {
            int take = Math.min(remove, cnt[v]);
            cnt[v] -= take;
            remove -= take;
        }

        remove = k;
        for (int v = 30; v >= 1 && remove > 0; v--) {
            int take = Math.min(remove, cnt[v]);
            cnt[v] -= take;
            remove -= take;
        }

        long sum = 0;
        long remain = 0;
        for (int v = 1; v <= 30; v++) {
            sum += (long) v * cnt[v];
            remain += cnt[v];
        }

        long ans = Math.round((double) sum / remain);
        System.out.println(ans);
    }
}
