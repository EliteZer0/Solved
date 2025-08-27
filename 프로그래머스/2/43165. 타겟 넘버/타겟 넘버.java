import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        // 현재 레벨의 각 합계와 그 경우의 수를 저장
        Map<Integer, Integer> currentLevel = new HashMap<>();
        currentLevel.put(0, 1); // 초기 상태: 합계 0이 1개
        
        // 각 숫자에 대해 BFS 수행
        for (int number : numbers) {
            Map<Integer, Integer> nextLevel = new HashMap<>();
            
            // 현재 레벨의 모든 상태에 대해
            for (Map.Entry<Integer, Integer> entry : currentLevel.entrySet()) {
                int currentSum = entry.getKey();
                int count = entry.getValue();
                
                // 현재 숫자를 더하는 경우
                int plusSum = currentSum + number;
                nextLevel.put(plusSum, nextLevel.getOrDefault(plusSum, 0) + count);
                
                // 현재 숫자를 빼는 경우
                int minusSum = currentSum - number;
                nextLevel.put(minusSum, nextLevel.getOrDefault(minusSum, 0) + count);
            }
            
            currentLevel = nextLevel;
        }
        
        // 타겟과 같은 합계의 경우의 수 반환
        return currentLevel.getOrDefault(target, 0);
    }
}