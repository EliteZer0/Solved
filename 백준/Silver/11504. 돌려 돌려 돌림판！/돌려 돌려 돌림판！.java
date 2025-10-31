import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // X 읽기
            st = new StringTokenizer(br.readLine());
            StringBuilder xStr = new StringBuilder();
            for (int i = 0; i < M; i++) {
                xStr.append(st.nextToken());
            }
            long X = Long.parseLong(xStr.toString());
            
            // Y 읽기
            st = new StringTokenizer(br.readLine());
            StringBuilder yStr = new StringBuilder();
            for (int i = 0; i < M; i++) {
                yStr.append(st.nextToken());
            }
            long Y = Long.parseLong(yStr.toString());
            
            // 돌림판 읽기
            st = new StringTokenizer(br.readLine());
            int[] spinner = new int[N];
            for (int i = 0; i < N; i++) {
                spinner[i] = Integer.parseInt(st.nextToken());
            }
            
            // 각 시작 위치에서 M자리 수를 만들어 범위 확인
            int count = 0;
            for (int start = 0; start < N; start++) {
                StringBuilder zStr = new StringBuilder();
                for (int i = 0; i < M; i++) {
                    zStr.append(spinner[(start + i) % N]);
                }
                long Z = Long.parseLong(zStr.toString());
                
                if (X <= Z && Z <= Y) {
                    count++;
                }
            }
            
            sb.append(count).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}