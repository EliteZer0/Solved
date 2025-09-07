import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] logs = new int[N];
    
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i<N; i++){
                logs[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(logs);
            
            int difficulty = Math.max(logs[1]-logs[0], logs[N-1]-logs[N-2]);
            for(int i = 0; i<N-2; i++){
                difficulty = Math.max(difficulty, logs[i+2]-logs[i]);
            }
            sb.append(difficulty).append("\n");
        }
        System.out.println(sb.toString());
    }
}