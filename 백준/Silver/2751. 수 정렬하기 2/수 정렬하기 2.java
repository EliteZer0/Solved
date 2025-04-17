import java.util.*;
import java.io.*;

public class Main {
    private static byte[] buffer = new byte[1 << 16];
    private static int bufferPointer = 0, bytesRead = 0;

    private static int read() throws IOException {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0;
            bytesRead = System.in.read(buffer);
            if (bytesRead == -1) return -1;
        }
        return buffer[bufferPointer++];
    }

    private static int nextInt() throws IOException {
        int result = 0;
        int c = read();
        
        // 공백 문자 패스
        while (c <= ' ') c = read();
        
        // 양수 또는 음수 판별
        boolean negative = (c == '-');
        if (negative) c = read();

        // 숫자 부분 읽기
        do {
            result = result * 10 + (c - '0');
            c = read();
        } while (c >= '0' && c <= '9');
        
        return negative ? -result : result;
    }

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        PriorityQueue<Integer> nums = new PriorityQueue<>();
        for(int i = 0; i<N; i++){
            nums.offer(nextInt());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(nums.poll()).append("\n");
        }
        System.out.println(sb.toString());
    }
}