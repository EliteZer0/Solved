import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do c = readByte(); while (c <= ' '); // skip spaces
            int sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int[] arr = new int[N];
        int[] freq = new int[8001]; // index: value + 4000

        long sum = 0;
        int min = 4001, max = -4001;

        for (int i = 0; i < N; i++) {
            int x = fs.nextInt();
            arr[i] = x;
            sum += x;
            freq[x + 4000]++;
            if (x < min) min = x;
            if (x > max) max = x;
        }

        Arrays.sort(arr);

        int mean = (int) Math.round((double) sum / N);
        int median = arr[N / 2];
        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int mode = 0;
        boolean firstFound = false;
        for (int i = 0; i < 8001; i++) {
            if (freq[i] == maxFreq) {
                int value = i - 4000;
                if (!firstFound) {
                    mode = value;
                    firstFound = true;
                } else {
                    mode = value;
                    break;
                }
            }
        }

        int range = max - min;

        StringBuilder sb = new StringBuilder();
        sb.append(mean).append('\n');
        sb.append(median).append('\n');
        sb.append(mode).append('\n');
        sb.append(range).append('\n');
        System.out.print(sb);
    }
}
