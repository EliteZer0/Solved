import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long minPrice = Long.parseLong(st.nextToken());
        long maxProfit = 0;
        
        for (int i = 1; i < N; i++) {
            long currentPrice = Long.parseLong(st.nextToken());
            
            // 현재 가격에서 팔았을 때의 이득 계산
            long profit = currentPrice - minPrice;
            maxProfit = Math.max(maxProfit, profit);
            
            // 최저가 갱신
            minPrice = Math.min(minPrice, currentPrice);
        }
        
        sb.append(maxProfit);
        System.out.print(sb);
    }
}