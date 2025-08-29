import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String password;
        
        while (!(password = br.readLine()).equals("end")) {
            if (isAcceptable(password)) {
                sb.append("<").append(password).append("> is acceptable.\n");
            } else {
                sb.append("<").append(password).append("> is not acceptable.\n");
            }
        }
        
        System.out.print(sb.toString());
    }
    
    private static boolean isAcceptable(String password) {
        // 조건 1: 모음을 하나 이상 포함해야 함
        if (!hasVowel(password)) {
            return false;
        }
        
        // 조건 2: 모음 3개 또는 자음 3개가 연속으로 오면 안됨
        if (hasThreeConsecutive(password)) {
            return false;
        }
        
        // 조건 3: 같은 글자가 연속으로 두 번 오면 안되나, ee와 oo는 허용
        if (hasSameConsecutiveChars(password)) {
            return false;
        }
        
        return true;
    }
    
    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    // 조건 1: 모음을 하나 이상 포함하는지 검사
    private static boolean hasVowel(String password) {
        for (char c : password.toCharArray()) {
            if (isVowel(c)) {
                return true;
            }
        }
        return false;
    }
    
    // 조건 2: 모음 3개 또는 자음 3개가 연속으로 오는지 검사
    private static boolean hasThreeConsecutive(String password) {
        if (password.length() < 3) {
            return false;
        }
        
        for (int i = 0; i <= password.length() - 3; i++) {
            char c1 = password.charAt(i);
            char c2 = password.charAt(i + 1);
            char c3 = password.charAt(i + 2);
            
            // 모음 3개 연속
            if (isVowel(c1) && isVowel(c2) && isVowel(c3)) {
                return true;
            }
            
            // 자음 3개 연속
            if (!isVowel(c1) && !isVowel(c2) && !isVowel(c3)) {
                return true;
            }
        }
        
        return false;
    }
    
    // 조건 3: 같은 글자가 연속으로 두 번 오는지 검사 (ee, oo 제외)
    private static boolean hasSameConsecutiveChars(String password) {
        for (int i = 0; i < password.length() - 1; i++) {
            char current = password.charAt(i);
            char next = password.charAt(i + 1);
            
            if (current == next) {
                // ee와 oo는 허용
                if (current == 'e' || current == 'o') {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}