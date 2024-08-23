import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_use_recursion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] time = new int[n];
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
        int maxProfit = calMaxProfit(n, time, price, 0);		
        System.out.println(maxProfit);
    }
    
    static int calMaxProfit(int n, int[] time, int[] price, int day) {
    	if(day>=n) {
    		return 0;
    	}
    	int canNotWork = calMaxProfit(n, time, price, day+1);
    	int canWork = 0;
    	if(day+time[day]<=n) {
    		canWork = price[day] + calMaxProfit(n, time, price, day+time[day]);
    	}
    	return Math.max(canWork, canNotWork);
    }
}
