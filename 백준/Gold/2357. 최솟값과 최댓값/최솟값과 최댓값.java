// arr = [5, 2, 7, 3]
//                      [0,3]  (min=2, max=7)
//                    /                    \
//          [0,1] (min=2, max=5)       [2,3] (min=3, max=7)
//           /            \               /               \
//   [0,0] (5,5)   [1,1] (2,2)     [2,2] (7,7)      [3,3] (3,3)


import java.util.*;
import java.lang.*;
import java.io.*;

class SegmentTree{
    final int n;
    final int[] minTree, maxTree, arr;

    SegmentTree(int[] input){
        this.n = input.length;
        this.arr = Arrays.copyOf(input, n);
        this.minTree = new int[4*n];
        this.maxTree = new int[4*n];
        build(1, 0, n - 1);
    }

    private void build(int node, int s, int e){
        // s == e 구간이 한 칸짜리 → 리프 노드
        if(s==e){
            // minTree[node] = maxTree[node] = arr[s] 자기 값이 곧 최솟값이자 최댓값
            minTree[node] = maxTree[node] = arr[s];
            return;
        }
        int m = (s + e) / 2;
        int L = node * 2;
        int R = node * 2 + 1;
        // 왼쪽 절반 [s,m] 구간 트리
        build(L, s, m);
        // 오른쪽 절반 [m+1, e] 구간 트리
        build(R, m+1, e);
        minTree[node] = Math.min(minTree[L], minTree[R]);
        maxTree[node] = Math.max(maxTree[L], maxTree[R]);
    }

    public int[] findMinMax(int l, int r){
        return find(1, 0, n - 1, l, r);
    }

    private int[] find(int node, int s, int e, int l, int r){
        if (r < s || e < l) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}; // 항등원
        }
        if (l <= s && e <= r) {
            return new int[]{minTree[node], maxTree[node]};
        }
        int m = (s + e) / 2;
        int L = node * 2;
        int R = node * 2 + 1;
        int[] a = find(L, s, m, l, r);
        int[] b = find(R, m + 1, e, l, r);
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }

        SegmentTree seg = new SegmentTree(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int b = Integer.parseInt(st.nextToken()) - 1; // 0-based
            if (a > b) { int t = a; a = b; b = t; }

            int[] res = seg.findMinMax(a, b);
            sb.append(res[0]).append(' ').append(res[1]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
