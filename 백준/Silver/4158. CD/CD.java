import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            
            if (N == 0 && M == 0) {
                break;
            }
            
            Set<Integer> sanggeunCDs = new HashSet<>();
            for (int i = 0; i < N; i++) {
                sanggeunCDs.add(Integer.parseInt(br.readLine()));
            }
            
            int commonCount = 0;
            for (int i = 0; i < M; i++) {
                int cd = Integer.parseInt(br.readLine());
                if (sanggeunCDs.contains(cd)) {
                    commonCount++;
                }
            }
            
            sb.append(commonCount).append("\n");
        }
        
        System.out.print(sb);
    }
}