import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int scenario = 1;

        while (true) {
            String line = br.readLine();
            if (line.equals("0")) break;

            int n = Integer.parseInt(line);
            String[] names = new String[n + 1]; // 1-based indexing

            for (int i = 1; i <= n; i++) {
                names[i] = br.readLine();
            }

            int[] cnt = new int[n + 1];
            for (int i = 0; i < 2 * n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                // A 또는 B는 의미 없음, 그냥 무시
                cnt[num]++;
            }

            for (int i = 1; i <= n; i++) {
                if (cnt[i] == 1) {
                    System.out.println(scenario + " " + names[i]);
                    break;
                }
            }

            scenario++;
        }
    }
}