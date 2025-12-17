import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) arr[i] = br.readLine().trim();

        Arrays.sort(arr, (a, b) -> {
            if (a.length() != b.length()) return a.length() - b.length();

            int sa = digitSum(a);
            int sb = digitSum(b);
            if (sa != sb) return sa - sb;

            return a.compareTo(b);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : arr) sb.append(s).append('\n');
        System.out.print(sb.toString());
    }
    
    static int digitSum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') sum += (c - '0');
        }
        return sum;
    }
}
