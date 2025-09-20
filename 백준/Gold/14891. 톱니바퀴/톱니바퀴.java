import java.io.*;
import java.util.*;

public class Main {
    static int[][] gears = new int[4][8];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 4개의 톱니바퀴 상태 입력
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = line.charAt(j) - '0';
            }
        }
        
        int K = Integer.parseInt(br.readLine());
        
        // K번 회전 수행
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1; // 0-based 인덱스
            int direction = Integer.parseInt(st.nextToken());
            
            rotate(gearNum, direction);
        }
        
        // 최종 점수 계산
        int totalScore = 0;
        int[] scores = {1, 2, 4, 8};
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) { // 12시 방향이 S극인 경우
                totalScore += scores[i];
            }
        }
        
        sb.append(totalScore);
        System.out.print(sb.toString());
    }
    
    static void rotate(int gearNum, int direction) {
        // 회전 전 상태를 기준으로 어떤 톱니바퀴가 회전할지 결정
        boolean[] willRotate = new boolean[4];
        int[] rotateDirection = new int[4];
        
        willRotate[gearNum] = true;
        rotateDirection[gearNum] = direction;
        
        // 선택된 톱니바퀴에서 왼쪽으로 전파
        for (int i = gearNum - 1; i >= 0; i--) {
            // i+1번 톱니바퀴의 6번 톱니와 i번 톱니바퀴의 2번 톱니 비교
            if (gears[i+1][6] != gears[i][2]) {
                willRotate[i] = true;
                rotateDirection[i] = -rotateDirection[i+1]; // 반대 방향
            } else {
                break; // 더 이상 전파되지 않음
            }
        }
        
        // 선택된 톱니바퀴에서 오른쪽으로 전파
        for (int i = gearNum + 1; i < 4; i++) {
            // i-1번 톱니바퀴의 2번 톱니와 i번 톱니바퀴의 6번 톱니 비교
            if (gears[i-1][2] != gears[i][6]) {
                willRotate[i] = true;
                rotateDirection[i] = -rotateDirection[i-1]; // 반대 방향
            } else {
                break; // 더 이상 전파되지 않음
            }
        }
        
        // 실제 회전 수행
        for (int i = 0; i < 4; i++) {
            if (willRotate[i]) {
                rotateGear(i, rotateDirection[i]);
            }
        }
    }
    
    static void rotateGear(int gearIdx, int direction) {
        if (direction == 1) { // 시계 방향
            // 마지막 원소를 맨 앞으로
            int temp = gears[gearIdx][7];
            for (int i = 7; i > 0; i--) {
                gears[gearIdx][i] = gears[gearIdx][i-1];
            }
            gears[gearIdx][0] = temp;
        } else { // 반시계 방향
            // 첫 번째 원소를 맨 뒤로
            int temp = gears[gearIdx][0];
            for (int i = 0; i < 7; i++) {
                gears[gearIdx][i] = gears[gearIdx][i+1];
            }
            gears[gearIdx][7] = temp;
        }
    }
}