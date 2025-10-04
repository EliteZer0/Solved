import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t<T; t++){
            char[] word = br.readLine().toCharArray();
            int len = word.length;
            int decreaseIdx = -1;

            for(int i = len-2; i>=0; i--){
                if(word[i]<word[i+1]){
                    decreaseIdx = i;
                    break;
                }
            }

            if(decreaseIdx == -1){
                sb.append(new String(word)).append("\n");
                continue;
            }

            int swapIdx = decreaseIdx+1;
            for(int j = decreaseIdx+1; j<len; j++){
                if(word[j]> word[decreaseIdx]){
                    swapIdx = j;
                }
            }

            char temp = word[decreaseIdx];
            word[decreaseIdx] = word[swapIdx];
            word[swapIdx] = temp;

            Arrays.sort(word, decreaseIdx+1, len);

            sb.append(new String(word)).append("\n");
        }
        System.out.println(sb.toString());
    }
}