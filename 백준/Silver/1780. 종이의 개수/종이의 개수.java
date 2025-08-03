import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix;
    static int[] result = new int[3]; // [-1의 개수, 0의 개수, 1의 개수]
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        
        // 행렬 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 분할 정복 시작
        cut(0, 0, n);
        
        // 결과 출력
        System.out.println(result[0]); // -1의 개수
        System.out.println(result[1]); // 0의 개수
        System.out.println(result[2]); // 1의 개수
    }
    
    // 주어진 영역이 모두 같은 값인지 확인하는 함수
    private static boolean checkSection(int x, int y, int size) {
        int firstValue = matrix[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (matrix[i][j] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // 분할 정복으로 종이를 자르는 함수
    private static void cut(int x, int y, int size) {
        // 현재 영역이 모두 같은 값인지 확인
        if (checkSection(x, y, size)) {
            // 모두 같은 값이면 해당 값의 카운트를 증가
            int value = matrix[x][y];
            if (value == -1) {
                result[0]++;
            } else if (value == 0) {
                result[1]++;
            } else {
                result[2]++;
            }
        } else {
            int newSize = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cut(x + i * newSize, y + j * newSize, newSize);
                }
            }
        }
    }
}