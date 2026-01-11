import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Container {
        int p, w;
        Container(int p, int w) { this.p = p; this.w = w; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayDeque<Container> rail = new ArrayDeque<>();
        int[] remain = new int[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            rail.addLast(new Container(p, w));
            remain[p]++;
        }

        // 숫자가 가장 큰 것부터
        int cur = M;
        while (cur >= 1 && remain[cur] == 0) cur--;

        // 우선순위별 적재 스택
        int[][] stack = new int[M + 1][N]; // 최악 N개 들어감
        int[] top = new int[M + 1]; // 각 스택의 size 역할

        long cost = 0;

        while (!rail.isEmpty()) {
            Container c = rail.pollFirst();

            if (c.p != cur) {
                // 더 높은 우선순위가 먼저 왔으니 레일 맨 앞으로 보냄
                cost += c.w;
                rail.addLast(c);
                continue;
            }

            // c.p == cur 적재
            int p = c.p;
            int w = c.w;

            // 가벼운 컨테이너들을 임시로 빼기
            int[] temp = new int[N];
            int tSize = 0;

            while (top[p] > 0 && stack[p][top[p] - 1] < w) {
                int x = stack[p][--top[p]];
                cost += x;          // 빼는 비용
                temp[tSize++] = x;  // 임시 저장
            }

            // 새 컨테이너 적재
            cost += w;
            stack[p][top[p]++] = w;

            // 마지막에 뺀 것부터 다시 적재
            for (int i = tSize - 1; i >= 0; i--) {
                int x = temp[i];
                cost += x;
                stack[p][top[p]++] = x;
            }

            // 현재 우선순위 남은 개수 감소
            remain[p]--;
            if (remain[p] == 0) {
                while (cur >= 1 && remain[cur] == 0) cur--;
            }
        }

        System.out.println(cost);
    }
}