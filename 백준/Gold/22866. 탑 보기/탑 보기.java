import java.util.*;
import java.io.*;

public class Main {
    static class Building {
        int index, height;

        Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N];      // 각 건물에서 볼 수 있는 건물 수
        int[] closest = new int[N];    // 가장 가까운 건물 번호

        // 왼쪽에서 볼 수 있는 건물 계산
        Deque<Building> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                count[i] += stack.size();

                // 가장 가까운 건물 번호 갱신
                closest[i] = stack.peek().index;
            }
            stack.push(new Building(i + 1, heights[i]));
        }

        // 오른쪽에서 볼 수 있는 건물 계산
        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                count[i] += stack.size();

                int candidate = stack.peek().index;
                // 가까운 거리 갱신 or 번호가 작은 쪽으로 갱신
                if (closest[i] == 0 ||
                        Math.abs(candidate - (i + 1)) < Math.abs(closest[i] - (i + 1)) ||
                        (Math.abs(candidate - (i + 1)) == Math.abs(closest[i] - (i + 1)) && candidate < closest[i])) {
                    closest[i] = candidate;
                }
            }
            stack.push(new Building(i + 1, heights[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(count[i]);
            if (count[i] > 0) {
                sb.append(" ").append(closest[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
