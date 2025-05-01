import java.io.*;
import java.util.*;

/**
 * 캐슬 디펜스 - 백준 17135번
 * N: 맵의 세로 크기
 * M: 맵의 가로 크기
 * D: 공격 사거리
 */
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
    
    /**
     * 궁수 위치 조합을 선택하는 재귀 함수
     * @param count 현재까지 선택한 궁수 수
     * @param start 선택 시작 인덱스
     */
    public static void selectArcherPositions(int count, int start) {
        // 기저 조건: 3명의 궁수가 모두 선택됨
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
        int[][] gamemap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                gamemap[i][j] = map[i][j];
            }
        }
        
        // 모든 적이 사라질 때까지 게임 진행
        for (int turn = 0; turn < N; turn++) {
            // 이번 턴에 제거할 적의 위치를 저장
            List<int[]> targetsToRemove = new ArrayList<>();
            
            // 각 궁수에 대해 공격 대상 선택
            for (int archerIdx = 0; archerIdx < 3; archerIdx++) {
                int archerPos = archers[archerIdx];
                
                // 가장 가까운 적부터 공격
                int[] target = findTarget(gamemap, archerPos);
                
                // 공격 대상이 있으면 목록에 추가
                if (target != null) {
                    // 중복된 타겟이 없는지 확인
                    boolean isDuplicate = false;
                    for (int[] existingTarget : targetsToRemove) {
                        if (existingTarget[0] == target[0] && existingTarget[1] == target[1]) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    
                    if (!isDuplicate) {
                        targetsToRemove.add(target);
                    }
                }
            }
            
            // 선택된 적들 제거 및 카운트 증가
            for (int[] target : targetsToRemove) {
                int r = target[0];
                int c = target[1];
                
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
    
    /**
     * 적들을 아래로 한 칸씩 이동시키는 함수
     * 맨 아래 줄(N-1)의 적들은 맵에서 사라짐
     * @param gamemap 게임 맵
     */
    private static void moveEnemies(int[][] gamemap) {
        // 아래 행부터 위로 이동 (덮어쓰기 방지)
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                gamemap[i][j] = gamemap[i - 1][j];
            }
        }
        
        // 첫 번째 행은 비움
        for (int j = 0; j < M; j++) {
            gamemap[0][j] = 0;
        }
    }
    
    /**
     * 특정 궁수의 공격 대상을 찾는 함수
     * @param gamemap 게임 맵
     * @param archerPos 궁수 위치 (열)
     * @return 공격 대상 위치 [행, 열], 대상 없으면 null
     */
    private static int[] findTarget(int[][] gamemap, int archerPos) {
        // 거리, 열 기준으로 정렬된 대상 목록
        PriorityQueue<int[]> targets = new PriorityQueue<>(
            (a, b) -> {
                // a = [행, 열, 거리]
                // 거리 기준 오름차순
                if (a[2] != b[2]) return a[2] - b[2];
                // 거리가 같으면 열 기준 오름차순 (왼쪽 우선)
                return a[1] - b[1];
            }
        );
        
        // 모든 맵을 탐색하며 사거리 내의 적 찾기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (gamemap[r][c] == 1) {  // 적이 있는 위치
                    // 궁수와 적 사이의 거리 계산
                    int distance = Math.abs(N - r) + Math.abs(archerPos - c);
                    
                    // 사거리 이내인 경우만 후보에 추가
                    if (distance <= D) {
                        targets.add(new int[]{r, c, distance});
                    }
                }
            }
        }
        
        // 가장 우선순위 높은 대상 반환 (없으면 null)
        if (!targets.isEmpty()) {
            int[] target = targets.poll();
            return new int[]{target[0], target[1]};  // [행, 열]만 반환
        }
        
        return null;  // 공격 가능한 적이 없음
    }
}