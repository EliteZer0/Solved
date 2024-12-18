import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Oil{
	int size, startCol, endCol;

	public Oil(int size, int startCol, int endCol) {
		this.size = size;
		this.startCol = startCol;
		this.endCol = endCol;
	}
	
}

class Solution {
    private int[] dr = {-1, 1, 0, 0};
	private int[] dc = {0, 0, -1, 1};
	private int row, col;
	
	public int solution(int[][] land) {
		row = land.length;
		col = land[0].length;
		boolean[][] visited = new boolean[row][col];
        
		int[] oilSum = new int[col];
        
        List<Oil> oilList = new ArrayList<>();
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
            	if(land[i][j] == 0) continue;
            	if(visited[i][j]) continue;
                
            	int size = 0;
                int minCol = col;
                int maxCol = -1;
                
                Queue<int[]> que = new LinkedList<>();
                que.offer(new int[] {i, j});
                visited[i][j] = true;
                
                while(!que.isEmpty()) {
                    int[] cur = que.poll();
                    int cr = cur[0];
                    int cc = cur[1];
                    
                    size++;
                    minCol = Math.min(minCol, cc);
                    maxCol = Math.max(maxCol, cc);
                    
                    for(int d = 0; d < 4; d++) {
                        int mr = cr + dr[d];
                        int mc = cc + dc[d];
                        
                        if(!check(mr, mc)) continue;
                        if(visited[mr][mc]) continue;
                        if(land[mr][mc] == 0) continue;
                        
                        visited[mr][mc] = true;
                        que.offer(new int[] {mr, mc});
                    }
                }
                
                oilList.add(new Oil(size, minCol, maxCol));
            }
        }
        
        for (Oil oil : oilList) {
			for (int i = oil.startCol; i <= oil.endCol; i++) {
				oilSum[i] += oil.size;
			}
		}
        
        int answer = 0;
        
        for (int cnt : oilSum) {
			answer = Math.max(answer, cnt);
		}
        
        return answer;
    }
	
	private boolean check(int r, int c) {
		return r>=0 && r<row && c>=0 && c<col;
	}
}