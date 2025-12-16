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

        for(int i = 0; i<zeroCnt/2; i++){
            sb.append("0");
        }
        
        for(int i = 0; i<oneCnt/2; i++){
            sb.append("1");
        }
        
        System.out.println(sb.toString());
    }
}