import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] ratings = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ratings[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int restaurant = 0; restaurant < M; restaurant++) {
            int operations = 0;

            for (int person = 0; person < N; person++) {
                int currentRating = ratings[person][restaurant];
                int countHigher = 0;

                for (int r = 0; r < M; r++) {
                    if (ratings[person][r] > currentRating) {
                        countHigher++;
                    }
                }

                if (countHigher > 0) {
                    operations++;
                }
            }
            
            sb.append(operations);
            if (restaurant < M - 1) {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}