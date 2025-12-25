import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();

    static void dfs(long num, int lastDigit) {
        list.add(num);
        for (int next = 0; next < lastDigit; next++) {
            dfs(num * 10 + next, next);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        if (N > 1022) {
            System.out.println(-1);
            return;
        }

        for (int start = 0; start <= 9; start++) {
            dfs(start, start);
        }

        Collections.sort(list);
        System.out.println(list.get(N));
    }
}
