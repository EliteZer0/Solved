import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        int x = 0;

        while(N>sum){
            x++;
            sum += x;
        }
        
        int p = 0;
        int q = 0;
        
        if(x%2==0) {
            p = N - sum + x;
            q = x-p+1;
        }else {
            q = N - sum + x;
            p = x-q+1;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(p).append("/").append(q);
        System.out.println(sb.toString());
    }
}