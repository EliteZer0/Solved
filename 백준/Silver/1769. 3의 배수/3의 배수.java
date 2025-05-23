import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numStr = br.readLine();

        int cnt = 0;

        // sum을 numStr 길이에 따라 구분
        while (numStr.length() > 1) {
            int sum = 0;
            for (int i = 0; i < numStr.length(); i++) {
                sum += numStr.charAt(i) - '0';
            }
            numStr = Integer.toString(sum);
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        if ((numStr.charAt(0) - '0') % 3 == 0) sb.append("YES");
        else sb.append("NO");

        System.out.print(sb.toString());
    }
}