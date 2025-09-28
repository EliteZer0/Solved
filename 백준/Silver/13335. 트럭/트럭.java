import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // 트럭 수
        int w = Integer.parseInt(firstLine[1]); // 다리 길이
        int L = Integer.parseInt(firstLine[2]); // 최대 하중
        
        String[] secondLine = br.readLine().split(" ");
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(secondLine[i]);
        }
        
        // bridge[i] = 해당 위치의 트럭 무게 (0이면 빈 자리)
        int[] bridge = new int[w];
        
        int time = 0;
        int truckIdx = 0;
        
        while (truckIdx < n || !isBridgeEmpty(bridge)) {
            time++;
            
            // 다리 위 트럭들을 한 칸씩 이동
            moveTrucksOnBridge(bridge);
            
            // 새로운 트럭이 다리에 진입할 수 있는지 확인
            if (truckIdx < n && canEnterBridge(bridge, trucks[truckIdx], L)) {
                bridge[0] = trucks[truckIdx];
                truckIdx++;
            }
        }
        
        sb.append(time);
        System.out.println(sb.toString());
    }
    
    private static void moveTrucksOnBridge(int[] bridge) {
        // 맨 앞의 트럭은 다리를 벗어나기 때문에 앞부터 한 칸 씩 앞으로 이동
        // (idx 0이 다리 시작점)
        for (int i = bridge.length - 1; i > 0; i--) {
            bridge[i] = bridge[i - 1];
        }
        bridge[0] = 0; // 시작점 비움
    }
    
    private static boolean canEnterBridge(int[] bridge, int truckWeight, int maxWeight) {
        int currentWeight = truckWeight;
        for (int weight : bridge) {
            currentWeight += weight;
        }
        return currentWeight <= maxWeight;
    }
    
    private static boolean isBridgeEmpty(int[] bridge) {
        for (int weight : bridge) {
            if (weight > 0) {
                return false;
            }
        }
        return true;
    }
}