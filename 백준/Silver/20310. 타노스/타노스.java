import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int len = input.length();

        int oneCnt = 0;
        int zeroCnt = 0;
        for(int i = 0; i<len; i++){
            if(input.charAt(i) == '1') oneCnt ++;
            else zeroCnt++;
        }

        int keepZero = zeroCnt / 2;
        int keepOne = oneCnt / 2;

        boolean[] keep = new boolean[len];

        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == '0') {
                keep[i] = true;
                keepZero--;
            }
            
            if(keepZero == 0) break;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (input.charAt(i) == '1') {
                keep[i] = true;
                keepOne--;
            }

            if(keepOne == 0) break;
        }

        for (int i = 0; i < len; i++) {
            if (keep[i]) {
                sb.append(input.charAt(i));
            }
        }
        
        System.out.println(sb.toString());
    }
}