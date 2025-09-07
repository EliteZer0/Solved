import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++){
            String password = br.readLine();
            String origin = br.readLine();
            
            if(password.length() < origin.length()) {
                sb.append("NO").append("\n");
                continue;
            }
            
            boolean isEncryption = false;
            int[] originCount = new int[26];
            int[] windowCount = new int[26];
            
            for(int i = 0; i < origin.length(); i++) {
                originCount[origin.charAt(i) - 'a']++;
            }
            
            for(int i = 0; i < origin.length(); i++) {
                windowCount[password.charAt(i) - 'a']++;
            }
            
            if(isAnagram(originCount, windowCount)) {
                isEncryption = true;
            }
            
            for(int i = origin.length(); i < password.length(); i++) {
                windowCount[password.charAt(i - origin.length()) - 'a']--;
                windowCount[password.charAt(i) - 'a']++;
                
                if(isAnagram(originCount, windowCount)) {
                    isEncryption = true;
                }
                
                if(isEncryption) break;
            }
            
            if(isEncryption) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    private static boolean isAnagram(int[] arr1, int[] arr2) {
        for(int i = 0; i < 26; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}