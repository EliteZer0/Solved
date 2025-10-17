import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());   // K는 long

        int[] courses = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        long totalDistance = 0L;                   // 합도 long
        for (int i = 1; i <= N; i++) {
            courses[i] = Integer.parseInt(st.nextToken());
            totalDistance += courses[i];
        }

        int result = 0;

        // 갈 때 (1번 → N번)
        if (K < totalDistance) {
            long distance = 0L;
            for (int i = 1; i <= N; i++) {
                if (K < distance + courses[i]) {  // 경계면이면 다음 코스 규칙을 만족
                    result = i;
                    break;
                }
                distance += courses[i];
            }
        }
        // 돌아올 때 (N번 → 1번)
        else {
            K -= totalDistance; // 돌아오는 거리로 변환
            long distance = 0L;
            for (int i = N; i >= 1; i--) {
                if (K < distance + courses[i]) {  // 경계면이면 '지나야 할' 코스 선택
                    result = i;
                    break;
                }
                distance += courses[i];
            }
        }

        sb.append(result);
        System.out.println(sb.toString());
    }
}
