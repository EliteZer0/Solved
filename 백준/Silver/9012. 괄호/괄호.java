import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String s = br.readLine().trim();
            int open = 0;
            boolean ok = true;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    open++;
                } else {
                    if (open == 0) {
                        ok = false;
                        break;
                    }
                    open--;
                }
            }

            if (ok && open == 0) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb.toString());
    }
}
