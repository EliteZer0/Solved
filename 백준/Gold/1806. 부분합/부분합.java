import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int l = 0;

        for (int r = 0; r < N; r++) {
            sum += arr[r];

            while (sum >= S) {
                ans = Math.min(ans, r - l + 1);
                sum -= arr[l++];
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }
}
