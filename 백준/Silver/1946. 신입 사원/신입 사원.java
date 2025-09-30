import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] applicants = new int[N][2];
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                applicants[i][0] = Integer.parseInt(st.nextToken()); // 서류 순위
                applicants[i][1] = Integer.parseInt(st.nextToken()); // 면접 순위
            }
            
            // 서류 성적 기준으로 정렬
            Arrays.sort(applicants, (a, b) -> Integer.compare(a[0], b[0]));
            
            int count = 1; // 서류 1등은 무조건 선발
            int minInterviewRank = applicants[0][1]; // 현재까지 선발된 사람 중 최고 면접 성적
            
            for (int i = 1; i < N; i++) {
                // 현재 지원자의 면접 성적이 이전 선발자들 중 최고보다 좋으면 선발
                if (applicants[i][1] < minInterviewRank) {
                    count++;
                    minInterviewRank = applicants[i][1];
                }
            }
            
            sb.append(count).append('\n');
        }
        
        System.out.print(sb);
    }
}