import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[L];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) arr[i] = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine().trim());

        Arrays.sort(arr);

        for (int x : arr) {
            if (x == n) {
                System.out.println(0);
                return;
            }
        }

        int left = 0;
        int right = 1001;

        for (int x : arr) {
            if (x < n) left = x;
            else if (x > n) { right = x; break; }
        }

        int aCount = n - left;
        int bCount = right - n;

        int ans = aCount * bCount - 1;
        System.out.println(ans);
    }
}
