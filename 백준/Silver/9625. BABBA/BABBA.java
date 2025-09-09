import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class AB {
        int A;
        int B;
        
        AB(int a, int b){
            this.A = a;
            this.B = b;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        
        AB[] moniter = new AB[K+1];
        moniter[0] = new AB(1,0);
        
        for(int i = 1; i<=K; i++){
            AB curMoniter = moniter[i-1];
            AB newMoniter = new AB(curMoniter.B, curMoniter.A+curMoniter.B);
            moniter[i] = newMoniter;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(moniter[K].A).append(" ").append(moniter[K].B);
        System.out.println(sb.toString());
    }
}