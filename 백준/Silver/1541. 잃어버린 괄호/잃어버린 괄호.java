import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split("-");
        // 처음 나오는 마이너스 이후의 숫자는 전부 다 뺄셈으로 계산 가능
        int result = calculate(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            result -= calculate(parts[i]);
        }
        System.out.println(result);
    }

    static int calculate(String part) {
        String[] numbers = part.split("\\+");  // +로 split
        int sum = 0;
        for (String num : numbers) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}