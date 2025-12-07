import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();
        
        for (int len = 1; len <= 20; len++) {
            int patterns = 1 << (len - 1);
            boolean[] seen = new boolean[patterns];

            int mask = 0;
            int windowLen = 0;
            int fullMask = (1 << len) - 1;

            for (int i = 0; i < N; i++) {
                int bit = S.charAt(i) - '0';
                mask = ((mask << 1) & fullMask) | bit;
                if (windowLen < len) {
                    windowLen++;
                }

                if (windowLen == len) {
                    if ((mask & (1 << (len - 1))) != 0) {
                        int idx = mask ^ (1 << (len - 1));
                        seen[idx] = true;
                    }
                }
            }

            int missing = -1;
            for (int i = 0; i < patterns; i++) {
                if (!seen[i]) {
                    missing = i;
                    break;
                }
            }

            if (missing != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append('1');
                for (int b = len - 2; b >= 0; b--) {
                    if ((missing & (1 << b)) != 0) sb.append('1');
                    else sb.append('0');
                }

                System.out.println(sb.toString());
                return;
            }
        }
        System.out.println("1");
    }
}