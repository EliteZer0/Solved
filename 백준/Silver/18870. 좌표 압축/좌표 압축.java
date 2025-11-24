import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(sorted[i])) {
                map.put(sorted[i], rank);
                rank++;
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i]));
            if (i < N - 1) {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}