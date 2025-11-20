import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());
        
        int[] prices = new int[n];
        String[] titles = new String[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            prices[i] = Integer.parseInt(st.nextToken());
            titles[i] = st.nextToken();
        }

        int[] targetCount = new int[26];
        for (char c : target.toCharArray()) {
            targetCount[c - 'A']++;
        }
        
        int minCost = Integer.MAX_VALUE;

        for (int mask = 1; mask < (1 << n); mask++) {
            int[] availableCount = new int[26];
            int totalCost = 0;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    totalCost += prices[i];
                    for (char c : titles[i].toCharArray()) {
                        availableCount[c - 'A']++;
                    }
                }
            }

            boolean canMake = true;
            for (int i = 0; i < 26; i++) {
                if (targetCount[i] > availableCount[i]) {
                    canMake = false;
                    break;
                }
            }
            
            if (canMake) {
                minCost = Math.min(minCost, totalCost);
            }
        }
        
        if (minCost == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
        }
    }
}