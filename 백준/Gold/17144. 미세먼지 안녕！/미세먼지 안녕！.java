import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[][] map;
    static int R, C, T;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map = new int[R][C]; // 배열 초기화 추가
        
        for(int i = 0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int t = 0; t<T; t++){
            spread();
            filter();
        }
        int total = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] > 0) total += map[i][j];
            }
        }
        System.out.println(total);
    }
    
    private static void spread(){
        int[][] copy = new int[R][C];
        
        // 기존 값들을 복사본에 먼저 복사
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                copy[i][j] = map[i][j];
            }
        }
        
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(map[i][j] > 0){
                    int spreadAmount = map[i][j]/5;
                    int spreadCnt = 0;
                    
                    for(int d = 0; d<4; d++){
                        int nextR = i+dr[d];
                        int nextC = j+dc[d];
                        if(!check(nextR, nextC)) continue;
                        if(map[nextR][nextC] == -1) continue; // 로직 오류 수정
                        copy[nextR][nextC] += spreadAmount;
                        spreadCnt++;
                    }
                    copy[i][j] -= spreadAmount * spreadCnt; // 원래 위치에서 확산된 만큼 감소
                } 
            }
        }
        map = copy; // 결과를 원래 배열에 복사
    }
    
    private static void filter(){
        // 공기청정기 위치 찾기
        int airPurifierRow = -1;
        for(int i = 0; i < R; i++){
            if(map[i][0] == -1){
                airPurifierRow = i;
                break;
            }
        }
        
        // 위쪽 공기청정기 (반시계방향)
        // 아래로
        for(int i = airPurifierRow - 1; i > 0; i--){
            map[i][0] = map[i-1][0];
        }
        // 왼쪽으로
        for(int j = 0; j < C - 1; j++){
            map[0][j] = map[0][j+1];
        }
        // 위로
        for(int i = 0; i < airPurifierRow; i++){
            map[i][C-1] = map[i+1][C-1];
        }
        // 오른쪽으로
        for(int j = C - 1; j > 1; j--){
            map[airPurifierRow][j] = map[airPurifierRow][j-1];
        }
        map[airPurifierRow][1] = 0;
        
        // 아래쪽 공기청정기 (시계방향)
        // 위로
        for(int i = airPurifierRow + 2; i < R - 1; i++){
            map[i][0] = map[i+1][0];
        }
        // 왼쪽으로
        for(int j = 0; j < C - 1; j++){
            map[R-1][j] = map[R-1][j+1];
        }
        // 아래로
        for(int i = R - 1; i > airPurifierRow + 1; i--){
            map[i][C-1] = map[i-1][C-1];
        }
        // 오른쪽으로
        for(int j = C - 1; j > 1; j--){
            map[airPurifierRow + 1][j] = map[airPurifierRow + 1][j-1];
        }
        map[airPurifierRow + 1][1] = 0;
    }
    
    private static boolean check(int r, int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}