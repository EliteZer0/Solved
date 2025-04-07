import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int hundred = b/100;
        int ten = (b%100)/10;
        int unit = b%10;
        StringBuilder sb = new StringBuilder();
        sb.append(a*unit).append("\n")
            .append(a*ten).append("\n")
            .append(a*hundred).append("\n")
            .append(a*b);
        System.out.println(sb.toString());
  }
}