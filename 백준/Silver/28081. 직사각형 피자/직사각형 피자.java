import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        
        // 가로 방향 커팅 == y 좌표 == 세로 구간
        int N = Integer.parseInt(br.readLine());
        long[] heights = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        long prev = 0;
        for (int i = 0; i < N; i++) {
            long curr = Long.parseLong(st.nextToken());
            heights[i] = curr - prev;
            prev = curr;
        }
        heights[N] = H - prev;
        // 세로 방향 커팅 == x 좌표 == 가로 구간
        int M = Integer.parseInt(br.readLine());
        long[] widths = new long[M + 1];
        st = new StringTokenizer(br.readLine());
        prev = 0;
        for (int i = 0; i < M; i++) {
            long curr = Long.parseLong(st.nextToken());
            widths[i] = curr - prev;
            prev = curr;
        }
        widths[M] = W - prev;
        long count = 0;
        if (M > N) {
            // widths가 더 많으니 widths를 정렬하고 heights를 순회
            Arrays.sort(widths);
            for (int i = 0; i <= N; i++) {
                long h = heights[i];
                long maxWidth = K / h;
                
                int left = 0;
                int right = M + 1;
                
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (widths[mid] <= maxWidth) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                count += left;
            }
        } else {
            // heights가 더 많으니 heights를 정렬하고 widths를 순회
            Arrays.sort(heights);
            for (int i = 0; i <= M; i++) {
                long w = widths[i];
                long maxHeight = K / w;
                
                int left = 0;
                int right = N + 1;
                
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (heights[mid] <= maxHeight) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                count += left;
            }
        }
        
        System.out.println(count);
    }
}