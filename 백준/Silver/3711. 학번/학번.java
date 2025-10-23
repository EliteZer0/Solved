import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int G = Integer.parseInt(br.readLine().trim());
            int[] a = new int[G];
            for (int i = 0; i < G; i++) a[i] = Integer.parseInt(br.readLine().trim());

            if (G == 1) { // 하나면 어떤 m에서도 충돌 없음 → 최소 양의 정수 1
                out.append(1).append('\n');
                continue;
            }

            // 모든 |ai - aj|의 약수를 '나쁜 m' 집합에 모은다
            HashSet<Integer> bad = new HashSet<>();
            int maxDiff = 0;

            for (int i = 0; i < G; i++) {
                for (int j = i + 1; j < G; j++) {
                    int d = Math.abs(a[i] - a[j]);
                    if (d == 0) continue;
                    if (d > maxDiff) maxDiff = d;

                    int r = (int)Math.sqrt(d);
                    for (int k = 1; k <= r; k++) {
                        if (d % k == 0) {
                            bad.add(k);
                            bad.add(d / k);
                        }
                    }
                }
            }

            int m = G;
            while (true) {
                if (!bad.contains(m)) { // 어떤 차이도 m으로 나눠떨어지지 않음 → 모든 나머지 서로 다름
                    out.append(m).append('\n');
                    break;
                }
                m++;
                // 참고: m이 maxDiff보다 커지면 자동으로 정답이 됨(어떤 차이도 m으로 0이 될 수 없음).
            }
        }

        System.out.print(out.toString());
    }
}
