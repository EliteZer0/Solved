import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // 입구 순서를 저장 (차량번호 -> 순서)
        Map<String, Integer> entryOrder = new HashMap<>();
        String[] entryCars = new String[n];
        
        // 대근이가 적은 입구 순서
        for (int i = 0; i < n; i++) {
            String car = br.readLine();
            entryCars[i] = car;
            entryOrder.put(car, i);
        }
        
        // 영식이가 적은 출구 순서
        String[] exitCars = new String[n];
        for (int i = 0; i < n; i++) {
            exitCars[i] = br.readLine();
        }
        
        // 추월한 차량을 찾기
        Set<String> overtakenCars = new HashSet<>();
        
        // 각 차량에 대해, 그 차량보다 출구에서 먼저 나온 차량들 중에서
        // 입구에서는 더 늦게 들어간 차량이 있는지 확인
        for (int i = 0; i < n; i++) {
            String currentCar = exitCars[i];
            int currentEntryPos = entryOrder.get(currentCar);
            
            // 현재 차량보다 출구에서 먼저 나온 차량들을 확인
            for (int j = 0; j < i; j++) {
                String earlierExitCar = exitCars[j];
                int earlierEntryPos = entryOrder.get(earlierExitCar);
                
                // 출구에서는 먼저 나왔는데, 입구에서는 더 늦게 들어간 경우
                // = 추월이 발생한 경우
                if (earlierEntryPos > currentEntryPos) {
                    overtakenCars.add(earlierExitCar);
                }
            }
        }
        
        System.out.println(overtakenCars.size());
    }
}