import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        TreeMap<Long, Integer> map = new TreeMap<>();
        long cur = 0;
        long answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                long x = Long.parseLong(st.nextToken());
                map.merge(x, 1, Integer::sum);
            } else {
                while (!map.isEmpty()) {
                    Long left = map.floorKey(cur);
                    Long right = map.ceilingKey(cur);

                    long next;
                    if (left == null) {
                        next = right;
                    } else if (right == null) {
                        next = left;
                    } else {
                        long dL = Math.abs(cur - left);
                        long dR = Math.abs(right - cur);
                        next = (dL <= dR) ? left : right;
                    }

                    answer += Math.abs(cur - next);
                    cur = next;
                    map.remove(next);
                }
            }
        }

        System.out.println(answer);
    }
}