import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int tcNum = 1; tcNum < T+1; tcNum++) {
            
            int N = sc.nextInt();
            int P = sc.nextInt();
            int Q = N/P;
            int R = N%P;
            long answer = 1;

            for(int i =0; i<(P-R); i++){
                answer *= Q;
            }
            for(int i = 0; i<R; i++){
                answer *= (Q+1);
            }
            System.out.printf("#%d %d\n", tcNum, answer);
        }
    }
}