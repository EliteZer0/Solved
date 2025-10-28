import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] cards = new int[N][5];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<5; j++){
                cards[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sums = new int[N][10];
        int[] best = new int[N];
        for(int i = 0; i<N; i++){
            sums[i][0] = cards[i][0]+cards[i][1]+cards[i][2];
            sums[i][1] = cards[i][0]+cards[i][1]+cards[i][3];
            best[i] = Math.max(sums[i][0]%10, sums[i][1]%10);
            sums[i][2] = cards[i][0]+cards[i][1]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][2]%10);
            sums[i][3] = cards[i][0]+cards[i][2]+cards[i][3];
            best[i] = Math.max(best[i], sums[i][3]%10);
            sums[i][4] = cards[i][0]+cards[i][2]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][4]%10);
            sums[i][5] = cards[i][0]+cards[i][3]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][5]%10);
            sums[i][6] = cards[i][1]+cards[i][2]+cards[i][3];
            best[i] = Math.max(best[i], sums[i][6]%10);
            sums[i][7] = cards[i][1]+cards[i][2]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][7]%10);
            sums[i][8] = cards[i][1]+cards[i][3]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][8]%10);
            sums[i][9] = cards[i][2]+cards[i][3]+cards[i][4];
            best[i] = Math.max(best[i], sums[i][9]%10);
        }
        
        int winner = 0;
        int score = -1;
        for(int i = 0; i<N; i++){
            if(score<=best[i]){
                score = best[i];
                winner = i+1;
            }
        }
        System.out.println(winner);
    }
}