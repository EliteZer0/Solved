import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        char[][] board = new char[N][M];
        
        // 보드 입력 받기
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        int minChanges = Integer.MAX_VALUE;
        
        // 모든 가능한 8x8 위치에 대해 확인
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // 두 가지 체스판 패턴에 대해 확인
                int changes1 = countChanges(board, i, j, 'W'); // 왼쪽 위가 흰색인 경우
                int changes2 = countChanges(board, i, j, 'B'); // 왼쪽 위가 검은색인 경우
                
                minChanges = Math.min(minChanges, Math.min(changes1, changes2));
            }
        }
        
        System.out.println(minChanges);
    }
    
    // 특정 위치에서 8x8 체스판으로 만들기 위해 필요한 변경 횟수 계산
    private static int countChanges(char[][] board, int startRow, int startCol, char startColor) {
        int changes = 0;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 체스판 패턴: (i + j)가 짝수면 시작 색상, 홀수면 반대 색상
                char expectedColor;
                if ((i + j) % 2 == 0) {
                    expectedColor = startColor;
                } else {
                    expectedColor = (startColor == 'W') ? 'B' : 'W';
                }
                
                // 현재 칸의 색상이 예상 색상과 다르면 변경 필요
                if (board[startRow + i][startCol + j] != expectedColor) {
                    changes++;
                }
            }
        }
        
        return changes;
    }
}