import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        if (K < N) {
            System.out.println("YES");
        } else {
            if (N % 2 == 0) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
