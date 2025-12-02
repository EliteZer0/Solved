import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] arr = new int[9];
        int zeroCnt = 0;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i * 3 + j] = Integer.parseInt(st.nextToken());
                if (arr[i * 3 + j] == 0) zeroCnt++;
            }
        }
        
        int magicSum = findMagicSum(arr);
        
        for (int i = 0; i < zeroCnt; i++) {
            fillZeros(arr, magicSum);
        }
        
        for (int i = 0; i < 9; i++) {
            sb.append(arr[i]);
            if (i % 3 < 2) sb.append(" ");
            else if (i < 8) sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    // 마법수 찾기
    static int findMagicSum(int[] arr) {
        // 행 확인 (0,1,2 / 3,4,5 / 6,7,8)
        for (int i = 0; i < 3; i++) {
            int idx = i * 3;
            if (arr[idx] != 0 && arr[idx+1] != 0 && arr[idx+2] != 0) {
                return arr[idx] + arr[idx+1] + arr[idx+2];
            }
        }
        
        // 열 확인 (0,3,6 / 1,4,7 / 2,5,8)
        for (int j = 0; j < 3; j++) {
            if (arr[j] != 0 && arr[j+3] != 0 && arr[j+6] != 0) {
                return arr[j] + arr[j+3] + arr[j+6];
            }
        }
        
        // 좌우대각선 (0,4,8)
        if (arr[0] != 0 && arr[4] != 0 && arr[8] != 0) {
            return arr[0] + arr[4] + arr[8];
        }
        
        // 우좌대각선 (2,4,6)
        if (arr[2] != 0 && arr[4] != 0 && arr[6] != 0) {
            return arr[2] + arr[4] + arr[6];
        }
        
        int totalSum = 0;
        for (int i = 0; i < 9; i++) {
            totalSum += arr[i];
        }
        
        return totalSum / 2;
    }
    

    static void fillZeros(int[] arr, int magicSum) {
        // 행 확인
        for (int i = 0; i < 3; i++) {
            int idx = i * 3;
            int zeroCount = 0;
            int zeroIdx = -1;
            int sum = 0;
            
            for (int j = 0; j < 3; j++) {
                if (arr[idx + j] == 0) {
                    zeroCount++;
                    zeroIdx = idx + j;
                } else {
                    sum += arr[idx + j];
                }
            }
            
            if (zeroCount == 1) {
                arr[zeroIdx] = magicSum - sum;
            }
        }
        
        // 열 확인
        for (int j = 0; j < 3; j++) {
            int zeroCount = 0;
            int zeroIdx = -1;
            int sum = 0;
            
            for (int i = 0; i < 3; i++) {
                int idx = i * 3 + j;
                if (arr[idx] == 0) {
                    zeroCount++;
                    zeroIdx = idx;
                } else {
                    sum += arr[idx];
                }
            }
            
            if (zeroCount == 1) {
                arr[zeroIdx] = magicSum - sum;
            }
        }
        
        // 대각선 (0,4,8)
        int[] diag1 = {0, 4, 8};
        int zeroCount = 0;
        int zeroIdx = -1;
        int sum = 0;
        
        for (int idx : diag1) {
            if (arr[idx] == 0) {
                zeroCount++;
                zeroIdx = idx;
            } else {
                sum += arr[idx];
            }
        }
        
        if (zeroCount == 1) {
            arr[zeroIdx] = magicSum - sum;
        }
        
        // 대각선 (2,4,6)
        int[] diag2 = {2, 4, 6};
        zeroCount = 0;
        zeroIdx = -1;
        sum = 0;
        
        for (int idx : diag2) {
            if (arr[idx] == 0) {
                zeroCount++;
                zeroIdx = idx;
            } else {
                sum += arr[idx];
            }
        }
        
        if (zeroCount == 1) {
            arr[zeroIdx] = magicSum - sum;
        }
    }
}