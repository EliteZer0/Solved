import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;

    static class Node implements Comparable<Node> {
        int idx;
        long dist;
        Node(int i, long d) { idx = i; dist = d; }
        public int compareTo(Node o) { return Long.compare(this.dist, o.dist); }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[M];
        int[] B = new int[M];
        int[] deg = new int[N + 1];

        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            A[t] = Integer.parseInt(st.nextToken());
            B[t] = Integer.parseInt(st.nextToken());
            deg[A[t]]++;
            deg[B[t]]++;
        }

        // 각 지역의 등장 시간 배열(times)과 해당 발생의 전역 인덱스(idx)
        int[][] times = new int[N + 1][];
        int[][] idxOf = new int[N + 1][];
        for (int v = 1; v <= N; v++) {
            times[v] = new int[deg[v]];
            idxOf[v] = new int[deg[v]];
        }
        int[] ptr = new int[N + 1];

        final int OCC = 2 * M;           // 발생 노드 수
        int[] node = new int[OCC];       // 발생 노드의 지역
        int[] time = new int[OCC];       // 발생 시각(0..M-1)
        int[] opp  = new int[OCC];       // 같은 분에 연결된 반대편 지역

        // 전역 인덱스 채우기
        int gid = 0;
        for (int t = 0; t < M; t++) {
            int a = A[t], b = B[t];

            node[gid] = a; time[gid] = t; opp[gid] = b;
            idxOf[a][ptr[a]] = gid; times[a][ptr[a]] = t; ptr[a]++; gid++;

            node[gid] = b; time[gid] = t; opp[gid] = a;
            idxOf[b][ptr[b]] = gid; times[b][ptr[b]] = t; ptr[b]++; gid++;
        }

        // 같은 지역 내에서 다음 발생으로 가는 간선
        int[] nextIdx = new int[OCC];
        int[] waitDelta = new int[OCC];
        for (int v = 1; v <= N; v++) {
            int k = times[v].length;
            for (int j = 0; j < k; j++) {
                int cur = idxOf[v][j];
                int nxt = idxOf[v][(j + 1) % k];
                nextIdx[cur] = nxt;
                int dt = (j + 1 < k) ? (times[v][j + 1] - times[v][j])
                                      : (times[v][0] + M - times[v][j]);
                waitDelta[cur] = dt;
            }
        }

        // 시작: 시간 0, 지역 1
        if (deg[1] == 0) { // 연결 그래프 조건상 사실상 없음
            System.out.println(-1);
            return;
        }
        long[] dist = new long[OCC];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 지역 1의 첫 등장으로 진입
        int firstIdx = idxOf[1][0];
        long startWait = times[1][0]; // 0분에서 해당 분까지 대기
        dist[firstIdx] = startWait;
        pq.add(new Node(firstIdx, startWait));

        long answer = (1 == N) ? 0L : INF;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int g = cur.idx;
            if (cur.dist != dist[g]) continue;
            if (cur.dist >= answer) break; // 더 좋은 해 없음

            int v = node[g];
            int t = time[g];
            int u = opp[g];

            // 1) 지금 분 t에 바로 횡단: t+1에 u 도착
            long arrive = cur.dist + 1;
            if (u == N) {
                if (arrive < answer) answer = arrive;
            }
            // 도착 후 u의 다음 등장으로 점프
            // threshold = t+1
            int threshold = t + 1;
            int[] tv = times[u];
            int pos = lowerBound(tv, threshold);
            int targetIdx;
            long addWait;
            if (pos < tv.length) {
                targetIdx = idxOf[u][pos];
                addWait = tv[pos] - threshold;
            } else {
                targetIdx = idxOf[u][0];
                addWait = tv[0] + (long)M - threshold;
            }
            long nd = arrive + addWait;
            if (nd < dist[targetIdx]) {
                dist[targetIdx] = nd;
                pq.add(new Node(targetIdx, nd));
            }

            // 이번 기회를 건너뛰고 같은 지역의 다음 등장까지 대기
            int nxt = nextIdx[g];
            long nd2 = cur.dist + waitDelta[g];
            if (nd2 < dist[nxt]) {
                dist[nxt] = nd2;
                pq.add(new Node(nxt, nd2));
            }
        }

        System.out.println(answer);
    }

    static int lowerBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] >= x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
