import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] numsCnt = new int[10];
        for(int i = 0; i<input.length(); i++){
            int num = input.charAt(i) - '0';
            numsCnt[num]++;
        }

        numsCnt[6] = (numsCnt[6]+numsCnt[9] + 1)/2;
        numsCnt[9] = 0;
        
        int ans = 0;
        for(int i = 0; i<10; i++){
            ans = Math.max(ans, numsCnt[i]);
        }
        System.out.println(ans);
    }
}