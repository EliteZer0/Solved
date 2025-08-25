import java.util.Queue;
import java.util.LinkedList;

public class Charactor{
    int r;
    int c;
    Charactor(int r, int c){
        this.r = r;
        this.c = c;
    }
}
class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<Charactor> que = new LinkedList<>();
        que.offer(new Charactor(0,0));
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int[][] visited = new int[n][m];
        visited[0][0] = 1;
        
        while(!que.isEmpty()){
            Charactor cur = que.poll();
            int curR = cur.r;
            int curC = cur.c;
            
            if(curR == n-1 && curC == m-1) return visited[curR][curC];
            
            for(int d = 0; d<4; d++){
                int nextR = curR+dr[d];
                int nextC = curC+dc[d];
                
                if(!check(n, m, nextR, nextC)) continue;
                if(maps[nextR][nextC] != 1) continue;
                if(visited[nextR][nextC] != 0) continue;
                
                visited[nextR][nextC] = visited[curR][curC]+1;
                que.offer(new Charactor(nextR, nextC));
            }
        }
        
        
        return -1;
    }
    
    public boolean check(int n, int m, int r, int c){
        return r<n && r>=0 && c<m && c>=0;
    }
}