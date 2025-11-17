import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] tokens = br.readLine().split(" ");
        int[] V = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(tokens[i]);
        }
        
        int[] speed = new int[N];
        
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1) {
                speed[i] = Math.min(V[i], 1);
            } else {
                speed[i] = Math.min(V[i], speed[i + 1] + 1);
            }
        }
        
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += speed[i];
        }
        
        System.out.println(sum);
    }
}
