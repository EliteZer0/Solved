import java.io.*;

public class Main {
    static char[][] grid;
    static int n, size;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        size = 4 * n - 3;
        grid = new char[size][size];
        
        // 격자를 공백으로 초기화
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
        
        // 재귀적으로 사각형 테두리 그리기
        drawSquareBorder(1);
        
        // 결과 출력
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
    
    static void drawSquareBorder(int level) {
        if (level > n) {
            return;
        }
        
        // 현재 레벨의 사각형 크기와 시작 위치
        int squareSize = 4 * level - 3;
        int start = (size - squareSize) / 2;
        
        // 테두리 그리기
        for (int i = 0; i < squareSize; i++) {
            // 위쪽과 아래쪽 테두리
            grid[start][start + i] = '*';
            grid[start + squareSize - 1][start + i] = '*';
            
            // 왼쪽과 오른쪽 테두리
            grid[start + i][start] = '*';
            grid[start + i][start + squareSize - 1] = '*';
        }
        
        // 다음 레벨 재귀 호출
        drawSquareBorder(level + 1);
    }
}