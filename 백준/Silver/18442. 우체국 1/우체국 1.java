import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, P;
    static long L;
    static long[] villages;
    static long minSum = Long.MAX_VALUE;
    static int[] bestPostOffices;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        villages = new long[V];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            villages[i] = Long.parseLong(st.nextToken());
        }

        bestPostOffices = new int[P];
        findBestPostOffices(0, 0, new int[P]);

        StringBuilder sb = new StringBuilder();
        sb.append(minSum).append("\n");
        for (int i = 0; i < P; i++) {
            sb.append(villages[bestPostOffices[i]]).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void findBestPostOffices(int start, int depth, int[] selected) {
        if (depth == P) {
            long sum = calculateTotalDistance(selected);
            if (sum < minSum) {
                minSum = sum;
                for (int i = 0; i < P; i++) {
                    bestPostOffices[i] = selected[i];
                }
            }
            return;
        }
        
        for (int i = start; i < V; i++) {
            selected[depth] = i;
            findBestPostOffices(i + 1, depth + 1, selected);
        }
    }

    static long calculateTotalDistance(int[] postOffices) {
        long sum = 0;
        for (int i = 0; i < V; i++) {
            long minDis = Long.MAX_VALUE;
            for (int j = 0; j < P; j++) {
                long dis = circularDistance(villages[i], villages[postOffices[j]]);
                minDis = Math.min(minDis, dis);
                if (minDis == 0) break;
            }
            sum += minDis;
        }
        return sum;
    }
    
    static long circularDistance(long x, long y) {
        long dis = Math.abs(x - y);
        return Math.min(dis, L - dis);
    }
}