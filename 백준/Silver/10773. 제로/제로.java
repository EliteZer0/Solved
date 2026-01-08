import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int[] stack = new int[K];
        int top = 0;
        long sum = 0;

        for (int i = 0; i < K; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                int v = stack[--top];
                sum -= v;
            } else {
                stack[top++] = x;
                sum += x;
            }
        }

        System.out.println(sum);
    }
}
