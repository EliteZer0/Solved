import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int l = 0;
        int odd = 0;
        int even = 0;

        // 오른쪽 포인터를 증가시키면서 진행
        for (int r = 0; r < N; r++) {
            if (arr[r] % 2 == 0) even++;
            else odd++;

            // 오른쪽 포인터를 최대한 증가 시키고 나서 홀수의 개수가 K개를 넘으면
            // 왼쪽 포인터를 증가시켜서 홀수의 개수를 만족할 때까지 오른쪽으로 이동
            while (odd > K) {
                if (arr[l] % 2 == 0) even--;
                else odd--;
                l++;
            }

            ans = Math.max(ans, even);
        }
        
        System.out.println(ans);
    }
}