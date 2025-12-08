import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long U = Long.parseLong(st.nextToken());
        long O = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        long maxByUS = (U + 2L * S) / 3L;
        long answer = Math.min(U, Math.min(O, maxByUS));

        System.out.println(answer);
    }
}
