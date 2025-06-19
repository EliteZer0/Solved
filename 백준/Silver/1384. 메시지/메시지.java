import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int groupNumber = 1;

        while (true) {
            String line = br.readLine();
            if (line.equals("0")) break;

            int n = Integer.parseInt(line);
            String[] names = new String[n];
            char[][] messages = new char[n][n - 1];

            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().split(" ");
                names[i] = parts[0];
                for (int j = 1; j < parts.length; j++) {
                    messages[i][j - 1] = parts[j].charAt(0);
                }
            }

            List<String> nastyList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (messages[i][j] == 'N') {
                        int writerIndex = (i - j - 1 + n) % n;
                        String writer = names[writerIndex];
                        String target = names[i];
                        nastyList.add(writer + " was nasty about " + target);
                    }
                }
            }

            System.out.println("Group " + groupNumber);
            if (nastyList.isEmpty()) {
                System.out.println("Nobody was nasty");
            } else {
                for (String msg : nastyList) {
                    System.out.println(msg);
                }
            }
            System.out.println();
            groupNumber++;
        }
    }
}
