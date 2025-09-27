import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            String word = br.readLine().trim();
            generateAnagrams(word);
        }
        
        System.out.print(sb.toString());
        br.close();
    }
    
    private static void generateAnagrams(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        
        do {
            sb.append(new String(chars)).append('\n');
        } while (nextPermutation(chars));
    }

    private static boolean nextPermutation(char[] chars) {
        int n = chars.length;
        
        int i = n - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        
        if (i < 0) {
            return false;
        }
        
        int j = n - 1;
        while (chars[j] <= chars[i]) {
            j--;
        }
        
        swap(chars, i, j);

        reverse(chars, i + 1, n - 1);
        
        return true;
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }
}