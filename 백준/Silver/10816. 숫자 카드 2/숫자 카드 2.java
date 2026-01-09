import java.io.*;
import java.util.*;

public class Main {

    static int[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int count = upperBound(target) - lowerBound(target);
            sb.append(count).append(" ");
        }

        System.out.println(sb.toString());
    }

    static int lowerBound(int target) {
        int left = 0, right = cards.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    static int upperBound(int target) {
        int left = 0, right = cards.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
