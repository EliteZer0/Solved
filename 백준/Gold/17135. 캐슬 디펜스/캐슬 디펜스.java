import java.io.*;
import java.util.*;

public class Main {
    // 맵 크기와 사거리 관련 변수
    static int N, M, D;
    // 선택된 궁수 위치
    static int[] archers = new int[3];
    // 원본 맵 데이터
    static int[][] map;
    // 최대 제거 적 수
    static int MAX = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 처리
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        // 맵 초기화
        map = new int[N][M];
        
        // 맵 데이터 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 궁수 위치 조합 탐색
        selectArcherPositions(0, 0);
        
        // 결과 출력
        System.out.println(MAX);
    }
    
    // 궁수 위치 조합을 선택
    public static void selectArcherPositions(int count, int start) {
        // 3명의 궁수가 모두 선택됨
        if (count == 3) {
            MAX = Math.max(MAX, simulateGame());
            return;
        }
        
        // 모든 가능한 위치 조합 탐색
        for (int i = start; i < M; i++) {
            archers[count] = i;
            selectArcherPositions(count + 1, i + 1);
        }
    }
    
    /**
     * 선택된 궁수 위치로 게임 시뮬레이션 수행
     * @return 제거한 적의 수
     */
    public static int simulateGame() {
        // 제거한 적의 수
        int enemiesKilled = 0;
        
        // 맵 복사본 생성
        int[][] gamemap = new int[N][];
        for (int i = 0; i < N; i++) {
            gamemap[i] = new int[M];
            System.arraycopy(map[i], 0, gamemap[i], 0, M);
        }
        
        // 모든 적이 사라질 때까지 게임 진행
        for (int turn = 0; turn < N; turn++) {
            // 이번 턴에 제거할 적의 위치를 저장
            Set<Point> targetsToRemove = new HashSet<>();
            
            // 각 궁수에 대해 공격 대상 선택
            for (int archerIdx = 0; archerIdx < 3; archerIdx++) {
                int archerPos = archers[archerIdx];
                
                // 가장 가까운 적부터 공격
                Point target = findTarget(gamemap, archerPos);
                
                // 공격 대상이 있으면 목록에 추가
                if (target != null) {
                    targetsToRemove.add(target);
                }
            }
            
            // 선택된 적들 제거 및 카운트 증가
            for (Point target : targetsToRemove) {
                int r = target.r;
                int c = target.c;
                
                if (gamemap[r][c] == 1) {
                    gamemap[r][c] = 0;  // 적 제거
                    enemiesKilled++;    // 킬 카운트 증가
                }
            }
            
            // 적 이동 (가장 아래 줄은 사라지고, 나머지는 한 칸씩 아래로)
            moveEnemies(gamemap);
        }
        
        return enemiesKilled;
    }
    
    // 적들을 아래로 한 칸씩 이동시키는 함수
    // 맨 아래 줄(N-1)의 적들은 맵에서 사라짐
    private static void moveEnemies(int[][] gamemap) {
        // 아래 행부터 위로 이동 (덮어쓰기 방지)
        for (int i = N - 1; i > 0; i--) {
            System.arraycopy(gamemap[i-1], 0, gamemap[i], 0, M);
        }
        
        // 첫 번째 행은 비움
        Arrays.fill(gamemap[0], 0);
    }
    
    // 좌표 저장을 위한 Point 클래스
    static class Point {
        int r, c, distance;
        
        Point(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    
    // 특정 궁수의 공격 대상을 찾는 함수
    private static Point findTarget(int[][] gamemap, int archerCol) {
    boolean[][] visited = new boolean[N][M];
    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(N, archerCol, 0));

    int[] dr = {0, -1, 0};  // 왼, 위, 오 방향
    int[] dc = {-1, 0, 1};

    while (!queue.isEmpty()) {
        Point now = queue.poll();
        for (int dir = 0; dir < 3; dir++) {
            int nr = now.r + dr[dir];
            int nc = now.c + dc[dir];
            int nd = now.distance + 1;

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                visited[nr][nc] = true;
                if (gamemap[nr][nc] == 1 && nd <= D) {
                    return new Point(nr, nc, nd);
                }
                if (nd < D) {
                    queue.offer(new Point(nr, nc, nd));
                }
            }
        }
    }

    return null;
}

}