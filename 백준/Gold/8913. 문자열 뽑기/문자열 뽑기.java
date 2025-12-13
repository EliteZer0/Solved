import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Boolean> memo;

    static boolean canEmpty(String s) {
        if (s.isEmpty()) return true;
        Boolean cached = memo.get(s);
        if (cached != null) return cached;

        int n = s.length();
        boolean moved = false;

        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;

            int len = j - i;
            if (len >= 2) {
                moved = true;
                String next = s.substring(0, i) + s.substring(j);
                if (canEmpty(next)) {
                    memo.put(s, true);
                    return true;
                }
            }
            i = j;
        }

        memo.put(s, false);
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String s = br.readLine().trim();
            memo = new HashMap<>();
            out.append(canEmpty(s) ? 1 : 0).append('\n');
        }

        System.out.print(out.toString());
    }
}
