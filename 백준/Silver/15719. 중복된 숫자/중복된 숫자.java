import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long expectedSum = (long)(N-1) * N / 2;
        long actualSum = 0;
        
        for(int i = 0; i<N; i++){
            actualSum += Integer.parseInt(st.nextToken());
        }

        System.out.println(actualSum - expectedSum);
    }
}