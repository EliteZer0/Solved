import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[] d = new int[n];
        int[] v = new int[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] sumD = new int[n + 1];
        int[] sumV = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            sumD[i + 1] = sumD[i] + d[i];
            sumV[i + 1] = sumV[i] + v[i];
        }
        
        int minT = 0;
        
        for (int i = 0; i < n; i++) {
            int requiredT = sumV[i + 1] - sumD[i];
            minT = Math.max(minT, requiredT);
        }
        
        System.out.println(minT);
    }
}