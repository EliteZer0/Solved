import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] parts = input.split("::", -1); // "::" 기준으로 분리
        List<String> blocks = new ArrayList<>();

        // 왼쪽 파트 처리
        if (!parts[0].isEmpty()) {
            for (String part : parts[0].split(":")) {
                blocks.add(pad(part));
            }
        }

        // 오른쪽 파트 처리
        List<String> right = new ArrayList<>();
        if (parts.length > 1 && !parts[1].isEmpty()) {
            for (String part : parts[1].split(":")) {
                right.add(pad(part));
            }
        }

        // 중간에 부족한 블록 0000으로 채우기
        while (blocks.size() + right.size() < 8) {
            blocks.add("0000");
        }

        blocks.addAll(right);
        System.out.println(String.join(":", blocks));
    }

    static String pad(String s) {
        return String.format("%4s", s).replace(' ', '0');
    }
}
