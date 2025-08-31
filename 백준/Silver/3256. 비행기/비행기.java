import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] passengerTargets = new int[N];
        
        for (int i = 0; i < N; i++) {
            passengerTargets[i] = Integer.parseInt(br.readLine());
        }
        
        // aisle[i] = -1이면 비어있음, 양수면 그 승객의 목표 행
        int[] aisle = new int[1001];
        Arrays.fill(aisle, -1);
        
        // 각 행에서 짐 넣는 남은 시간 (0이면 짐 안 넣는 중, 1~5면 짐 넣는 중)
        int[] luggageTime = new int[1001];
        
        // 시간 0에 승객은 1행에 위치(테케 1번 참고)
        int totalTime = -1;
        
        // 모든 승객을 입장시키는 루프
        for (int i = 0; i < passengerTargets.length; i++) {
            aisle[0] = passengerTargets[i]; // 입구(0행)에 새 승객 배치
            // 입장할 자리가 생길 때까지 시뮬레이션
            totalTime += simulate(aisle, luggageTime, "insert");
        }
        
        // 모든 승객이 자리에 앉을 때까지 시뮬레이션
        totalTime += simulate(aisle, luggageTime, "move");
        
        sb.append(totalTime);
        System.out.println(sb.toString());
    }
    
    private static int simulate(int[] aisle, int[] luggageTime, String mode) {
        int time = 0;
        
        while (shouldContinueSimulation(aisle, mode)) {
            processPassengerMovement(aisle, luggageTime);
            processEntranceMovement(aisle, luggageTime);
            time++;
        }
        return time;
    }
    
    private static boolean shouldContinueSimulation(int[] aisle, String mode) {
        if (mode.equals("insert")) {
            return aisle[0] != -1; // 입구가 빌 때까지
        } else {
            return !isAisleEmpty(aisle); // 복도가 완전히 빌 때까지
        }
    }
    
    private static void processPassengerMovement(int[] aisle, int[] luggageTime) {
        // 뒤에서부터 처리 (1000행 -> 1행)
        for (int currentRow = 1000; currentRow > 0; currentRow--) {
            int person = aisle[currentRow]; // 현재 행에 있는 사람의 목표행
            
            if (person != -1) {
                if (isAtTargetSeat(currentRow, person)) {
                    handleLuggageLoading(aisle, luggageTime, currentRow);
                } else {
                    attemptMovement(aisle, luggageTime, currentRow, person);
                }
            }
        }
    }
    
    private static boolean isAtTargetSeat(int currentRow, int targetRow) {
        return currentRow == targetRow;
    }
    
    private static void handleLuggageLoading(int[] aisle, int[] luggageTime, int row) {
        luggageTime[row]--;
        // 짐을 다 넣었으면 자리에서 제거
        if (luggageTime[row] == 0) {
            aisle[row] = -1;
        }
    }
    
    private static void attemptMovement(int[] aisle, int[] luggageTime, int currentRow, int targetRow) {
        int nextRow = currentRow + 1;
        // 앞 행이 비어있고, 목표 행 이하인 경우 이동 가능
        if (nextRow <= targetRow && aisle[nextRow] == -1) {
            // 사람을 다음 행으로 이동
            aisle[nextRow] = targetRow;
            aisle[currentRow] = -1;
            
            // 목표 행에 도착했으면 짐 넣기 시작 (5초)
            if (nextRow == targetRow) {
                luggageTime[nextRow] = 5;
            }
        }
    }
    
    private static void processEntranceMovement(int[] aisle, int[] luggageTime) {
        // 0행(입구)에서 1행으로 이동 처리
        if (aisle[0] != -1 && aisle[1] == -1) {
            int person = aisle[0];
            if (1 <= person) {
                aisle[1] = person;
                aisle[0] = -1;
                
                // 목표 행에 도착했으면 짐 넣기 시작
                if (1 == person) {
                    luggageTime[1] = 5;
                }
            }
        }
    }
    
    private static boolean isAisleEmpty(int[] aisle) {
        for (int i = 0; i < aisle.length; i++) {
            if (aisle[i] != -1) {
                return false;
            }
        }
        return true;
    }
}