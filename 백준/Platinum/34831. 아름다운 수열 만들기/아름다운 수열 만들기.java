import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        if (N < 6 || (N - 1) % 5 != 0) {
            System.out.println("No");
            return;
        }

        int c = (N - 1) / 5;

        int[][] cnt = new int[3][3];
        cnt[0][1] = cnt[1][0] = 2 * c;
        cnt[1][2] = cnt[2][1] = 2 * c;
        cnt[0][2] = cnt[2][0] = c;

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();

        stack.push(0);
        while (!stack.isEmpty()) {
            int u = stack.peek();
            int v = -1;
            // u에서 나가는 아직 안 쓴 간선을 찾기
            for (int nx = 0; nx < 3; nx++) {
                if (cnt[u][nx] > 0) {
                    v = nx;
                    break;
                }
            }
            if (v == -1) {
                // 더 나갈 간선이 없으면 경로에 추가
                path.add(u);
                stack.pop();
            } else {
                // 간선 하나 사용
                cnt[u][v]--;
                cnt[v][u]--;
                stack.push(v);
            }
        }

        Collections.reverse(path);

        if (path.size() != N) {
            System.out.println("No");
            return;
        }

        sb.append("Yes\n");
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(' ');
            sb.append(path.get(i));
        }
        System.out.println(sb.toString());
    }
}
