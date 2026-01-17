import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] arr = new int[N];
        boolean[] has = new boolean[1_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            arr[i] = x;
            has[x] = true;
            if (x > maxVal) maxVal = x;
        }

        int[] score = new int[maxVal + 1];

        for (int x : arr) {
            for (int m = x + x; m <= maxVal; m += x) {
                if (has[m]) {
                    score[x] += 1;
                    score[m] -= 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[arr[i]]);
            if (i + 1 < N) sb.append(' ');
        }
        System.out.println(sb);
    }
}
