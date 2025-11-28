import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine().trim();
        
        if (n.length() % 2 == 1) {
            System.out.println(-1);
            return;
        }
        
        StringBuilder x = new StringBuilder();
        
        for (int i = 0; i < n.length(); i += 2) {
            int count = n.charAt(i) - '0';
            char digit = n.charAt(i + 1);
            
            if (count < 1 || count > 9) {
                System.out.println(-1);
                return;
            }
            
            if (digit < '0' || digit > '9') {
                System.out.println(-1);
                return;
            }
            
            for (int j = 0; j < count; j++) {
                x.append(digit);
            }
        }
        
        String xStr = x.toString();

        if (xStr.length() > 0 && xStr.charAt(0) == '0') {
            System.out.println(-1);
            return;
        }

        if (xStr.isEmpty()) {
            System.out.println(-1);
            return;
        }
        
        int consecutive = 1;
        for (int i = 1; i < xStr.length(); i++) {
            if (xStr.charAt(i) == xStr.charAt(i - 1)) {
                consecutive++;
                if (consecutive > 9) {
                    System.out.println(-1);
                    return;
                }
            } else {
                consecutive = 1;
            }
        }
        
        String result = lookAndSay(xStr);
        
        if (result.equals(n)) {
            System.out.println(xStr);
        } else {
            System.out.println(-1);
        }
    }
    
    static String lookAndSay(String s) {
        if (s.isEmpty()) return "";
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char current = s.charAt(i);
            int count = 1;
            
            while (i + count < s.length() && s.charAt(i + count) == current) {
                count++;
            }
            
            result.append(count).append(current);
            i += count;
        }
        
        return result.toString();
    }
}