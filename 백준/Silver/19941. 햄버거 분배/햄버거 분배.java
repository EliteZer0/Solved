import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        char[] banch = br.readLine().toCharArray();
        boolean[] eaten = new boolean[N];
        int cnt = 0;
        
        // 왼쪽부터 자신의 위치에서 K 이내의 햄버거 중 아직 먹히지 않은 가장 왼쪽 햄버거를 선택
        for (int i = 0; i < N; i++) {
            if (banch[i] == 'P') {
                // 왼쪽부터 K 범위 내에서 햄버거 찾기
                for (int j = i - K; j <= i + K; j++) {
                    if (j >= 0 && j < N && banch[j] == 'H' && !eaten[j]) {
                        eaten[j] = true;
                        cnt++;
                        break;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}