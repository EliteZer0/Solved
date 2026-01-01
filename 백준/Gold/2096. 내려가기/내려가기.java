import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] prevMax = new int[3];
        int[] prevMin = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a0 = Integer.parseInt(st.nextToken());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                prevMax[0] = prevMin[0] = a0;
                prevMax[1] = prevMin[1] = a1;
                prevMax[2] = prevMin[2] = a2;
            } else {
                int[] curMax = new int[3];
                int[] curMin = new int[3];

                curMax[0] = Math.max(prevMax[0], prevMax[1]) + a0;
                curMax[1] = Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]) + a1;
                curMax[2] = Math.max(prevMax[1], prevMax[2]) + a2;

                curMin[0] = Math.min(prevMin[0], prevMin[1]) + a0;
                curMin[1] = Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]) + a1;
                curMin[2] = Math.min(prevMin[1], prevMin[2]) + a2;

                prevMax = curMax;
                prevMin = curMin;
            }
        }

        int maxAns = Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]);
        int minAns = Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]);

        System.out.println(maxAns + " " + minAns);
    }
}
