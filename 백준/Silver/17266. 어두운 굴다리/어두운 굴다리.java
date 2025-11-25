import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        int[] lights = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            lights[i] = Integer.parseInt(st.nextToken());
        }
        
        int height = 0;

        height = Math.max(height, lights[0]);

        height = Math.max(height, N - lights[M - 1]);
        
        for (int i = 0; i < M - 1; i++) {
            int gap = lights[i + 1] - lights[i];
            int requiredHeight = (gap + 1) / 2;
            height = Math.max(height, requiredHeight);
        }
        
        System.out.println(height);
    }
}