import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	// d, l, r, u 순서 (사전순)
	private int[] dr = {1, 0, 0, -1};
    private int[] dc = {0, -1, 1, 0};
    private char[] command = {'d', 'l', 'r', 'u'};
	
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    	int minDist = Math.abs(x - r) + Math.abs(y - c);
    	
    	// 이동 횟수보다 최단거리가 짧은 경우 이동이 불가능
    	// 이동 횟수에서 최단거리를 뺀 거리가 홀수인 경우
    	// 추가적인 움직임으로 원래 자리로 돌아오려면
    	// 짝수번의 움직임이 필요하므로 이동이 불가능 
    	if(minDist > k || (k - minDist) % 2 == 1) {
            return "impossible";
        }
    	
    	StringBuilder sb = new StringBuilder();
        int cr = x;
        int cc = y;
        
        // k번 이동
        for(int i = 0; i < k; i++) {
            int remainDistance = k - i;
            
            // 각 방향 시도
            for(int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if(nr < 1 || nr > n || nc < 1 || nc > m) continue;
                
                int newDis = Math.abs(nr - r) + Math.abs(nc - c);
                
                // 남은 이동 횟수로 도달 가능한지 확인
                if(newDis <= remainDistance - 1 && (remainDistance - 1 - newDis) % 2 == 0) {
                    sb.append(command[d]);
                    cr = nr;
                    cc = nc;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}
