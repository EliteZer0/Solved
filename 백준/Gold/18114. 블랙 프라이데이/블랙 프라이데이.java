import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int N, C;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        if (hasOneElement() || hasTwoElements() || hasThreeElements()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    // 1개 조합으로 가능한 경우
    private static boolean hasOneElement() {
        return Arrays.binarySearch(weights, C) >= 0;
    }

    // 2개 조합으로 가능한 경우
    private static boolean hasTwoElements() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = weights[i] + weights[j];
                if (sum == C) return true;
                if (sum > C) break;
            }
        }
        return false;
    }

    // 3개 조합으로 가능한 경우
    private static boolean hasThreeElements() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int remain = C - weights[i] - weights[j];
                if (remain <= 0) continue;

                // 이분 탐색을 통해 남은 값 찾기
                int idx = Arrays.binarySearch(weights, j + 1, N, remain);
                if (idx >= 0) return true;
            }
        }
        return false;
    }
}
