import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] clouds = new int[r][c];
        for(int i = 0; i<r; i++){
            char[] input = br.readLine().toCharArray();
            for(int j = 0; j<c; j++){
                if(clouds[i][j] == 0) clouds[i][j] = -1;
                if(input[j] == 'c') {
                    clouds[i][j] = 0;
                    for(int k = j+1; k<c; k++){
                        if(input[k] == 'c') break;
                        clouds[i][k] = clouds[i][k-1]+1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                sb.append(clouds[i][j]);
                if(j<c-1) sb.append(" ");
            }
            if(i<r-1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}