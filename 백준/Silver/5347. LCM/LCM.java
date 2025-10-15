import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("\\s+");
            long a = Long.parseLong(input[0]);
            long b = Long.parseLong(input[1]);
            
            long lcm = (a * b) / gcd(a, b);
            sb.append(lcm).append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}