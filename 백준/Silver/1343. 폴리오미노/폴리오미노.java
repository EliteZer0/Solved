import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String board = br.readLine();
        StringBuilder result = new StringBuilder();

        int count = 0;

        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);

            if (c == 'X') {
                count++;
            } else {
                if (count % 2 != 0) {
                    System.out.println("-1");
                    return;
                }
                result.append("AAAA".repeat(count / 4));
                count %= 4;
                result.append("BB".repeat(count / 2));
                result.append('.');
                count = 0;
            }
        }

        if (count % 2 != 0) {
            System.out.println("-1");
            return;
        }
        result.append("AAAA".repeat(count / 4));
        count %= 4;
        result.append("BB".repeat(count / 2));

        System.out.println(result);
    }
}
