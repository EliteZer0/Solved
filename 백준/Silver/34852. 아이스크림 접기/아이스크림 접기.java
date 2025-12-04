import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());   // int도 되지만 long 써도 깔끔

        double sqrt3 = Math.sqrt(3.0);
        double k = (9.0 - 5.0 * sqrt3) / 3.0;
        double area = (double) N * N * k;

        System.out.printf("%.10f%n", area);
    }
}