import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 투 포인터와 각 원소의 개수를 추적하는 HashMap
        int left = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        int maxLength = 0;
        
        for (int right = 0; right < n; right++) {
            // 오른쪽 포인터의 원소 개수 증가
            count.put(arr[right], count.getOrDefault(arr[right], 0) + 1);
            
            // 현재 원소의 개수가 K를 초과하면 왼쪽 포인터 이동
            while (count.get(arr[right]) > k) {
                count.put(arr[left], count.get(arr[left]) - 1);
                if (count.get(arr[left]) == 0) {
                    count.remove(arr[left]);
                }
                left++;
            }
            
            // 현재 윈도우 크기로 최대 길이 업데이트
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        System.out.println(maxLength);
    }
}