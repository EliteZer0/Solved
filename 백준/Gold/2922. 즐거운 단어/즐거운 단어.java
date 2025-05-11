import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Main {
    static char[] input;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
        dfs(0, 0, 0, false, 1);
        System.out.println(answer);
    }

    // idx: 현재 인덱스, vc: 연속된 모음 수, cc: 연속된 자음 수, hasL: L 포함 여부, mul: 곱셈 계수
    static void dfs(int idx, int vc, int cc, boolean hasL, long mul) {
        if (vc >= 3 || cc >= 3) return;

        if (idx == input.length) {
            if (hasL) answer += mul;
            return;
        }

        char c = input[idx];

        if (c == '_') {
            // 모음 넣기 (A,E,I,O,U → 5개)
            dfs(idx + 1, vc + 1, 0, hasL, mul * 5);
            // 자음 중 L 넣기 (1개)
            dfs(idx + 1, 0, cc + 1, true, mul * 1);
            // 자음 중 L 제외한 20개 넣기
            dfs(idx + 1, 0, cc + 1, hasL, mul * 20);
        } else if ("AEIOU".indexOf(c) >= 0) {
            dfs(idx + 1, vc + 1, 0, hasL, mul);
        } else {
            dfs(idx + 1, 0, cc + 1, hasL || c == 'L', mul);
        }
    }
}
