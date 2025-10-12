import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        long left = 1;
        long right = (long)Arrays.stream(A).min().getAsInt() * M;
        long answer = right;
        
        while(left <= right){
            long mid = (left + right) / 2;
            long balloonCnt = 0;
            
            for(int i = 0; i < N; i++){
                balloonCnt += mid / A[i];
                if(balloonCnt >= M) break; // 오버플로우 방지
            }
            
            if(balloonCnt >= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}