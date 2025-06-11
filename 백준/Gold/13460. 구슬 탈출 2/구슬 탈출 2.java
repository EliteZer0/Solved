import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};
    
    static class State {
        int redR, redC, blueR, blueC, moves;
        
        State(int redR, int redC, int blueR, int blueC, int moves) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.moves = moves;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        
        int redR = 0, redC = 0, blueR = 0, blueC = 0;
        
        // 보드 입력 및 구슬 위치 찾기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    redR = i;
                    redC = j;
                    board[i][j] = '.'; // 빨간 구슬 위치를 빈 칸으로 변경
                } else if (board[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                    board[i][j] = '.'; // 파란 구슬 위치를 빈 칸으로 변경
                }
            }
        }
        
        System.out.println(bfs(redR, redC, blueR, blueC));
    }
    
    static int bfs(int redR, int redC, int blueR, int blueC) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(new State(redR, redC, blueR, blueC, 0));
        visited.add(redR + "," + redC + "," + blueR + "," + blueC);
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            // 10번 이상 움직였으면 실패
            if (current.moves >= 10) {
                continue;
            }
            
            // 4방향으로 기울이기
            for (int dir = 0; dir < 4; dir++) {
                int[] redResult = move(current.redR, current.redC, dir);
                int[] blueResult = move(current.blueR, current.blueC, dir);
                
                int newRedR = redResult[0];
                int newRedC = redResult[1];
                boolean redInHole = redResult[2] == 1;
                
                int newBlueR = blueResult[0];
                int newBlueC = blueResult[1];
                boolean blueInHole = blueResult[2] == 1;
                
                // 파란 구슬이 구멍에 들어가면 실패
                if (blueInHole) {
                    continue;
                }
                
                // 빨간 구슬이 구멍에 들어가면 성공
                if (redInHole) {
                    return current.moves + 1;
                }
                
                // 두 구슬이 같은 위치에 있으면 조정
                if (newRedR == newBlueR && newRedC == newBlueC) {
                    adjustPosition(current, redResult, blueResult, dir);
                    newRedR = redResult[0];
                    newRedC = redResult[1];
                    newBlueR = blueResult[0];
                    newBlueC = blueResult[1];
                }
                
                String newKey = newRedR + "," + newRedC + "," + newBlueR + "," + newBlueC;
                if (!visited.contains(newKey)) {
                    visited.add(newKey);
                    queue.offer(new State(newRedR, newRedC, newBlueR, newBlueC, current.moves + 1));
                }
            }
        }
        
        return -1; // 10번 이내에 해결할 수 없음
    }
    
    // 구슬을 특정 방향으로 움직임
    static int[] move(int r, int c, int dir) {
        int nr = r;
        int nc = c;
        boolean inHole = false;
        
        while (true) {
            int nextR = nr + dr[dir];
            int nextC = nc + dc[dir];
            
            // 벽에 부딪히면 멈춤
            if (board[nextR][nextC] == '#') {
                break;
            }
            
            nr = nextR;
            nc = nextC;
            
            // 구멍에 들어가면 표시하고 멈춤
            if (board[nr][nc] == 'O') {
                inHole = true;
                break;
            }
        }
        
        return new int[]{nr, nc, inHole ? 1 : 0};
    }
    
    // 두 구슬이 같은 위치에 있을 때 위치 조정
    static void adjustPosition(State current, int[] redResult, int[] blueResult, int dir) {
        // 이동 방향에 따라 어느 구슬이 더 앞에 있었는지 판단
        boolean redFirst = false;
        
        if (dir == 0) { // 위로 이동 - 더 위에 있던 구슬이 앞
            redFirst = (current.redR < current.blueR);
        } else if (dir == 1) { // 아래로 이동 - 더 아래에 있던 구슬이 앞
            redFirst = (current.redR > current.blueR);
        } else if (dir == 2) { // 왼쪽으로 이동 - 더 왼쪽에 있던 구슬이 앞
            redFirst = (current.redC < current.blueC);
        } else { // 오른쪽으로 이동 - 더 오른쪽에 있던 구슬이 앞
            redFirst = (current.redC > current.blueC);
        }
        
        if (redFirst) {
            // 빨간 구슬이 더 앞에 있으므로 파란 구슬을 뒤로
            blueResult[0] -= dr[dir];
            blueResult[1] -= dc[dir];
        } else {
            // 파란 구슬이 더 앞에 있으므로 빨간 구슬을 뒤로
            redResult[0] -= dr[dir];
            redResult[1] -= dc[dir];
        }
    }
}