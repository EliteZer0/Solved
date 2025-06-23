import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 기차 수
        int M = Integer.parseInt(st.nextToken()); // 명령 수

        int[] train = new int[N + 1]; // 기차는 1번부터 시작

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int seat = Integer.parseInt(st.nextToken());
                train[idx] |= (1 << (seat - 1));
            } else if (cmd == 2) {
                int seat = Integer.parseInt(st.nextToken());
                train[idx] &= ~(1 << (seat - 1));
            } else if (cmd == 3) {
                train[idx] = (train[idx] << 1) & ((1 << 20) - 1); // 왼쪽으로 쉬프트 후, 20비트 유지
            } else if (cmd == 4) {
                train[idx] >>= 1;
            }
        }

        Set<Integer> states = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            states.add(train[i]);
        }

        System.out.println(states.size());
    }
}
