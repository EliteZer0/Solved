import java.util.LinkedList;
import java.util.Queue;

class Node {
    int row, col, path;
    
    Node(int row, int col, int path) {
        this.row = row;
        this.col = col;
        this.path = path;
    }
}

class Solution {
    private int[] dr = {-1, 1, 0, 0};
	private int[] dc = {0, 0, -1, 1};
	
    public int solution(int m, int n, int[][] puddles) {
    	int MOD = 1000000007;
    	
    	int[][] dp = new int[n + 1][m + 1];
        
        for(int[] puddle : puddles) {
        	dp[puddle[1]][puddle[0]] = -1;
        }
    	
        dp[1][1] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 물웅덩이면 0으로 처리
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                // 위쪽에서 오는 경로의 수를 더함
                if (i > 1) {
                    dp[i][j] += dp[i-1][j];
                }
                // 왼쪽에서 오는 경로의 수를 더함
                if (j > 1) {
                    dp[i][j] += dp[i][j-1];
                }
                // 나눈 나머지를 구하는 거라 나머지를 저장해서 연산하는 것과 똑같음.
                dp[i][j] %= MOD;
            }
        }
        
        return dp[n][m];
    }
}