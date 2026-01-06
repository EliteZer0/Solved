import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.equals(".")) break;

            Deque<Character> stack = new ArrayDeque<>();
            boolean ok = true;

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (ch == '(' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        ok = false;
                        break;
                    }
                    stack.pop();
                } else if (ch == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        ok = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if (ok && stack.isEmpty()) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.print(sb.toString());
    }
}
