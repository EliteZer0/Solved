import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] result = findCycle(map);
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(result[i][j]);
                // 뒤에 공백 있다고 틀렸다고 해서 추가함
                if(j<N-1) sb.append(" ");
            }
            // 줄 추가 하나 더 했다고 틀렸다고 해서 추가함
            if(i<N-1) sb.append("\n");
        }

        System.out.print(sb.toString());
    }
    
    private static int[][] findCycle(int[][] graph) {
        int[][] result = new int[N][N];
        
        // 원본 그래프를 결과 배열에 복사
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result[i][j] = graph[i][j];
            }
        }
        
        // Floyd-Warshall 알고리즘으로 이행적 폐쇄 계산
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    // i에서 j로 가는 경로가 있거나, i->k->j 경로가 있으면 1
                    if(result[i][j] == 1 || (result[i][k] == 1 && result[k][j] == 1)) {
                        result[i][j] = 1;
                    }
                }
            }
        }
        
        return result;
    }
}