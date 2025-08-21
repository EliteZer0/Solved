import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++){
            String input = br.readLine();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            char[][] map = new char[r][c];
            for(int j = 0; j<r; j++){
                map[j] = br.readLine().toCharArray();
            }

            int cnt = 0;
            for(int j = 0; j<r; j++){
                for(int k = 0; k<c; k++){
                    if(k + 2 < c && map[j][k] == '>' && map[j][k+1] == 'o' && map[j][k+2] == '<'){
                        cnt++;
                    }
                    if(j + 2 < r && map[j][k] == 'v' && map[j+1][k] == 'o' && map[j+2][k] == '^'){
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}