import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            
            while (!stack.isEmpty() && stack.peek()[0] < height) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append(0).append(" ");
            } else {
                sb.append(stack.peek()[1]).append(" ");
            }

            stack.push(new int[]{height, i});
        }
        
        System.out.println(sb.toString());
    }
}