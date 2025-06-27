import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] coefficients = br.readLine().split(" ");
        int a1 = Integer.parseInt(coefficients[0]);
        int a0 = Integer.parseInt(coefficients[1]);
        
        int c = Integer.parseInt(br.readLine());
        int n0 = Integer.parseInt(br.readLine());

        boolean isBigO = true;
        for (int n = n0; n <= 100; n++) {
            long fn = a1 * (long)n + a0;
            long cn = c * (long)n;
            if (fn > cn) {
                isBigO = false;
                break;
            }
        }

        System.out.println(isBigO ? 1 : 0);
    }
}
